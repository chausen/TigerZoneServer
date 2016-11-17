package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface PlayerOutAdapter
{
    public void sendTurnInitial(String currentPlayer, PlayableTile currentTile);
    public void sendTurn(String turnString);
    public void receiveTurn(String s);
    public void notifyBeginGame(List<PlayableTile> allTiles);
    public void notifyEndGame(Map<String, Integer> playerScores);
    public void forfeitInvalidMeeple(String currentPlayerID);
    public void forfeitIllegalMeeple(String currentPlayerID);
    public void forfeitIllegalTile(String currentPlayerID);
}