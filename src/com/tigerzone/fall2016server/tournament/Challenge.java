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
    List<Round> rounds;
    Round currentRound;
    int currentRoundNumber;
    Set<TournamentPlayer> forfeitPlayerSet;

    public Challenge(TournamentServer tournamentServers, long seed, List<TournamentPlayer> players) {
        cid = challengeID++;
        this.tournamentServer = tournamentServers;


        this.tiles = TileStackGenerator.generateTiles(seed); // TODO: 11/27/2016 Edit the seed to test different tile sets
        
        if(players.size() % 2 == 0){
            numOfRounds = players.size() - 1;
        }
        else {
            numOfRounds = players.size();
        }
        this.players = players;
        this.forfeitPlayerSet = new HashSet<>();
    }

    public void beginChallenge() {
        currentRoundNumber=1;
        sendNewChallengeMessageToPlayers();
        Logger.beginChallenge(1,challengeID);
        rounds = generateRounds();
        rounds.get(currentRoundNumber-1).playRound();
    }

    public void roundComplete() {
        if (currentRoundNumber==numOfRounds) {
            sendEndMessage();
            tournamentServer.notifyChallengeComplete();
        } else {
            currentRoundNumber++;
            currentRound = rounds.get(currentRoundNumber-1);
            currentRound.playRound();
        }
    }

    private void sendEndMessage(){
        Logger.endChallenge(getTournamentID(),challengeID);
        if(tournamentServer.getNumOfChallenges() == tournamentServer.getNumOfChallengesComplete()) {
            Logger.createEndStats(players);//Create the log file of all teams that participated, and their wins, losses, etc. that Dave requested.
        }
        for(TournamentPlayer tournamentPlayer: players){
            if(tournamentServer.getNumOfChallenges() == tournamentServer.getNumOfChallengesComplete()){

                tournamentPlayer.sendMessageToPlayer("END OF CHALLENGES");
                System.out.println("end of challenges");
            }
            else {
                tournamentPlayer.sendMessageToPlayer("PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN");
                System.out.println("still more challenges");
            }
            //tournamentPlayer.sendMessageToPlayer("PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN");
        }
    }

    private void sendNewChallengeMessageToPlayers(){
        for(TournamentPlayer tournamentPlayer: players){
            String message = "NEW CHALLENGE " + (cid + 1)  + " YOU WILL PLAY " + numOfRounds;
            if(numOfRounds == 1) {
                tournamentPlayer.sendMessageToPlayer(message + " MATCH");
            }
            else {
                tournamentPlayer.sendMessageToPlayer(message + " MATCHES");
            }
        }
    }

    public List<Round> generateRounds(){
        List<Round> rounds = new ArrayList<>();
        Round round;
        for (int roundNumber = 1; roundNumber <= numOfRounds; roundNumber++) {
            round = new Round(this, roundNumber);
            rounds.add(round);
        }
        return rounds;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public Set<TournamentPlayer> getForfeitedPlayerSet(){
        return this.forfeitPlayerSet;
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

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public int getTournamentID() {
        return tournamentServer.getTournamentID();
    }

    public void addForfeitPlayers(Set<TournamentPlayer> forfeitedPlayersInRound) {
        this.forfeitPlayerSet.addAll(forfeitedPlayersInRound);
    }
}
