package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;

/**
 * Created by Aidan on 11/15/2016.
 */
public class JungleTerrainNode extends TerrainNode {

    public JungleTerrainNode(){
        this.terrain = new JungleTerrain();
    }

    @Override
    public Area createArea() {
        return new JungleArea();
    }
}
