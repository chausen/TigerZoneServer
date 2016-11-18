package com.tigerzone.fall2016server.server;

/**
 * Created by lenovo on 11/17/2016.
 */

import java.util.HashMap;

public class TournamentProtocol {
    private static final int ENTER = 0;
    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int WAITING = 3;

    private int state = ENTER;
    private int loginAttempts = 0;
    private String user = null;

    private HashMap<String, String> credentials = new HashMap<>();

    public String login(String theInput) {
        String output = null;
        credentials.put("PLAYER1", "PASSWORD1"); //dummy credentials
        credentials.put("PLAYER2", "PASSWORD2"); //dummy credentials

        if (state == ENTER) {
            output = "TOURNAMENT PASSWORD?";
            state = USERNAME;
        } else if (state == USERNAME) {
            if (credentials.containsKey(theInput)) {
                output = "PASSWORD?";
                user = theInput;
                state = PASSWORD;
            } else {
                output = "NOPE TRY AGAIN";
                state = USERNAME;
            }
        } else if (state == PASSWORD && loginAttempts<3) {
            if (theInput.equals(credentials.get(user))) {
                output = "WELCOME " + user + " PLEASE WAIT FOR THE NEXT CHALLENGE";
                state = WAITING;
            } else if (loginAttempts<3){
                output = "NOPE TRY AGAIN";
                loginAttempts++;
                state = PASSWORD;
            } else {
                output = "NOPE GOODBYE";
                loginAttempts=0;
                state = ENTER;
            }
        }
        return output;
    }



}
