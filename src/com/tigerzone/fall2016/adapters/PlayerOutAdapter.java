package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.Set;

public interface PlayerOutAdapter
{
    public void sendTurn();
    public void receiveTurn(String s);
    public void sendTilesInOrder(LinkedList<PlayableTile> allAreaTiles);
    public void notifyBeginGame(PlayableTile playableTile);
    public void notifyEndGame(Set<String> winnerIDs);
    public void forfeitInvalidMeeple(int winnerID);
    public void forfeitIllegalMeeple(int winnerID);
    public void forfeitIllegalTile(int winnerID);

}