package com.tigerzone.fall2016.tileplacement.terrain;

import java.util.HashMap;

/**
 * Created by Aidan on 11/11/2016.
 */
public class SegmentAdder implements SegmentVisitor{

    private HashMap<Integer, DenTerrain> denTerrains;
    private HashMap<Integer, JungleTerrain> jungleTerrains;
    private HashMap<Integer, LakeTerrain> lakeTerrains;
    private HashMap<Integer, TrailTerrain> trailTerrains;
    int count = 0;

    @Override
    public void visitDen(DenTerrain denTerrain) {
        denTerrains.put(count++, denTerrain);
    }

    @Override
    public void visitFree(FreeTerrain freeTerrain) {
    }

    @Override
    public void visitLake(LakeTerrain lakeTerrain) {
        lakeTerrains.put(count++, lakeTerrain);
    }

    @Override
    public void visitTrail(TrailTerrain trailTerrain) {
        denTerrains.put(count++, denTerrain);
    }

    @Override
    public void visitTrail(TrailTerrain trailTerrain) {
        denTerrains.put(count++, denTerrain);
    }
}
