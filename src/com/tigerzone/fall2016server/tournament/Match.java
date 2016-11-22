package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match {
    private Round round;
    private int matchID;
    private Game game1;
    private Game game2;

    public Match(TournamentPlayer player1,TournamentPlayer player2, LinkedList<PlayableTile> tileStack) {
        game1 = new Game(1, player1, player2, tileStack);
        game2 = new Game(2, player2, player1, tileStack);
    }

    public void startMatch() {
        game1.start();
        game2.start();
    }

    public Round getRound() {
        return round;
    }

    public int getMatchID() {
        return matchID;
    }
}
