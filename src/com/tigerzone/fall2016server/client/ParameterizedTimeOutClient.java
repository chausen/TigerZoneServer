package com.tigerzone.fall2016server.client;

import java.util.Timer;

/**
 * Created by chausen on 12/4/16.
 */
public class ParameterizedTimeOutClient extends ParameterizedClient {

    private boolean firstMove = true;

    ParameterizedTimeOutClient(String host, int port, String player1MovesFilename, String player2MovesFilename) {
        super(host, port, player1MovesFilename, player2MovesFilename);
    }

    @Override
    public void playGame() {
        if (firstMove) {
            // Sleep for 1.2 seconds so you timeout
            try {
                Thread.sleep(1200);
                firstMove = false;
            } catch (InterruptedException e) {
                System.err.println("Exception in ParameterizedTimeOutClient:" + e.getMessage());
            }
        }
        super.playGame();
    }
}
