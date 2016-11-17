package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface PlayerOutAdapter
{
    public void notifyBeginGame(List<PlayableTile> allTiles);
    //TODO: Do we need to output this every turn, or just before the first move of the game? Delete if not.
    // public void sendTurnInitial(String currentPlayer, PlayableTile currentTile);
    public void receiveTurn(String s);
    public void successfulTurn();
    public void forfeitInvalidMeeple(String currentPlayerID);
    public void forfeitIllegalMeeple(String currentPlayerID);
    public void forfeitIllegalTile(String currentPlayerID);
    public void notifyEndGame(Map<String, Integer> playerScores);
}