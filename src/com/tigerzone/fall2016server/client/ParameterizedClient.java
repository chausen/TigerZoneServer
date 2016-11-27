package com.tigerzone.fall2016server.client;

import com.tigerzone.fall2016server.files.FileReader;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.*;

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

    private List<String> player1Moves;
    private List<String> player2Moves;

    private Iterator<String> game1MoveIterator;
    private Iterator<String> game2MoveIterator;
    private Iterator<String> currentGameMoveIterator;

    private int game1MoveNumber = 1;
    private int game2MoveNumber = 1;
    private int currentGameMoveNumber;

    private int gid;
    private HashMap<Integer, Iterator> gidToMoveset = new HashMap<>();


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

        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/");
        player1Moves = FileReader.getMoves(sb.toString() + player1MovesFilename);
        player2Moves = FileReader.getMoves(sb.toString() + player2MovesFilename);
        game1MoveIterator = player1Moves.iterator();
        game2MoveIterator = player2Moves.iterator();
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
                System.out.println("From Server: " + fromServer);
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
     * Determines if this client is Player1 or Player2 (in this game)
     * and maps the appropriate movesets
     */
    public void determineMoveSet() {

        try {

            String fromServer;

            while ( (fromServer = in.readLine()) != null ) {
                System.out.println("From Server: " + fromServer);
                if (fromServer.startsWith("MAKE")) {
                    String[] split = fromServer.split(" ");
                    int moveNumber = Integer.parseInt(split[10]);
                    int gid = Integer.parseInt(split[5]);
                    if (moveNumber % 2 == 0) {
                        String firstMove = game2MoveIterator.next();
                        System.out.println("Client Sending move: GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        gidToMoveset.put(gid, game2MoveIterator);
                    } else {
                        String firstMove = game1MoveIterator.next();
                        System.out.println("Client Sending move: GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        gidToMoveset.put(gid, game1MoveIterator);
                    }
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
                System.out.println("From Server: " + fromServer);
                if (fromServer.startsWith("MAKE")) {

                    gid = getGid(fromServer);

                    if (gidToMoveset.get(gid).equals(game1MoveIterator)) {
                        String[] split = fromServer.split(" ");
                        int moveNumber = Integer.parseInt(split[10]);
                        userInput = game1MoveIterator.next();
                        System.out.println("Client sending move: GAME " + gid + " MOVE " + moveNumber + " " + userInput);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + userInput);
                    } else if (gidToMoveset.get(gid).equals(game2MoveIterator)) {
                        String[] split = fromServer.split(" ");
                        int moveNumber = Integer.parseInt(split[10]);
                        userInput = game2MoveIterator.next();
                        System.out.println("Client sending move: GAME " + gid + " MOVE " + moveNumber + " " + userInput);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + userInput);
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

    private int getGid(String fromServer) {
        Scanner scanner = new Scanner(fromServer);
        while (scanner.hasNext()) {
            if (scanner.next().equals("GAME")) {
                return scanner.nextInt();
            }
        }
        return 0;
    }
}

