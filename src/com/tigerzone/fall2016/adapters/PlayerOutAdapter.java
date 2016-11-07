package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;

public interface PlayerOutAdapter
{
    public void passTurn(Turn lastturn, AreaTile areatile);
    public void notifyBeginGame(AreaTile areatile);
    public void notifyEndGame();
}