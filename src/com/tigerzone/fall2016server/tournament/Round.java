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
    private int roundID;
    private int numOfMatches;
    private int numOfMatchesComplete = 0;
    private int currentRound = 0;
    private int numOfRounds;

    List<Match> matches;


    public Round(List<Match> matches) {
        this.matches = matches;
        this.numOfMatches = matches.size();
    }


    public Round(Challenge challenge, List<Match> matches) {
        this.challenge = challenge;
        this.matches = matches;
        this.numOfMatches = matches.size();
        getChallengeInfo();
    }

    public void playRound() {
        sendMessageToPlayers();
        for (Match match: matches) {
            match.playMatch();
        }
        //notifyComplete();
    }

    public void getChallengeInfo() {
        this.players = challenge.getPlayers();
        this.numOfRounds = challenge.getNumOfRounds();
    }

    private void sendMessageToPlayers(){
        for(TournamentPlayer tournamentPlayer: players){
            PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
            printWriter.println("BEGIN ROUND " + roundID + " OF " + numOfRounds);
        }
    }

    public void notifyComplete() {
        numOfMatchesComplete++;
        if(numOfMatchesComplete == numOfMatches) {
            for (TournamentPlayer tournamentPlayer : players) {
                PrintWriter printWriter = tournamentPlayer.getConnection().getOut();
                printWriter.println("END OF ROUND " + roundID + " OF " + numOfRounds);
            }
            challenge.notifyComplete();
        }
    }

    public void setPlayers(List<TournamentPlayer> players) {
        this.players = players;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    public void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    public int getRoundID() {
        return roundID;
    }
}
