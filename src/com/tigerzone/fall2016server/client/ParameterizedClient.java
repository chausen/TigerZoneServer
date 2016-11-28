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

    private int gid;
    private HashMap<Integer, Iterator> gidToMoveset = new HashMap<>(); // Used to map a moveset to a gid for a match


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
        player1Moves = FileReader.getMoves(fullDirectoryPath(player1MovesFilename));
        player2Moves = FileReader.getMoves(fullDirectoryPath(player2MovesFilename));
    }

    /** Logs in to the server, then calls waitForGame()
     *
     * @param loginName
     * @param password
     * @throws Exception
     */
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
                waitForGame();
            }

        }
    }


    /**
     * Waits during the time before a match begins, then calls determineMoveSet().
     * If the Challenge is over, it will break out of the recursive round loop.
     */
    public void waitForGame() {
        try {
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("From Server: " + fromServer);
                if (fromServer.startsWith("MATCH BEGINS")) {
                    determineMoveSet();
                    break;
                }
                if (fromServer.equals("NOPE GOOD BYE")) {
                    System.out.println("Server says goodbye");
                    break;
                }
                if (fromServer.equals("END OF CHALLENGES")) {
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
     * and maps the appropriate movesets, then calls playGame()
     */
    public void determineMoveSet() {

        try {

            String fromServer;
            game1MoveIterator = player1Moves.iterator();
            game2MoveIterator = player2Moves.iterator();

            int numberOfGamesDetermined = 0;

            while ( (fromServer = in.readLine()) != null ) {
                System.out.println("From Server: " + fromServer);
                if (fromServer.startsWith("MAKE")) {

                    // parse the move number and gid
                    String[] split = fromServer.split(" ");
                    int moveNumber = Integer.parseInt(split[10]);
                    int gid = Integer.parseInt(split[5]);

                    // Make the first move depending on if you are first or second
                    if (moveNumber % 2 == 0) {
                        String firstMove = game2MoveIterator.next();
                        System.out.println("Client Sending move: GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        gidToMoveset.put(gid, game2MoveIterator);
                        ++numberOfGamesDetermined;
                    } else {
                        String firstMove = game1MoveIterator.next();
                        System.out.println("Client Sending move: GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        out.println("GAME " + gid + " MOVE " + moveNumber + " " + firstMove);
                        gidToMoveset.put(gid, game1MoveIterator);
                        ++numberOfGamesDetermined;
                    }

                    if (numberOfGamesDetermined == 2) {
                        playGame();
                        break;
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }
    }

    /**
     * Uses runs through the moveset for a particular Round when prompted.
     * Then calls waitForGame() at the end of the Round
     */
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
                    if (fromServer.startsWith("END OF ROUND")) {
                        waitForGame();
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

    // ========== Helper Methods ========== //
    private int getGid(String fromServer) {
        Scanner scanner = new Scanner(fromServer);
        while (scanner.hasNext()) {
            if (scanner.next().equals("GAME")) {
                return scanner.nextInt();
            }
        }
        return 0;
    }

    // Given a fileName, prepends it with the directory path for the files package
    private String fullDirectoryPath(String filename) {
        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/");
        sb.append(filename);
        return sb.toString();
    }
}

