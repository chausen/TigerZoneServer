package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.TournamentPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 * Created by lenovo on 11/19/2016.
 */
public class TournamentServer {

    private static HashMap<TournamentPlayer, AuthenticationThread> playerThreads = new LinkedHashMap<TournamentPlayer, AuthenticationThread>();
    private static List<TournamentPlayer> tournamentPlayers = new ArrayList<TournamentPlayer>();
    Challenge challenge;

    private static int PORT = 4444;
    private static int seed = 123456789;

    public TournamentServer() {
    }


    public void runTournament() {
        authentication();
        startChallenge(tournamentPlayers);
        notifyChallengeComplete();
    }


    public void startChallenge(List<TournamentPlayer> tournamentPlayers) {
        challenge = new Challenge(this, seed, tournamentPlayers);
        challenge.beginChallenge();
    }

    public void authentication() { //creates a connectionHandler thread to handle authentication
        int maxConnections = 24;
        ConnectionHandler connectionHandler = new ConnectionHandler(maxConnections);
        connectionHandler.start();
        try {
            connectionHandler.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
            System.out.println("These are the tournament players " + tournamentPlayer.getUsername());
        }
    }

    public void authenticate() { //handles authentication from within this server class

        long startTime = System.currentTimeMillis();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(1000);
            Connection connection;
            while (!tournamentReady(startTime)) { //might need to spin a thread for authentication itself so can interrupt?
                try {
                    connection = new Connection(serverSocket);
                    connection.accept(); //the loop holds here until a new connection attempt is made
                    connection.setupIO();
                    new AuthenticationThread(connection).start();
                } catch (SocketTimeoutException ste) {
                    System.out.println("Waited 1000 millis but no connection made");
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    public boolean tournamentReady() {
        boolean ready = false;
        if (tournamentPlayers.size() >= 2) {
            ready = true;
        }
        return ready;
    }

    public boolean tournamentReady(long start) {
        boolean ready = false;
        long timePassed = System.currentTimeMillis() - start;
        System.out.println("This is how much time has passed in tournamnet ready " + timePassed);
        if (tournamentPlayers.size() >= 24 || (timePassed > 40000)) {
            ready = true;
        }
        return ready;
    }

    public void notifyChallengeComplete(){
        //TODO: end of tournament shut down
    }

    public static HashMap<TournamentPlayer, AuthenticationThread> getPlayerThreads() {
        return playerThreads;
    }

    public static List<TournamentPlayer> getTournamentPlayers() {
        return tournamentPlayers;
    }
}


