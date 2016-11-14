package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;

/**
 * Created by Jeff on 2016/11/13.
 */
public class NetworkPort implements PlayerOutAdapter {
    @Override
    public void passTurn(Turn lastturn, AreaTile areatile) {

    }

    @Override
    public void notifyBeginGame(AreaTile areatile) {
        System.out.println(areatile);
        System.out.println(areatile);
    }

    @Override
    public void notifyEndGame() {

    }
}
