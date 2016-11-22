package com.tigerzone.fall2016server.server;

/**
 * Created by lenovo on 11/17/2016.
 */

import java.util.HashMap;

public class TournamentProtocol {
    private static final int ENTER = 0;
    private static final int LOGIN = 1;
    private static final int CREDENTIALS = 2;
    private static final int PASSWORD = 3;
    private static final int WAITING = 4;

    private int state = ENTER;
    private int enterAttempts = 0;
    private int loginAttempts = 0;
    private String user = null;

    private HashMap<String, String> credentials = new HashMap<>();

    public TournamentProtocol() { }

    public TournamentProtocol(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }

    public String getUser(){
        return this.user;
    }

    public String login(String input) {
        String output = null;
        credentials.put("PLAYER1", "PASSWORD1"); //dummy credentials
        credentials.put("PLAYER2", "PASSWORD2"); //dummy credentials
        String tournamentPass = "TIGERZONE";

        if (state == ENTER) {
                System.out.println("STATE IS enter IN PROTOCOL");
                output = "THIS IS SPARTA!";
                System.out.println("Soliciting tournament password");
                state = LOGIN;
        } else if (state == LOGIN) {
            System.out.println("STATE IS login IN PROTOCOL");
            if (enterAttempts < 3) {
                if (input.equals("JOIN " + tournamentPass)) {
                    output = "HELLO!";
                    System.out.println("Client entered correct tournament password");
                    state = CREDENTIALS;
                } else {
                    output = "NOPE TRY AGAIN";
                    System.out.println("Client did not enter correct tournament password");
                    state = LOGIN;
                    enterAttempts++;
                }
            } else {
                output = "NOPE GOODBYE";
                System.out.println("Client did not enter correct tournament password");
                enterAttempts = 0;
                state = ENTER;
            }
        } else if (state == CREDENTIALS) {
            if (loginAttempts < 3) {
                if (input.startsWith("I AM ")) {
                    String credentialString = input.substring(5);
                    String[] credentialSplit = credentialString.split("\\s+");
                    String username = "";
                    String password = "";
                    if (!(credentialSplit.length > 1)) {
                        output = "NOPE TRY AGAIN";
                        System.out.println("CATCHING SOME SHIT IN CS");
                        enterAttempts++;
                        state = CREDENTIALS;
                    } else {
                        username = credentialSplit[0];
                        password = credentialSplit[1];
                    }
                    if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                        user = username;
                        output = "WELCOME " + user + " PLEASE WAIT FOR THE NEXT CHALLENGE";
                        state = WAITING;
                    } else {
                        output = "NOPE TRY AGAIN";
                        loginAttempts++;
                        state = CREDENTIALS;
                    }
                } else {
                    output = "NOPE TRY AGAIN";
                    loginAttempts++;
                    state = CREDENTIALS;
                }
            } else {
                output = "NOPE GOOD BYE";
                System.out.println("TOO MANY LOGIN ATTEMPTS " + loginAttempts);
                loginAttempts = 0;
                state = ENTER;
            }
        } else {
            output = "NOPE GOOD BYE";
            System.out.println("INVALID ENTRY THAT DIDN'T ENTER ANY STATE LOGIC");
        }
        return output;
    }



}
