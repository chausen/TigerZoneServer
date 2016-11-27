package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016server.tournament.Game;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by lenovo on 11/21/2016.
 */
public class Logger {
    private static PrintWriter pw = null;
    private static ArrayList<String> logs = new ArrayList<>();

    /**
     * Initializes the Logger with the PrintWriter for a text-based Logger (mostly for testing) and adds the tournament
     */
    public static void initializeLogger(int tournamentID) {

        try {
            pw = new PrintWriter("./logger.txt");
        }catch (FileNotFoundException e) {
        e.printStackTrace();
        }

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

    public static void beginGame(int tournamentID, int challengeID, int roundID, int matchID, int gameID, int player1ID, int player2ID) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("BEGIN GAME PLAYER1 ");
        sb.append(player1ID);
        sb.append(" PLAYER2 ");
        sb.append(player2ID);
        addLogToLogger(sb.toString());
    }

    private static void end(int tournamentID){
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
    }

    public static void endGame(int tournamentID, int challengeID, int roundID, int matchID, int gameID, TournamentPlayer player1, TournamentPlayer player2) {
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
        appendPlayerID("---",sb);
        sb.append("END GAME PLAYER1 ");
        sb.append(player1.getUsername());
        sb.append(" PLAYER2 ");
        sb.append(player2.getUsername());
        addLogToLogger(sb.toString());
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
        sb.append(getGameTabs()+"TOTAL_POINTS ");
        sb.append(player.getStats().getTotalPoints());
        sb.append("\r\n");
        sb.append(getGameTabs()+"AVERAGE_RELATIVE_PERFORMANCE ");
        sb.append(player.getStats().getAvgRelPerf());
        sb.append("\r\n");
        sb.append(getGameTabs()+"LARGEST_LOSS_POINT_DIFF ");
        sb.append(player.getStats().getLargestpointdifference());
        sb.append("\r\n");
        sb.append(getGameTabs()+"AVERAGE_RELATIVE_PERFORMANCE ");
        sb.append(player.getStats().getLargestpointdifferencerelative());
        sb.append("\r\n");
        addLogToLogger(sb.toString());
    }


    //TODO: Add logic to score features when they're triggered
    public static void addFeatureScored(int tournamentID, int challengeID, int roundID, int matchID, int gameID){
        StringBuilder sb = new StringBuilder(getPrefix(tournamentID, challengeID, roundID, matchID, gameID));
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
    }

    public static void loggerTest(){
        beginGame(6,5,4,3,2,1,0);
        endChallenge(6,5);
    }

    public static String getGameTabs() {
        return "\t\t\t\t\t\t\t\t\t\t\t\t";
    }
}
