package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016adapter.PlayerInAdapter;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Prey;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.DenArea;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.area.LakeArea;
import com.tigerzone.fall2016.area.TrailArea;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016adapter.ViewOutAdapter;
import com.tigerzone.fall2016server.scoreboard.PlayerBoxController;
import com.tigerzone.fall2016server.tournament.Game;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lenovo on 11/21/2016.
 */
public class Logger {
    private static PrintWriter pw = null;
    private static CopyOnWriteArrayList<String> logs = new CopyOnWriteArrayList<>();
    private static HashMap<Integer, Integer[]> gameLookup = new HashMap<>();
    // Old GUI
//    private static PlayerBoxController pbc;
    private static ViewOutAdapter viewOutAdapter;
    private static ObservableList<PlayerStats> listOfPlayerStats = FXCollections.observableArrayList();

    /**
     * Initializes the Logger with the PrintWriter for a text-based Logger (mostly for testing) and adds the tournament
     */
    public static void initializeLogger(int tournamentID, ViewOutAdapter adapter) {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("./");
            sb.append(getTimeStamp());
            sb.append(".txt");
            pw = new PrintWriter(new File(sb.toString()));
        }catch (FileNotFoundException e) {
        e.printStackTrace();
        }
//        pbc = new PlayerBoxController();
        viewOutAdapter = adapter;
        viewOutAdapter.giveListOfPlayerStats(listOfPlayerStats);
        begin(tournamentID);
    }

    private static void begin(int tournamentID) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID));
        appendPlayerID("---",sb);
        sb.append("BEGIN TOURNAMENT");
        addLogToLogger(sb.toString());
    }

    public static void beginChallenge(int tournamentID, int challengeID) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID));
        appendPlayerID("---",sb);
        sb.append("BEGIN CHALLENGE");
        addLogToLogger(sb.toString());
    }

    public static void beginRound(int tournamentID, int challengeID, int roundID, int numOfRounds) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID));
        appendPlayerID("---",sb);
        sb.append("BEGIN ROUND ");
        sb.append(roundID);
        sb.append(" of ");
        sb.append(numOfRounds);
        addLogToLogger(sb.toString());
    }

    public static void beginGame(int tournamentID, int challengeID, int roundID, int matchID, int gameID, String player1ID, String player2ID) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("BEGIN GAME PLAYER1 ");
        sb.append(player1ID);
        sb.append(" PLAYER2 ");
        sb.append(player2ID);
        addLogToLogger(sb.toString());
        gameLookup.put(gameID,new Integer[]{tournamentID, challengeID, roundID, matchID});
    }

    private static void end(int tournamentID){
        System.out.println("END OF TOURNAMENT");
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID));
        appendPlayerID("---",sb);
        sb.append("END TOURNAMENT");
        addLogToLogger(sb.toString());
        pw.flush();
        pw.close();
    }

    public static void endChallenge(int tournamentID, int challengeID){
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID,challengeID));
        appendPlayerID("---",sb);
        sb.append("END CHALLENGE");
        addLogToLogger(sb.toString());
        end(tournamentID);
    }

    public static void endRound(int tournamentID, int challengeID, int roundID, int numOfRounds) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID));
        appendPlayerID("---",sb);
        sb.append("END ROUND ");
        sb.append(roundID);
        sb.append(" of ");
        sb.append(numOfRounds);
        addLogToLogger(sb.toString());
        System.out.println("ViewAdapter: " + viewOutAdapter);
        viewOutAdapter.notifyEndOfRound(roundID, numOfRounds);
    }

    public static void endGame(int tournamentID, int challengeID, int roundID, int matchID, int gameID, TournamentPlayer player1, TournamentPlayer player2) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("END GAME PLAYER1 ");
        sb.append(player1.getUsername());
        sb.append(" PLAYER2 ");
        sb.append(player2.getUsername());
        addLogToLogger(sb.toString());
