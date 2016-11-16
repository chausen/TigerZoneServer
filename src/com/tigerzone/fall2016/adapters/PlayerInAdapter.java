package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;


public interface PlayerInAdapter
{
    public void receiveTurn(Turn t);
    public void initializeGame(String player1id, String player2id, long seed);
    public void setOutAdapter(PlayerOutAdapter outAdapter);
    public boolean isTilePlaceable(PlayableTile playableTile);
    public void triggerSendTurn();
}