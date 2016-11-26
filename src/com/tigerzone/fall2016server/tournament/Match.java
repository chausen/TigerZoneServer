package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match {
    private TournamentPlayer player1;
    private TournamentPlayer player2;
    private LinkedList<PlayableTile> tileStack;
    private Round round;
    private int matchID;
    private Game game1;
    private Game game2;
    private boolean game1complete = false;
    private boolean game2complete = false;
    private final int setUpTime = 10;


    public Match(TournamentPlayer player1,TournamentPlayer player2, LinkedList<PlayableTile> tileStack) {
        this.tileStack = tileStack;
        this.player1 = player1;
        this.player2 = player2;
        game1 = new Game(1, player1, player2, tileStack, this);
        game2 = new Game(2, player2, player1, tileStack, this);
    }

    public void playMatch() {
        sendMessageToPlayers();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startGames();

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

    public void startGames() {
        game1.start();
        game2.start();
    }

    private void sendEndMessage(Game game){
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();
        player1.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                game.getPlayer1FinalScore() + " PLAYER " + p2.getUsername() + " " + game.getPlayer2FinalScore());
        player2.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                game.getPlayer1FinalScore() + " PLAYER " + p2.getUsername() + " " + game.getPlayer2FinalScore());
    }

    private void notifyEndGameToPlayers(){
        sendEndMessage(game1);
        sendEndMessage(game2);
    }

    public void notifyComplete(int gID){
        game1complete = gID == game1.getGameID() ? true : game1complete;
        game2complete = gID == game2.getGameID() ? true : game2complete;
        if(game1complete && game2complete){
            notifyEndGameToPlayers();
            round.notifyComplete();
        }
    }

    public void sendPlayerMoveMessages() {


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
}
