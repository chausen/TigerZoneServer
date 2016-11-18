package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Game {

    Player player1;
    Player player2;
    long seed;

    public Game(Player player1, Player player2, long seed) {
        this.player1 = player1;
        this.player2 = player2;
        this.seed = seed;
    }

    public void playGame() {
//        GameSystem gameSystem = new GameSystem(1, player1.getOwner(), player2.getOwner(), this.seed);

        //initialize game is called in the gameSystem constructor
        //gameSystem.startGame(); //startGame() is called in initializeGame(), which is called in the constructor
    }
}
