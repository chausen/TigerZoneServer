package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.DenArea;
import com.tigerzone.fall2016.tileplacement.terrain.DenTerrain;

import java.util.List;

/**
 * Created by Aidan on 11/15/2016.
 */
public class DenTerrainNode extends TerrainNode {

    public DenTerrainNode() {
        setArea(createArea());
    }

    @Override
    public Area createArea() { //this was returning null and we were trying to get area's
        DenArea denArea = new DenArea();
        denArea.addTerrainNode(this);
        return denArea;
    }


}
