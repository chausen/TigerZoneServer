package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Jeff on 2016/11/14.
 */
public class CMDPromptPort extends IOPort {
    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     *
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed       Seed value for randomization of TileStack inside GameSystem.
     */
    public CMDPromptPort(String loginName1, String loginName2, long seed) {
        super(loginName1, loginName2, seed);
    }

    @Override
    public void sendTurn(Turn lastturn, AreaTile areatile) {

    }

    @Override
    public void sendTilesInOrder(LinkedList<PlayableTile> allAreaTiles) {

    }

    @Override
    public void notifyBeginGame(AreaTile areatile) {

    }

    @Override
    public void notifyEndGame(Set<Integer> winners) {

    }
}
