package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Challenge {
    LinkedList<PlayableTile> tiles;
    Set<TournamentPlayer> players;
    private int challengeID;

    public Challenge(String[] tiles, long seed) {
        this.tiles = TileStackGenerator.generateTiles(tiles, seed);
    }


    public int getPlayerCount() {
        int playerCount = players.size();
        return playerCount;
    }

    public int getChallengeID() {
        return challengeID;
    }
}
