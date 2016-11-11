package com.tigerzone.fall2016.tileplacement.terrain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Aidan on 11/10/2016.
 */
public class SegmentAdder implements SegmentVisitor{

    HashMap<Integer, LakeTerrain> lakeTerrains = new HashMap<>();
    HashMap<Integer, DenTerrain> denTerrains = new HashMap<>();;
    HashMap<Integer, JungleTerrain> jungleTerrains = new HashMap<>();;
    HashMap<Integer, TrailTerrain> trailTerrains = new HashMap<>();;

    @Override
    public void visit(LakeTerrain lakeTerrain, int i) {
        lakeTerrains.put(i, lakeTerrain);
    }

    @Override
    public void visit(TrailTerrain trailTerrain, int i) {

    }

    @Override
    public void visit(JungleTerrain jungleTerrain, int i) {

    }

    @Override
    public void visit(DenTerrain denTerrain, int i) {

    }

}
