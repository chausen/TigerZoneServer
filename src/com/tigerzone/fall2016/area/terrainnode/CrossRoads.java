package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;

import java.util.List;

/**
 * Created by Aidan on 11/15/2016.
 */
public class CrossRoads extends TerrainNode {

    public CrossRoads(List<Integer> zoneList){
        setZones(zoneList);
    }

    @Override
    public Area createArea() {
        return null;
    }
}
