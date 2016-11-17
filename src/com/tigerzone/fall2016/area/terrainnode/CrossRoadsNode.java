package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.CrossRoadsArea;

import java.util.List;

/**
 * Created by Aidan on 11/15/2016.
 */
public class CrossRoadsNode extends TerrainNode {

    public CrossRoadsNode(List<Integer> zoneList){
        setZones(zoneList);
    }

    public Area getArea() {
        return null;
    }

    @Override
    public Area createArea() {
        return new CrossRoadsArea();
    }
}
