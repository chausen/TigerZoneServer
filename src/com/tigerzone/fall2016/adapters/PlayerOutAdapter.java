package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface PlayerOutAdapter
{
    public void promptForTurn(PlayableTile currentTile);
    public void receiveTurn(String s);
    public void receiveIllegalMessage();
    public void successfulTurn();
    public void reportScoringEvent(Map<Player,Integer> playerScores);
    public void forfeitInvalidMeeple(String currentPlayerID);
    public void forfeitIllegalMeeple(String currentPlayerID);
    public void forfeitIllegalTile(String currentPlayerID);
    //public void notifyEndGame(Map<Player,Integer> playerScores);
    public void notifyEndGame(int score1, int score2);
    public int getFinalScore(String playerId);
}