//        pbc.updatePlayerInfoBox(player1.getUsername(),player1.getStats());
//        pbc.updatePlayerInfoBox(player2.getUsername(),player2.getStats());

        addStats(tournamentID, challengeID, roundID, matchID, gameID, player1);
        addStats(tournamentID, challengeID, roundID, matchID, gameID, player2);
    }

    public static void messageReceived(int tournamentID, int challengeID, int roundID, int matchID, int gameID, String playerID, String message){
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID(playerID, sb);
        sb.append("RECEIVED "+message);
        addLogToLogger(sb.toString());
    }

    public static void messageSent(int tournamentID, int challengeID, int roundID, int matchID, int gameID, String playerID, String message){
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID(playerID,sb);
        sb.append("SENT "+message);
        addLogToLogger(sb.toString());
    }

    private static void appendPlayerID(String playerID, StringBuilder sb) {
        sb.append(playerID+'\t');
    }

    public static void playerStatus(Game game, Player p){
        int gameID = game.getGameID();
        int matchID = game.getMatch().getMatchID();
        int roundID = game.getMatch().getRound().getRoundID();
        int challengeID = game.getMatch().getRound().getChallenge().getChallengeID();
        int tournamentID = game.getMatch().getRound().getChallenge().getTournamentID();
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID,challengeID,roundID,matchID,gameID));
        appendPlayerID(p.getPlayerId(),sb);
        sb.append("STATUS ");
        sb.append(" SCORE ");
        sb.append(game.getPlayerScore(p));
        sb.append(" TIGERS ");
        sb.append(p.getGoodSupply());
        sb.append(" CROCS ");
        sb.append(p.getBadSupply());
        addLogToLogger(sb.toString());
    }

    private static void addStats(int tournamentID, int challengeID, int roundID, int matchID, int gameID, TournamentPlayer player){
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("END GAME PLAYER ");
        sb.append(player.getUsername());
        sb.append(" STATS \r\n");
        //TODO: Add statistics
        sb.append(getGameTabs()+"GAMES_PLAYED ");
        sb.append(player.getStats().getGamesPlayed());
        sb.append("\r\n");
        sb.append(getGameTabs()+"OUTRIGHT_WINS ");
        sb.append(player.getStats().getWins());
        sb.append("\r\n");
        sb.append(getGameTabs()+"WINS_BY_FORFEIT ");
        sb.append(player.getStats().getWinsByForfeit());
        sb.append("\r\n");
        sb.append(getGameTabs()+"TIES ");
        sb.append(player.getStats().getTies());
        sb.append("\r\n");
        sb.append(getGameTabs()+"LOSSES ");
        sb.append(player.getStats().getLosses());
        sb.append("\r\n");
        sb.append(getGameTabs()+"LOSSES_BY_FORFEIT");
        sb.append(player.getStats().getLossesByForfeit());
        sb.append("\r\n");
        sb.append(getGameTabs()+"TOTAL_POINTS ");
        sb.append(player.getStats().getTotalPoints());
        sb.append("\r\n");
        sb.append(getGameTabs()+"AVERAGE_RELATIVE_PERFORMANCE ");
        sb.append(player.getStats().getAvgRelPerf());
        sb.append("\r\n");
        sb.append(getGameTabs()+"LARGEST_LOSS_POINT_DIFF ");
        sb.append(player.getStats().getLargestpointdifference());
        sb.append("\r\n");
        sb.append(getGameTabs()+"LARGEST_LOSS_RELATIVE ");
        sb.append(player.getStats().getLargestpointdifferencerelative());
        sb.append("\r\n");
        addLogToLogger(sb.toString());
    }


    //TODO: Add logic to score features when they're triggered
    public static void addFeatureScored(int gameID, PlayerInAdapter inAdapter, String loginName1, String loginName2, Map<Player, Integer> playerScores, JungleArea ja){
        Integer[] ids = gameLookup.get(gameID);
        int tournamentID = ids[0];
        int challengeID = ids[1];
        int roundID = ids[2];
        int matchID = ids[3];
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("JUNGLE\r\n");
        sb.append(getGameTabs());
        sb.append("COMPLETED ");
        sb.append(ja.isComplete());
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(inAdapter.getTileRepresentationString(ja.getBoardTiles()));
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("BOAR 0\r\n");
        sb.append(getGameTabs());
        sb.append("BUFFALO 0\r\n");
        sb.append(getGameTabs());
        sb.append("DEER 0\r\n");
        sb.append(getGameTabs());
        sb.append("CROCS 0\r\n");
        sb.append(getGameTabs());
        int player1tigercount = 0;
        int player2tigercount = 0;
        int player1croccount = 0;
        int player2croccount = 0;
        for(Tiger t : ja.getTigerList()){
            String s = t.getOwner().getPlayerId();
            if(s.equals(loginName1))
                player1tigercount++;
            else player2tigercount++;
        }
        sb.append(loginName1+" TIGERS ");
        sb.append(player1tigercount);
        sb.append(" CROCS ");
        sb.append(player1croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName1))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName1)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(loginName2+" TIGERS ");
        sb.append(player2tigercount);
        sb.append(" CROCS ");
        sb.append(player2croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName2))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName2)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        addLogToLogger(sb.toString());
    }

    public static void addFeatureScored(int gameID, PlayerInAdapter inAdapter, String loginName1, String loginName2, Map<Player, Integer> playerScores, DenArea da){
        Integer[] ids = gameLookup.get(gameID);
        int tournamentID = ids[0];
        int challengeID = ids[1];
        int roundID = ids[2];
        int matchID = ids[3];
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("DEN\r\n");
        sb.append(getGameTabs());
        sb.append("COMPLETED ");
        sb.append(da.isComplete());
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(inAdapter.getTileRepresentationString(da.getBoardTiles()));
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("BOAR 0\r\n");
        sb.append(getGameTabs());
        sb.append("BUFFALO 0\r\n");
        sb.append(getGameTabs());
        sb.append("DEER 0\r\n");
        sb.append(getGameTabs());
        sb.append("CROCS 0\r\n");
        sb.append(getGameTabs());
        int player1tigercount = 0;
        int player2tigercount = 0;
        int player1croccount = 0;
        int player2croccount = 0;
        for(Tiger t : da.getTigerList()){
            String s = t.getOwner().getPlayerId();
            if(s.equals(loginName1))
                player1tigercount++;
            else player2tigercount++;
        }
        sb.append(loginName1+" TIGERS ");
        sb.append(player1tigercount);
        sb.append(" CROCS ");
        sb.append(player1croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName1))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName1)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(loginName2+" TIGERS ");
        sb.append(player2tigercount);
        sb.append(" CROCS ");
        sb.append(player2croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName2))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName2)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        addLogToLogger(sb.toString());
    }



    public static void addFeatureScored(int gameID, PlayerInAdapter inAdapter, String loginName1, String loginName2, Map<Player, Integer> playerScores, LakeArea la){
        Integer[] ids = gameLookup.get(gameID);
        int tournamentID = ids[0];
        int challengeID = ids[1];
        int roundID = ids[2];
        int matchID = ids[3];
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("LAKE\r\n");
        sb.append(getGameTabs());
        sb.append("COMPLETED ");
        sb.append(la.isComplete());
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(inAdapter.getTileRepresentationString(la.getBoardTiles()));
        //TODO: Add logic to get boar, buffalo, and deer from LAKES
        int boarcount = 0;
        int deercount = 0;
        int buffalocount = 0;
        if(la.containsBoar()) boarcount++;
        if(la.containsDeer()) deercount++;
        if(la.containsBuffalo()) buffalocount++;
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("BOAR ");
        sb.append(boarcount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("BUFFALO ");
        sb.append(buffalocount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("DEER ");
        sb.append(deercount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("CROCS ");
        sb.append(la.getCrocodileList().size());
        sb.append("\r\n");
        sb.append(getGameTabs());
        int player1tigercount = 0;
        int player2tigercount = 0;
        int player1croccount = 0;
        int player2croccount = 0;
        for(Tiger t : la.getTigerList()){
            String s = t.getOwner().getPlayerId();
            if(s.equals(loginName1))
                player1tigercount++;
            else player2tigercount++;
        }

        for(Crocodile c : la.getCrocodileList()) {

            Player p = c.getOwner();
            if(p != null){
                if(p.getPlayerId().equals(loginName1)){
                    player1croccount++;
                }
                else player2croccount++;
            }

        }
        sb.append(loginName1+" TIGERS ");
        sb.append(player1tigercount);
        sb.append(" CROCS ");
        sb.append(player1croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName1))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName1)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(loginName2+" TIGERS ");
        sb.append(player2tigercount);
        sb.append(" CROCS ");
        sb.append(player2croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName2))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName2)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        addLogToLogger(sb.toString());

    }

    public static void addFeatureScored(int gameID, PlayerInAdapter inAdapter, String loginName1, String loginName2, Map<Player, Integer> playerScores, TrailArea ta){
        Integer[] ids = gameLookup.get(gameID);
        int tournamentID = ids[0];
        int challengeID = ids[1];
        int roundID = ids[2];
        int matchID = ids[3];
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("TRAIL\r\n");
        sb.append(getGameTabs());
        sb.append("COMPLETED ");
        sb.append(ta.isComplete());
        //TODO: Add in Logic for getting the X and Y coord of all Tiles.
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(inAdapter.getTileRepresentationString(ta.getBoardTiles()));
        sb.append("\r\n");
        sb.append(getGameTabs());
        //TODO: Add logic to get boar, buffalo, and deer from LAKES
        int boarcount = 0;
        int deercount = 0;
        int buffalocount = 0;
        for(Prey pr : ta.getPreyList())
        {
            if(pr.isBoar()) boarcount++;
            if(pr.isDeer()) deercount++;
            if(pr.isBuffalo()) buffalocount++;
        }
        sb.append("BOAR ");
        sb.append(boarcount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("BUFFALO ");
        sb.append(buffalocount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("DEER ");
        sb.append(deercount);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append("CROCS ");
        sb.append(ta.getCrocodileList().size());
        sb.append("\r\n");
        sb.append(getGameTabs());
        int player1tigercount = 0;
        int player2tigercount = 0;
        int player1croccount = 0;
        int player2croccount = 0;
        for(Tiger t : ta.getTigerList()){
            String s = t.getOwner().getPlayerId();
            if(s.equals(loginName1))
                player1tigercount++;
            else player2tigercount++;
        }

        for(Crocodile c : ta.getCrocodileList()) {

            Player p = c.getOwner();
            if(p != null){
                if(p.getPlayerId().equals(loginName1)){
                    player1croccount++;
                }
                else player2croccount++;
            }

        }
        sb.append(loginName1+" TIGERS ");
        sb.append(player1tigercount);
        sb.append(" CROCS ");
        sb.append(player1croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName1))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName1)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        sb.append(loginName2+" TIGERS ");
        sb.append(player2tigercount);
        sb.append(" CROCS ");
        sb.append(player2croccount);
        sb.append(" SCORE ");
        if(playerScores.get(inAdapter.getPlayer(loginName2))!= null)
            sb.append(playerScores.get(inAdapter.getPlayer(loginName2)));
        else sb.append(0);
        sb.append("\r\n");
        sb.append(getGameTabs());
        addLogToLogger(sb.toString());

    }


    /**
     * Gets a TimeStamp of the current time (set on the computer) to prefix to any log added.
     * @return String that has time stamp in a specific format
     */
    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
    }

    /**
     * getPrefix(...) is to get the TimeStamp and affix any IDs and "3 dashes" to our Log.
     * @param tournamentID We HAVE to have a tournamentID, so this is included all the time.
     * @param IDs Any other IDs are possibly optional, so this is varargs.Will replace dashes with the IDs.
     * @return
     */
    private static String getPrefix(int tournamentID, int... IDs){
        StringBuilder sb = new StringBuilder();
        int dashcount = 4;
        sb.append(getTimeStamp()+'\t');//Affix timestamp at the very beginning.
        sb.append(tournamentID);
        sb.append('\t');
        for(int id : IDs){
            dashcount--;//Decrement dashcount since we have one more ID to add.
            sb.append(id);
            sb.append('\t');
        }
        for(;dashcount > 0; dashcount--){
            sb.append("---\t");//Add the three dashes.
        }
        return sb.toString();
    }


    public static void addLogToLogger(String log){
        logs.add(log);
        writeLineToTextFile(log);
    }

    private static void writeLineToTextFile(String s)  {
        pw.println(s);
        pw.flush();
    }

    public static void loggerTest(){
        beginGame(6,5,4,3,2,"1","0");
        //endChallenge(6,5);
    }

    public static String getGameTabs() {
        return "\t\t\t\t\t\t\t\t\t\t\t\t";
    }

    //Doing something that Dave requested we show at the end: a player's name, wins, losses, ties, wins by forfeit, and losses by forfeit
    public static void createEndStats(List<TournamentPlayer> players) {
        PrintWriter printwriter = null;
        try {
            printwriter = new PrintWriter(new File("./FinalStats.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Create a file called FinalStats.txt. This is an error caused by Jeff so yell at him.");
        }

        printwriter.println("TEAM\tWINS\tLOSSES\tTIES\tWINSFORFEIT\tLOSSESFORFEIT");
        for(TournamentPlayer player : players){
            StringBuilder stringb = new StringBuilder();
            stringb.append(player.getUsername());
            stringb.append('\t');
            stringb.append(player.getStats().getWins());
            printwriter.println('\t');
            stringb.append(player.getStats().getLosses());
            stringb.append('\t');
            stringb.append(player.getStats().getTies());
            stringb.append('\t');
            stringb.append(player.getStats().getWinsByForfeit());
            stringb.append('\t');
            stringb.append(player.getStats().getLossesByForfeit());
            printwriter.println(stringb.toString());
        }
        printwriter.flush();
        printwriter.close();
    }

    public static void addPlayerStats(PlayerStats playerStats) {
        listOfPlayerStats.add(playerStats);
    }
}
