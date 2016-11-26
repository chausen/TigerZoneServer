package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

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
    private static int MAX_CONNECTIONS = 2;

    public TournamentServer() {
    }


    public void runTournament() {
        authentication();
        //authenticationExecutor();
        startChallenge(tournamentPlayers);
        notifyChallengeComplete();
    }


    public void startChallenge(List<TournamentPlayer> tournamentPlayers) {
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


