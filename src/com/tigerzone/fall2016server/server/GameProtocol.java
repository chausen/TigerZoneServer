package com.tigerzone.fall2016server.server;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by lenovo on 11/21/2016.
 */
public class GameProtocol {

    private static final int ENTER = 0;
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;

    private int state = ENTER;

    private String player1 = "A Team";
    private String player2 = "Opponent";
    private String time = "1:00:00";

    private String gameID = "1";

    public String[] setup = {"NEW MATCH YOUR OPPONENT IS PLAYER ", "THE TILES ARE ", "MATCH BEGINS IN " + time + "seconds",
            "YOU ARE THE ACTIVE PLAYER IN GAME "};

    public String[] moves = {"GAME  PLAYER <pid> PLACED <move>",
            "GAME" + gameID + "PLAYER <pid> FORFEITED ILLEGAL TILE PLACEMENT <move>",
            "GAME" + gameID + "PLAYER <pid> FORFEITED ILLEGAL MEEPLE PLACEMENT <move>",
            "GAME" + gameID + "PLAYER <pid> FORFEITED INVALID MEEPLE PLACEMENT <move>",
            "GAME" + gameID + "PLAYER <pid> FORFEITED TIMEOUT",
            "GAME" + gameID + "PLAYER <pid> FORFEITED QUIT",
            "GAME" + gameID + "PLAYER <pid> FORFEITED ILLEGAL MESSAGE RECEIVED <player message>"};

    //does the game protocol just format strings appropriately?
    //seems like the protocol will just take input from players, keep track of state, and format/generate
    //move strings appropriately for handling by our IOPort

    public String play(String move) {

        String response = null;
//
//        if (state == ENTER) {
//
//            state = PLAYER1;
//        } else if (state == PLAYER1) {
//            System.out.println("STATE IS login IN PROTOCOL");
//            if (move.equals(tournamentPass)) {
//                response = "USERNAME?";
//                System.out.println("Client entered correct tournament password");
//                state = USERNAME;
//            } else if (enterAttempts < 3){
//                response = "NOPE TRY AGAIN";
//                System.out.println("Client did not enter correct tournament password");
//                state = LOGIN;
//            } else {
//                response = "NOPE GOODBYE";
//                System.out.println("Client did not enter correct tournament password");
//                state = ENTER;
//            }
//        } else if (state == PLAYER2) {
//                state = PLAYER1;
//            } else {
//            response = "NOPE TRY AGAIN";
//            state = USERNAME;
//        }
//            } else {
//            response = "NOPE GOODBYE";
//            System.out.println("INVALID ENTRY");
//        }

        return response;

    }
}
