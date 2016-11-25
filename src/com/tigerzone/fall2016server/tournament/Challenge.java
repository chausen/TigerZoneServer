package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.TournamentServer;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Challenge {
    private TournamentServer tournamentServer;
    LinkedList<PlayableTile> tiles;
    List<TournamentPlayer> players;
    private static int challengeID = 0;
    private int cid;
    private int numOfRounds;
    private int numOfRoundsComplete;
    List<Round> rounds;

    private int currentRound = 0;


    public Challenge(TournamentServer tournamentServers, long seed, List<TournamentPlayer> players) {

        cid = challengeID++;
        this.tournamentServer = tournamentServers;
        this.tiles = TileStackGenerator.generateTiles(seed);
        if(players.size()/2 % 2 == 0){
            numOfRounds = players.size() - 1;
        }
        else {
            numOfRounds = players.size();
        }
        this.players = players;
    }

    public void beginChallenge() {
        sendMessageToPlayers();
        rounds = generateRounds();
        for (Round round: rounds) {
            round.playRound();
            numOfRoundsComplete++;
            currentRound++;
        }
        notifyComplete();
    }

    private void sendMessageToPlayers(){
        System.out.println("CID " + cid);
        System.out.println("num round " + numOfRounds);
        for(TournamentPlayer tournamentPlayer: players){
            PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
            if(numOfRounds == 1) {
                printWriter.println("NEW CHALLENGE " + cid + " YOU WILL PLAY " + numOfRounds + " MATCH");
            }
            else {
                printWriter.println("NEW CHALLENGE " + cid + " YOU WILL PLAY " + numOfRounds + " MATCHES");
            }
        }
    }

    //erik generateRounds
    public List<Round> generateRounds(){
        List<Round> rounds = new ArrayList<>();
        Round round;
        for (int roundNumber = 1; roundNumber <= numOfRounds; roundNumber++) {
            round = new Round(this, RoundRobin.listMatches(players, roundNumber, tiles));
            round.setRoundID(roundNumber);
            rounds.add(round);
        }
        return rounds;
    }

    public void notifyComplete(){
            for(TournamentPlayer tournamentPlayer: players){
                PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
                printWriter.println("END OF CHALLENGES");
            }
            tournamentServer.notifyChallengeComplete();
        }


    public int getChallengeID() {
        return challengeID;
    }

    public List<TournamentPlayer> getPlayers() {
        return players;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }
}
