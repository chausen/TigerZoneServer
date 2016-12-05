package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016adapter.ViewInAdapter;
import com.tigerzone.fall2016adapter.ViewOutAdapter;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.*;

/**
 * Created by lenovo on 11/19/2016.
 */
public class TournamentServer implements ViewInAdapter, Runnable {

    private static HashMap<TournamentPlayer, AuthenticationThread> playerThreads = new LinkedHashMap<TournamentPlayer, AuthenticationThread>();
    private static List<TournamentPlayer> tournamentPlayers = new ArrayList<TournamentPlayer>();
    private static List<String> userNames = new ArrayList<>();
    private ViewOutAdapter viewOutAdapter;

    Challenge challenge;

    private static int PORT = 4444;
    private static int seed = 123;
    private static int MAX_CONNECTIONS = 20;
    private static int tournamentID = 1;
    private static int numOfChallenges = 9; //the number of challenges is actually this plus 1

    public int getNumOfChallengesComplete() {
        return numOfChallengesComplete;
    }

    public int getNumOfChallenges() {
        return numOfChallenges;
    }

    private int numOfChallengesComplete = 0;

    // Default constructor
    public TournamentServer() {}

    // Constructor used for parameterized main: 3 arguments
    public TournamentServer(int port, int seed, int maxConnections) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
    }

    // Constructor used for parameterized main: 4 arguments
    public TournamentServer(int port, int seed, int maxConnections, int tournamentID) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.tournamentID = tournamentID;
    }

    // Constructor used for parameterized main: 5 arguments
    public TournamentServer(int port, int seed, int maxConnections, int tournamentID, int numOfChallenges) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.tournamentID = tournamentID;
        this.numOfChallenges = numOfChallenges - 1;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void runTournament() {
        authentication();
        if(tournamentPlayers.size()>1) {
            startChallenge(tournamentPlayers);
        } else{
            System.out.println("Not enough players for a tournament");
            viewOutAdapter.notifyEndOfTournament();
//            System.exit(1);
        }
    }

    public void startChallenge(List<TournamentPlayer> tournamentPlayers) {
        Logger.initializeLogger(tournamentID, viewOutAdapter);
        challenge = new Challenge(this, seed, tournamentPlayers);
        challenge.beginChallenge();
    }


    public void authentication() { //creates a connectionHandler thread to handle authentication
        ConnectionHandler connectionHandler = new ConnectionHandler(MAX_CONNECTIONS);
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

    public void authenticationExecutor() {
        ConnectionExecutor connectionExecutor = new ConnectionExecutor(MAX_CONNECTIONS);
        new Thread(connectionExecutor).start();
        for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
            System.out.println("These are the tournament players " + tournamentPlayer.getUsername());
        }
    }

    public void notifyChallengeComplete() {
        //TODO: end of tournament shut down

        if(numOfChallengesComplete++ == numOfChallenges) {
            for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
                tournamentPlayer.sendMessageToPlayer("THANK YOU FOR PLAYING! GOODBYE");
                try {
                    tournamentPlayer.closeConnection();
                } catch (IOException e) {
                    System.out.println("Couldn't close player connection");
                    continue;
                }

            }
            // This is here to prevent the GUI from closing at the end of the tournament
//        while (true) {
//            if (Thread.activeCount() > 5) {
//                // do nothing
//            } else {
//                System.exit(0);
//            }
//        }
            viewOutAdapter.notifyEndOfTournament();
        }
        else{
            challenge = new Challenge(this, seed--, tournamentPlayers);
            challenge.beginChallenge();
        }
    }

    public void setViewOutAdapter(ViewOutAdapter adapter) {
        this.viewOutAdapter = adapter;
    }

    public static HashMap<TournamentPlayer, AuthenticationThread> getPlayerThreads() {
        return playerThreads;
    }

    public static List<TournamentPlayer> getTournamentPlayers() {
        return tournamentPlayers;
    }

    public static List<String> getUserNames() {
        return userNames;
    }

    @Override
    public void run() {
        runTournament();
    }
}


