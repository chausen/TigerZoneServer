package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.Set;

public interface PlayerOutAdapter
{
    public void sendTurn(Turn lastturn, AreaTile areatile);
    public void receiveTurn(String s);
    public void sendTilesInOrder(LinkedList<PlayableTile> allAreaTiles);
    public void notifyBeginGame(AreaTile areatile);
    public void notifyEndGame(Set<Integer> winners);
}