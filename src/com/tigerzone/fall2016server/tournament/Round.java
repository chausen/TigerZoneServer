package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Round {
    List<TournamentPlayer> players;
    LinkedList<PlayableTile> tileStack;
    private Challenge challenge;
    private static int roundID = 0;
    private int rid;
    private int numOfMatches;
    private int numOfMatchesComplete = 0;
    private int currentRound = 0;
    private int numOfRounds;

    List<Match> matches;

    /**
     * NOTE: players size should be even
     * @param players
     * @param tileStack
     */
    public Round(List<TournamentPlayer> players, LinkedList<PlayableTile> tileStack){
        rid = roundID++;
        this.players = players;
        this.tileStack = tileStack;
        numOfMatches = players.size()/2;
        if(players.size()/2 % 2 == 0){
            numOfRounds = players.size() - 1;
        }
        else {
            numOfRounds = players.size();
        }
    }

    public Round(List<Match> matches) {
        this.matches = matches;
    }

    public void playRound() {
        for (Match match: matches) {
            match.playMatch();
        }
    }

    /**
     * Generates all Matches for the Round
     * @return
     */
    List<Match> generateMatches(){
        List<Match> matches = new ArrayList<>();
        for(int i = 0; i < players.size() - 1; i = i + 2){
            TournamentPlayer p1 = this.players.get(i);
            TournamentPlayer p2 = this.players.get(i + 1);
            Match match = new Match(p1, p2, this.tileStack);
            matches.add(match);
        }
        return matches;
    }

    private List<Match> makeMatches(){
        List<Match> matchList =  RoundRobin.listMatches(players,currentRound,tileStack);
        currentRound++;
        return matchList;
    }

    private void sendMessageToPlayers(){
        for(TournamentPlayer tournamentPlayer: players){
            PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
            printWriter.println("BEGIN ROUND " + rid + " OF " + numOfRounds);
        }
    }

    /**
     * This method starts the current Round.
     */
    public void startMatches() {
        sendMessageToPlayers();
        List<Match> matches = makeMatches();
        for(Match match : matches){
            match.startGames();
        }
    }

    public void notifyComplete(){
        numOfMatchesComplete++;
        if(numOfMatchesComplete == numOfMatches){
            for(TournamentPlayer tournamentPlayer: players){
                PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
                printWriter.println("END OF ROUND " + rid + " OF " + numOfRounds);
            }
            challenge.notifyComplete();
        }
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public int getRoundID() {
        return roundID;
    }
}
