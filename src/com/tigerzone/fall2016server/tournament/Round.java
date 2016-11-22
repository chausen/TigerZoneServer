package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

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

    /**
     * NOTE: players size should be even
     * @param players
     * @param tileStack
     */
    public Round(List<TournamentPlayer> players, LinkedList<PlayableTile> tileStack){
        this.players = players;
        this.tileStack = tileStack;
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

    /**
     * This method starts the current Round.
     */
    public void startRound() {
        List<Match> matches = generateMatches();
        for(Match match : matches){
            match.startMatch();
        }
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public int getRoundID() {
        return roundID;
    }
}
