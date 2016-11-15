package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;

public interface PlayerInAdapter {
    public void acceptTurn(Turn t);
    public void startGame(int player1id, int player2id);
    public void initializeGame();
}