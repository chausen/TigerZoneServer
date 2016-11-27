package com.tigerzone.fall2016server.client;

import com.tigerzone.fall2016server.files.FileReader;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 11/17/2016.
 */
public class ParameterizedClient {

    private String host;
    private int port;
    private BufferedReader in;
    private PrintWriter out;
    private Socket clientSocket;

    private String player1MovesFilename;
    private String player2MovesFilename;
    private List<String> moves;
    private Iterator<String> moveIterator;


    public ParameterizedClient(String host, int port, String player1MovesFilename, String player2MovesFilename) {
        this.host = host;
        this.port = port;
        this.player1MovesFilename = player1MovesFilename;
        this.player2MovesFilename = player2MovesFilename;
        try {
            clientSocket = new Socket(this.host, this.port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Some ioexception");
        }
    }

    public void login(String loginName, String password) throws Exception {
        String fromServer;
        while ((fromServer = in.readLine()) != null) {
            if (fromServer.equals("NOPE GOOD BYE")) {
                System.out.println("Server says goodbye");
                break;
            }
            if (fromServer.equals("THIS IS SPARTA!")) {
                out.println("JOIN TIGERZONE");
            } else if (fromServer.startsWith("HELLO!")) {
                out.println("I AM " + loginName + " " + password);
            } else if (fromServer.startsWith("WELCOME")) {
                System.out.println("ACCEPTED TO THE TOURNEY");
                break;
            }

        }
    }


    public void waitForGame() {
        try {
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.startsWith("MATCH BEGINS")) {
                    break;
                }
                if (fromServer.equals("NOPE GOOD BYE")) {
                    System.out.println("Server says goodbye");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }

    }

    /**
     * Determines if this client is Player1 or Player2 (in this game) and gets the appropriate moveset
     */
    public void determineMoveSet() {

        boolean determiningMessage = false;

        try {

            String fromServer;

            while ( (fromServer = in.readLine()) != null ) {
                if (fromServer.startsWith("MATCH")) {
                    // if next message starts with "MAKE YOUR MOVE", get player 1 moves
                    // otherwise, get player 2 moves
                    determiningMessage = true;
                }
                if (determiningMessage && (fromServer = in.readLine()) != null) {
                    if (fromServer.startsWith("MAKE YOUR MOVE")) {
                        moves = FileReader.getMoves(player1MovesFilename);
                        System.out.println("I'm Player 1");
                        moveIterator = moves.iterator();
                        String firstMove = moveIterator.next();
                        System.out.println(firstMove);
                        out.println(firstMove);
                    } else {
                        moves = FileReader.getMoves(player2MovesFilename);
                        System.out.println("I'm Player 2");
                        moveIterator = moves.iterator();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }
    }


    public void playGame() {
        try {
            String fromServer;
            String userInput = null;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.startsWith("MAKE")) {
                    if (moveIterator.hasNext()) {
                        userInput = moveIterator.next();
                        System.out.println(userInput);
                        out.println(userInput);
                    } else {
                        System.out.println("Client ran out of moves to make");
                    }
                }
                if (fromServer.equals("NOPE GOOD BYE")) {
                    System.out.println("Server says goodbye");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }

    }
}

