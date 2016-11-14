package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import sun.nio.ch.Net;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jeff on 2016/11/13.
 */
public class NetworkPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;

    private int seed; // seed corresponding to the tile order
    private Map<Integer, String> players; // key: playerID, value: loginName
    private String loginName1;
    private String loginName2;

    public NetworkPort(PlayerInAdapter inAdapter) {
        //TODO: make seed random?
        this.seed = 1234567890;
        this.inAdapter = new GameSystem(seed);
    }

    @Override
    public void passTurn(Turn lastturn, AreaTile areatile) {

    }

    @Override
    public void getTilesInOrder(List<AreaTile> allAreaTiles) {

    }

    @Override
    public void notifyBeginGame(AreaTile areatile) {
        System.out.println(areatile);
        System.out.println(areatile);
    }

    @Override
    public void notifyEndGame(Set<Integer> winners) {
        // Message declaring winning player or tie
    }

    public int getSeed() {
        return seed;
    }
}
