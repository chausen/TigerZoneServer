package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match {
    private Game game1;
    private Game game2;

    public Match(Player player1, Player player2, LinkedList<PlayableTile> tileStack) {
        game1 = new Game(0, player1.getPlayerId(), player2.getPlayerId(), tileStack);
        game2 = new Game(1, player2.getPlayerId(), player1.getPlayerId(), tileStack);
    }

    public void startMatch() {
        game1.start();
        game2.start();
    }
}
