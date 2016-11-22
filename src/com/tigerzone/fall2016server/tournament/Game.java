package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.ports.IOPort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Game extends Thread{
    int gameID;
    TournamentPlayer player1;
    TournamentPlayer player2;
    LinkedList<PlayableTile> tileStack;
    Deque<String> readQueue;

    public Game(int gameID, TournamentPlayer player1, TournamentPlayer player2,
                LinkedList<PlayableTile> tileStack) {
        readQueue = new ArrayDeque<>();
        this.gameID = gameID;
        this.player1 = player1;
        this.player2 = player2;
        this.tileStack = tileStack;
    }

    public Deque<String> getReadQueue(){
        return this.readQueue;
    }

    @Override
    public void run(){
        playGame();
    }

    void playGame() {
        IOPort ioPort = new IOPort(this.gameID, player1.getUsername(), player2.getUsername(), tileStack);
        ioPort.initialize();
        Queue<String> player1Messages = ioPort.getPlayer1MessageQueue();
        Queue<String> player2Messages = ioPort.getPlayer2MessageQueue();

        // get input from socket and pass it to this method
        while(true) {

            try {
                sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!readQueue.isEmpty()) {
                String messageFromServer = readQueue.pop();
                System.out.println("Game receives the message" + messageFromServer);
                ioPort.receiveTurn(messageFromServer);
            }
        }
        // expect output from game in the current player's messages
        // the first player in the IOPort parameter list goes first
//        while (!player1Messages.isEmpty()) {
//            String message = player1Messages.remove();
//            // Send message to player1 socket
//            if(message.startsWith("WELCOME")){
//
//            }
//            }
//        }
//        while (!player2Messages.isEmpty()) {
//            String message = player2Messages.remove();
//            // Send message to player2 socket
//        }

    }
}
