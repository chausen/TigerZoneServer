package com.tigerzone.fall2016server.server;


import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.Game;
import com.tigerzone.fall2016server.tournament.TournamentPlayer;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer {
    BufferedReader serverInput;
    PrintWriter serverOutput;

    BufferedReader in;
    PrintWriter out;
    int numOfPlayers;
    int portNum;

    Challenge challenge;
    List<TournamentPlayer> tournamentPlayers;

    public TournamentServer(int portNum, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.portNum = portNum;
        this.tournamentPlayers = new ArrayList<>();
    }

    public TournamentServer(int portNum) {
        this.portNum = portNum;
    }

    public boolean isTournamentReady(){
        return (this.tournamentPlayers.size() == this.numOfPlayers);

    }


    public void login() throws IOException {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();

        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();
        outputLine = tp.login(null);
        connection.getOut().println(outputLine);

        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            connection.getOut().println(outputLine);
            if (outputLine.equals("NOPE GOOD BYE")) {
                connection.getOut().println(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    System.out.println("Player has been welcomed to the system");
                    break;
                }
                if (outputLine.equals("NOPE GOODBYE")) {
                    System.out.println("Server says goodbye inside server");
                    out.close();
                    in.close();
                    clientSocket.close();
                    serverSocket.close();
                }
            }
        }
    }

    public Connection createConnection(int portNum) throws IOException {
        return new Connection(portNum);
    }


    public boolean isLoginSuccessful(Connection connection) throws IOException {
        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();
        this.serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        this.serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);

        serverOutput.println(outputLine);

        while ((inputLine = serverInput.readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            serverOutput.println(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    addPLayerToPlayerToList(connection, tp.getUser());
                    return true;
                }

                if (outputLine.equals("NOPE GOODBYE")) {
                    System.out.println("Server says goodbye inside server");
                    serverOutput.close();
                    serverInput.close();
                    clientSocket.close();
                    serverSocket.close();
                    return false;
                }
            }
        serverOutput.close();
        serverInput.close();
        return true;
        }



    public void addPLayerToPlayerToList(Connection connection, String userName){
        TournamentPlayer tournamentPlayer = new TournamentPlayer(userName, connection);
        this.tournamentPlayers.add(tournamentPlayer);
    }

    //This class is for testing purposes only
    public void startGame(){
        TileStackGenerator stackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = stackGenerator.createTilesFromTextFile(123456789);
        Game game = new Game(1, tournamentPlayers.get(0), tournamentPlayers.get(1), tileStack);
        game.start();
    }
}
