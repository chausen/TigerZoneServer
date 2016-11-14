
package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

/**
 * Created by Aidan on 11/7/2016.
 */

public class AreaTile extends Tile {

    public AreaTile(Edge northEdge, Edge eastEdge, Edge southEdge, Edge westEdge, Terrain center) {
        super(northEdge,eastEdge,southEdge,westEdge,center);
    }

}