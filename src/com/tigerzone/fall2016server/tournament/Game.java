package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Game extends Thread{

    Player player1;
    Player player2;
    LinkedList<PlayableTile> tileStack;

    public Game(Player player1, Player player2, LinkedList<PlayableTile> tileStack) {
        this.player1 = player1;
        this.player2 = player2;
        this.tileStack = tileStack;

    }

    @Override
    public void run(){
        playGame();
    }

    void playGame() {
        GameSystem gameSystem = new GameSystem();
        gameSystem.initializeGame(player1.getPlayerId(), player2.getPlayerId(), tileStack);
        //initialize game is called in the gameSystem constructor
        //gameSystem.startGame(); //startGame() is called in initializeGame(), which is called in the constructor
    }
}
