package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.server.TournamentServer;
import com.tigerzone.fall2016server.server.protocols.GameToClientMessageFormatter;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.IOException;
import java.net.SocketException;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match extends Thread{
    private TournamentPlayer player1;
    private TournamentPlayer player2;
    private TournamentPlayer game1player;
    private TournamentPlayer game2player;
    private LinkedList<PlayableTile> tileStack;
    private Round round;
    private int matchID;
    private Game game1;
    private Game game2;
    private boolean game1complete = false;
    private boolean game2complete = false;
    private final int setUpTime = 1;
    private Map<Integer, String> playerMessages = new HashMap<>();
    private int numOfActiveGames = 2;


    public Match(TournamentPlayer player1,TournamentPlayer player2, LinkedList<PlayableTile> tileStack) {
        this.tileStack = tileStack;
        this.player1 = player1;
        this.player2 = player2;
        game1 = new Game(1, player1, player2, tileStack, this);
        game2 = new Game(2, player2, player1, tileStack, this);
        this.game1player = player1;
        this.game2player = player2;
    }

    private void swapPlayers(){
        TournamentPlayer placeHolder = this.game1player;
        this.game1player = this.game2player;
        this.game2player = placeHolder;
    }

    public void run(){
        startMatch();
        playMatch();
    }

    private void startMatch() {
        sendMessageToPlayers();
        try {
            Thread.sleep(setUpTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //startGames();
    }

    private void playMatch() {
        int moveNumber = 1;
        game1.initializeIOport();
        game2.initializeIOport();

        try {
            player1.setCommunicationTimeout(15000);
            player2.setCommunicationTimeout(15000);
        } catch (SocketException e) {
            System.out.println("Sokcet timeout exception???");
        }

        while(!game1.isOver() || !game2.isOver()){
            //A single game will be doing the following in each line of the if statement...
            //Create prompt message for both players
            //Send each player their own prompt message
            if(!game1.isOver()){
                String game1playerPrompt = GameToClientMessageFormatter.generateMessageToActivePlayer(game1.getGameID(), 1, moveNumber, game1.getCurrentTile());
                game1player.sendMessageToPlayer(game1playerPrompt);
            }
            if(!game2.isOver()) {
                String game2playerPrompt = GameToClientMessageFormatter.generateMessageToActivePlayer(game2.getGameID(), 1, moveNumber, game2.getCurrentTile());
                game2player.sendMessageToPlayer(game2playerPrompt);
            }

            //Wait one second
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start < 1000) {

            }

            //A single game will be doing the following in each line of the if statement...
            //Get each player's response after 1 second
            //Send each player's response to the respective gamePort
            //Get the ioPort's response
            //Send the ioPort's response to both players. Note that each player gets the same message
            turnIO(game1, game1player);
            turnIO(game2, game2player);

            //swap who is the active player in each game
            swapPlayers();

            //Increment move count
            moveNumber++;
        }
        notifyEndGameToPlayers();
        round.notifyComplete();
    }

    private void turnIO(Game game, TournamentPlayer player) {
        if(!game.isOver()){
            String gamePlayerResponse = null;
            String gameResponse = null;
            try {
                gamePlayerResponse = player.readPlayerMessage();
                game.receiveTurn(gamePlayerResponse);
                gameResponse = game.getResponse();
            }
            catch (IOException e){
                gameResponse = "GAME " + game.getGameID() + " PLAYER " + player.getUsername() + " FORFEITED: TIMEOUT";
                game.endGame();
            }
            sendGameMessage(gameResponse);
        }
    }

    private String tileToSTring(LinkedList<PlayableTile> tileStack){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Iterator<PlayableTile> iter = tileStack.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(" ");
            stringBuilder.append(iter.next().getTileString());
        }
        stringBuilder.append(" ] ");
        return stringBuilder.toString();
    }

    private void sendStartMessage(TournamentPlayer player, String opponentUserName){
        player.sendMessageToPlayer("YOUR OPPONENT IS PLAYER " + opponentUserName);
        player.sendMessageToPlayer("STARTING TILE IS TLTJ- AT 0 0 0");
        player.sendMessageToPlayer("THE REMAINING 76 TILES ARE " + tileToSTring(tileStack));
        player.sendMessageToPlayer("MATCH BEGINS IN " + setUpTime + " SECONDS");


        System.out.println("YOUR OPPONENT IS PLAYER" + opponentUserName);
        System.out.println("STARTING TILE IS TLTJ- AT 0 0 0");
        System.out.println("THE REMAINING 76 TILES ARE" + tileToSTring(tileStack));
        System.out.println("MATCH BEGINS IN " + setUpTime + " SECONDS");
    }

    private void sendMessageToPlayers(){
        sendStartMessage(player1, player2.getUsername());
        sendStartMessage(player2, player1.getUsername());
    }


    private void sendEndMessage(Game game){
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();
        player1.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                game.getPlayer1FinalScore() + " PLAYER " + p2.getUsername() + " " + game.getPlayer2FinalScore());
        player2.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                game.getPlayer1FinalScore() + " PLAYER " + p2.getUsername() + " " + game.getPlayer2FinalScore());
        updatePlayerStatistics(game, p1, p2);
    }

    private void updatePlayerStatistics(Game game, TournamentPlayer p1, TournamentPlayer p2){
        HashMap<String, TournamentPlayer> playerLookup = new HashMap<>();
        Match m = game.getMatch();
        Round r = m.getRound();
        Challenge c = r.getChallenge();

        PlayerStats p1stats = p1.getStats();
        PlayerStats p2stats = p2.getStats();
        p1stats.setGamesPlayed(p1stats.getGamesPlayed()+1);
        p2stats.setGamesPlayed(p2stats.getGamesPlayed()+1);
        if(game.getPlayer1FinalScore() > game.getPlayer2FinalScore()){
            p1stats.setWins(p1stats.getWins()+1);
            p2stats.setLosses(p2stats.getLosses()+1);
            p2stats.setLargestpointdifference(game.getPlayer2FinalScore(),game.getPlayer1FinalScore());
        }
        else if(game.getPlayer1FinalScore() == game.getPlayer2FinalScore()){
            p1stats.setTies(p1stats.getTies()+1);
            p2stats.setTies(p2stats.getTies()+1);
        }
        else {
            p2stats.setWins(p2stats.getWins()+1);
            p1stats.setLosses(p1stats.getLosses()+1);
            p1stats.setLargestpointdifference(game.getPlayer1FinalScore(),game.getPlayer2FinalScore());
        }

        if(game.didForfeit()){
            PlayerStats ps = playerLookup.get(game.getForfeitedPlayer()).getStats();
            ps.setForfeits(ps.getForfeits()+1);
            if(ps == p1stats)
                p2stats.setWinsByForfeit(p2stats.getWinsByForfeit()+1);//If our ps is the same as p1stats, it means P1 forfeited.
            else p1stats.setWinsByForfeit(p1stats.getWinsByForfeit()+1);//Else, ps is player 2, P2 forfeited.
        }

        p1stats.setTotalPoints(p1stats.getTotalPoints()+game.getPlayer1FinalScore());
        p2stats.setTotalPoints(p2stats.getTotalPoints()+game.getPlayer2FinalScore());

        p1stats.setOpponentTotalPoints(p1stats.getOpponentTotalPoints()+game.getPlayer2FinalScore());
        p2stats.setOpponentTotalPoints(p2stats.getOpponentTotalPoints()+game.getPlayer1FinalScore());

        Logger.endGame(c.getTournamentID(),c.getChallengeID(),r.getRoundID(),m.getMatchID(),game.getGameID(),p1,p2);
    }

    private void notifyEndGameToPlayers(){
        sendEndMessage(game1);
        sendEndMessage(game2);
    }

    public void sendPlayerMoveMessages() {


    }

//    public void notifyComplete(int gID){
//        game1complete = gID == game1.getGameID() ? true : game1complete;
//        game2complete = gID == game2.getGameID() ? true : game2complete;
//        if(game1complete && game2complete){
//            notifyEndGameToPlayers();
//            round.notifyComplete();
//        }
//    }

    public void sendGameMessage(String playerMessage){
        player1.sendMessageToPlayer(playerMessage);
        player2.sendMessageToPlayer(playerMessage);
    }

    public void decreaseNumOfActiveGames(){
        numOfActiveGames--;
    }

    public Round getRound() {
        return round;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}
