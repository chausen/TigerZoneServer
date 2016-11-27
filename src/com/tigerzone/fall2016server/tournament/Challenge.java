package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.server.TournamentServer;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

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
    private int numOfRoundsComplete = 0;
    List<Round> rounds;
    Round currentRound;
    int currentRoundNumber;
    //Queue<Round> rounds;


    public Challenge(TournamentServer tournamentServers, long seed, List<TournamentPlayer> players) {

        cid = challengeID++;
        this.tournamentServer = tournamentServers;
        this.tiles = TileStackGenerator.generateTiles(seed);
        if(players.size() % 2 == 0){
            numOfRounds = players.size() - 1;
        }
        else {
            numOfRounds = players.size();
        }
        this.players = players;
    }

    public void beginChallenge() {
        currentRoundNumber=1;
        sendMessageToPlayers();
        Logger.beginChallenge(1,challengeID);
        rounds = generateRounds();
        rounds.get(currentRoundNumber-1).playRound();
    }

    public void roundComplete() {
        currentRoundNumber++;
        if (currentRoundNumber==numOfRounds) {
            tournamentServer.notifyChallengeComplete();
        } else {
            currentRound = rounds.get(currentRoundNumber-1);
            currentRound.playRound();
        }
    }

    private void sendMessageToPlayers(){
        for(TournamentPlayer tournamentPlayer: players){
            String message = "NEW CHALLENGE " + cid + " YOU WILL PLAY " + numOfRounds;
            if(numOfRounds == 1) {
                tournamentPlayer.sendMessageToPlayer(message + " MATCH");
            }
            else {
                tournamentPlayer.sendMessageToPlayer(message + " MATCHES");
            }
        }
    }

    //erik generateRounds
    public List<Round> generateRounds(){
        List<Round> rounds = new ArrayList<>();
        Round round;
        for (int roundNumber = 1; roundNumber <= numOfRounds; roundNumber++) {
            round = new Round(this, roundNumber);
            rounds.add(round);
        }
        return rounds;
    }

    //erik generateRounds
    public Queue<Round> generateRounds2(){
        Queue<Round> rounds = new LinkedList<>();
        Round round;
        for (int roundNumber = 1; roundNumber <= numOfRounds; roundNumber++) {
            round = new Round(this, RoundRobin.listMatches(players, roundNumber, tiles));
            round.setRoundID(roundNumber);
            rounds.add(round);
        }
        return rounds;
    }

//    public void notifyComplete(){
//        numOfRoundsComplete++;
//        if(numOfRoundsComplete == numOfRounds) {
//            for (TournamentPlayer tournamentPlayer : players) {
//                tournamentPlayer.sendMessageToPlayer("END OF CHALLENGES");
//            }
//            tournamentServer.notifyChallengeComplete();
//        }
//    }



    public int getChallengeID() {
        return challengeID;
    }

    public List<TournamentPlayer> getPlayers() {
        return players;
    }

    public LinkedList<PlayableTile> getTiles() {
        return tiles;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public int getTournamentID() {
        return tournamentServer.getTournamentID();
    }
}
