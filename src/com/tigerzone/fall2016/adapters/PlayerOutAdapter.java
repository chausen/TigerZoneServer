package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface PlayerOutAdapter
{
    public void sendTurn();
    public void receiveTurn(String s);
    public void notifyBeginGame(List<PlayableTile> allTiles);
    public void notifyEndGame(Set<String> winnerIDs);
    public void forfeitInvalidMeeple(String winnerID);
    public void forfeitIllegalMeeple(String winnerID);
    public void forfeitIllegalTile(String winnerID);
    public void unplaceableTile();
}