package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match {

    private Game game1;
    private Game game2;

    Player player1;
    Player player2;

    public Match(Player player1, Player player2, long seed) {
        game1 = new Game(player1, player2, seed);
        game2 = new Game(player2, player1, seed);
    }

    public void startMatch() {
        game1.playGame();
        game2.playGame();
    }
}
