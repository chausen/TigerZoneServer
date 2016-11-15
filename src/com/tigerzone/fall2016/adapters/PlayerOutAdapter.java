package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

public interface PlayerOutAdapter
{
    public void passTurn(Turn lastturn, PlayableTile playableTile);
    public void notifyBeginGame(PlayableTile playableTile);
    public void notifyEndGame();
}