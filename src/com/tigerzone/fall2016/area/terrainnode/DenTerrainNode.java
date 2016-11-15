package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.DenArea;
import com.tigerzone.fall2016.tileplacement.terrain.DenTerrain;

/**
 * Created by Aidan on 11/15/2016.
 */
public class DenTerrainNode extends TerrainNode {

    public DenTerrainNode(){
        this.terrain = new DenTerrain();
    }

    @Override
    public Area createArea() {
        return new DenArea();
    }
}
