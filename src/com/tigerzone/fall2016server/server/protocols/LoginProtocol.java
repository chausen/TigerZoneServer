package com.tigerzone.fall2016server.server.protocols;

import com.tigerzone.fall2016server.FileReader;

import java.util.HashMap;

/**
 * Created by lenovo on 11/21/2016.
 */
public class LoginProtocol {


    private static final int ENTER = 0;
    private static final int LOGIN = 1;
    private static final int CREDENTIALS = 2;
    private static final int WAITING = 3;

    private int state = ENTER;
    private int enterAttempts = 0;
    private int loginAttempts = 0;
    private String user = null;
    private String tournamentPass = "TIGERZONE";

    private HashMap<String, String> credentials = new HashMap<>();

    public LoginProtocol() {
        credentials.put("PLAYER1", "PASSWORD1"); //dummy credentials
        credentials.put("PLAYER2", "PASSWORD2"); //dummy credentials
    }

    public LoginProtocol(String credentialsFilename) {
        credentials = FileReader.getCredentials(credentialsFilename);
    }

    public LoginProtocol(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    public String getUser() {
        return this.user;
    }

    public String login(String input) {
        String output = null;

        if (state == ENTER) {
            output = "THIS IS SPARTA!";
            state = LOGIN;
        } else if (state == LOGIN) {
            enterAttempts++;
            if (input.equals("JOIN " + tournamentPass)) {
                output = "HELLO!";
                state = CREDENTIALS;
            } else if (enterAttempts < 3) {
                output = "NOPE TRY AGAIN";
                state = LOGIN;
            } else {
                output = "NOPE GOOD BYE";
                enterAttempts = 0;
                state = ENTER;
            }
        } else if (state == CREDENTIALS) {
            loginAttempts++;
            if (input.startsWith("I AM ")) {
                String credentialString = input.substring(5);
                String[] credentialSplit = credentialString.split("\\s+");
                String username = "";
                String password = "";
                if (!(credentialSplit.length > 1)) {
                    output = "NOPE TRY AGAIN";
                    state = CREDENTIALS;
                } else {
                    username = credentialSplit[0];
                    password = credentialSplit[1];
                }
                if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                    user = username;
                    output = "WELCOME " + user + " PLEASE WAIT FOR THE NEXT CHALLENGE";
                    state = WAITING;
                } else if (loginAttempts < 3) {
                    output = "NOPE TRY AGAIN";
                    state = CREDENTIALS;
                }
            } else if (loginAttempts < 3) {
                output = "NOPE TRY AGAIN";
                state = CREDENTIALS;
            } else {
                    output = "NOPE GOOD BYE";
                    loginAttempts = 0;
                    state = ENTER;
                }
            } else {
                output = "NOPE GOOD BYE";
                System.out.println("INVALID ENTRY THAT DIDN'T ENTER ANY STATE LOGIC");
            }
            return output;
        }

    public int getState() {
        return state;
    }
}

