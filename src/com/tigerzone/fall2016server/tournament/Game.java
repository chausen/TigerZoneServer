package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.ports.IOPort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Game extends Thread{
    private int gameID;
    private Match match;
    TournamentPlayer player1;
    TournamentPlayer player2;
    LinkedList<PlayableTile> tileStack;
    //private GamePlayerCommunication gamePlayerCommunication;
    private IOPort ioPort;

    public Game(int gameID, TournamentPlayer player1, TournamentPlayer player2,
                LinkedList<PlayableTile> tileStack, Match match) {
        ioPort = new IOPort(this.gameID, player1.getUsername(), player2.getUsername(), tileStack);

        this.gameID = gameID;
        this.player1 = player1;
        this.player2 = player2;
        this.tileStack = tileStack;
        this.match = match;
        //this.gamePlayerCommunication = new GamePlayerCommunication(player1, player2);
    }

    @Override
    public void run(){
        playGame();
    }

    public void readMessageFromTournamemtPlayer(){

    }

    public void writeMessageToTournamentPlayer(){

    }

    void playGame() {
        ioPort.initialize();
        Queue<String> player1Messages = ioPort.getPlayer1MessageQueue();
        Queue<String> player2Messages = ioPort.getPlayer2MessageQueue();

        // get input from socket and pass it to this method
        while(!ioPort.isGameOver()) {
            try {
                sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(!player1Messages.isEmpty()){
                player1.sendMessageToPlayer(player1Messages.remove());
            }

            if(!player1Messages.isEmpty()) {
                player2.sendMessageToPlayer(player2Messages.remove());
            }

//            if (!player1ReadQueue.isEmpty()) {
//                String messageFromServer = player1ReadQueue.pop();
//                ioPort.receiveTurn(messageFromServer);
//            }
        }

        notifyComplete();

    }

    private void notifyComplete(){
        match.notifyComplete(gameID);
    }

    public Match getMatch() {
        return match;
    }

    public int getGameID() {
        return gameID;
    }

    public TournamentPlayer getPlayer1() {
        return player1;
    }

    public TournamentPlayer getPlayer2() {
        return player2;
    }

    public int getPlayer1FinalScore(){
        return ioPort.getFinalScore(player1.getUsername());
    }

    public int getPlayer2FinalScore(){
        return ioPort.getFinalScore(player2.getUsername());
    }

//    public Deque<String> getPlayer1ReadQueue(){
//        return this.player1ReadQueue;
//    }
}
