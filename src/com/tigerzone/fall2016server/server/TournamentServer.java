package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016adapter.ViewInAdapter;
import com.tigerzone.fall2016adapter.ViewOutAdapter;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;
import javafx.collections.ObservableList;
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
    private boolean eliminationTournament;

    private ViewOutAdapter viewOutAdapter = new ViewOutAdapter() {
        @Override
        public void notifyEndOfRound(int roundsCompleted, int totalRounds) {

        }

        @Override
        public void notifyEndOfTournament() {
            System.exit(0);
        }

        @Override
        public void giveListOfPlayerStats(ObservableList<PlayerStats> listOfPlayerStats) {

        }
    };

    Challenge challenge;

    private static int PORT = 4444;
    private static int seed = 123;
    private static int MAX_CONNECTIONS = 20;
    private static int tournamentID = 1;
    private static int numOfChallenges = 3; //the number of challenges is actually this plus 1
    //set true when teams are dropped from next challenge if they forfeit in previous challenge


    public int getNumOfChallengesComplete() {
        return numOfChallengesComplete;
    }

    public int getNumOfChallenges() {
        return numOfChallenges;
    }

    private int numOfChallengesComplete = 0;

    boolean tournamentCancelled = false;

    // Default constructor
    public TournamentServer() {
    }

    // Constructor used for parameterized main: 3 arguments
    public TournamentServer(int port, int seed, int maxConnections) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.eliminationTournament = false;
    }

    // Constructor used for parameterized main: 4 arguments
    public TournamentServer(int port, int seed, int maxConnections, int tournamentID) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.tournamentID = tournamentID;
        this.eliminationTournament = false;
    }

    // Constructor used for parameterized main: 5 arguments
    public TournamentServer(int port, int seed, int maxConnections, int tournamentID, int numOfChallenges) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.tournamentID = tournamentID;
        this.numOfChallenges = numOfChallenges - 1;
        this.eliminationTournament = false;
    }

    // Constructor used for parameterized main: 6 arguments
    public TournamentServer(int port, int seed, int maxConnections,
                            int tournamentID, int numOfChallenges, boolean eliminationTournament) {
        this.PORT = port;
        this.seed = seed;
        this.MAX_CONNECTIONS = maxConnections;
        this.tournamentID = tournamentID;
        this.numOfChallenges = numOfChallenges - 1;
        this.eliminationTournament = eliminationTournament;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void runTournament() {
        authentication();
        if (tournamentPlayers.size() > 1) {
            startChallenge(tournamentPlayers);
        } else {
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
        ConnectionHandler connectionHandler = new ConnectionHandler(MAX_CONNECTIONS, PORT);
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

    private void endCommunicationWithPlayer(Collection<TournamentPlayer> tournamentPlayers) {
        tournamentPlayers.forEach((tournamentPlayer -> {
            tournamentPlayer.sendMessageToPlayer("THANK YOU FOR PLAYING! GOODBYE");
            try {
                tournamentPlayer.closeConnection();
            } catch (IOException e) {
                System.out.println("Couldn't close player connection");
            }
        }));
    }

    public void notifyChallengeComplete() {
        if (numOfChallengesComplete++ == numOfChallenges || tournamentCancelled) {
            endCommunicationWithPlayer(this.tournamentPlayers);
            viewOutAdapter.notifyEndOfTournament();
            System.exit(0);
        } else {
            if (eliminationTournament) {
                Set<TournamentPlayer> playersForfeitedInChallenge = this.challenge.getForfeitedPlayerSet();
                this.tournamentPlayers.removeAll(playersForfeitedInChallenge);
                endCommunicationWithPlayer(playersForfeitedInChallenge);
                //if last challenge forfeited all but one Player then Tournament ends early.
                if (this.tournamentPlayers.size() < 2) {
                    System.out.println("Tournament has to few players; ending early");
                    endCommunicationWithPlayer(this.tournamentPlayers);
                    viewOutAdapter.notifyEndOfTournament();
                    System.exit(0);
                }
            }
            challenge = new Challenge(this, seed--, this.tournamentPlayers);
            challenge.beginChallenge();
        }
    }

    @Override
    public void setViewOutAdapter(ViewOutAdapter adapter) {
        this.viewOutAdapter = adapter;
    }

    @Override
    public void cancelTournament() {
        tournamentCancelled = true;
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


