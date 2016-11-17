package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.area.LakeArea;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;

import java.util.List;

/**
 * Created by Aidan on 11/15/2016.
 */
public class LakeTerrainNode extends TerrainNode {

    public LakeTerrainNode(List<Integer> canConnectTo, List<Integer> zones) {
        super(canConnectTo, zones);
        setArea(createArea());
    }
    
    @Override
    public Area createArea() {
        LakeArea lakeArea = new LakeArea();
        lakeArea.addTerrainNode(this);
        return lakeArea;
    }
}
