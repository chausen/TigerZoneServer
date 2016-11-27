package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.ArrayList;
import java.util.LinkedList;


public interface PlayerInAdapter
{
    public void initializeGame(String player1id, String player2id, LinkedList<PlayableTile> playableTiles);
    public void setOutAdapter(PlayerOutAdapter outAdapter);
    public void receiveTurn(Turn t);
    public void receivePass();
    public void tigerRetrieve(int x, int y);
    public void tigerPlace(int x, int y);
    public void truncateTS(int x);//ONLY USED FOR TESTING PURPOSES
    public Player getPlayer(String playerID);
    public void forfeit(); // used by the outAdapter in the case where an illegal message is received
    public String getCurrentTile();
}