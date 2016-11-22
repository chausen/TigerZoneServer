package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.TournamentServer;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    private boolean challengeComplete = false;

    public Challenge(TournamentServer tournamentServers, String[] tiles, long seed, List<TournamentPlayer> players) {
        cid = challengeID++;
        this.tournamentServer = tournamentServers;
        this.tiles = TileStackGenerator.generateTiles(tiles, seed);
        if(players.size()/2 % 2 == 0){
            numOfRounds = players.size() - 1;
        }
        else {
            numOfRounds = players.size();
        }
        this.players = players;
    }

    private void sendMessageToPlayers(){
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


    public int getPlayerCount() {
        int playerCount = players.size();
        return playerCount;
    }


    public void startRound(){
        sendMessageToPlayers();
        Round round = new Round(players, tiles);
        round.startMatches();
    }

    public void notifyComplete(){
        numOfRoundsComplete++;
        if(numOfRounds == numOfRoundsComplete){
            for(TournamentPlayer tournamentPlayer: players){
                PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
                printWriter.println("END OF CHALLENGES");
            }
            tournamentServer.notifyChallengeComplete();
        }
        else{
            startRound();
        }
    }

    public int getChallengeID() {
        return challengeID;
    }
}
