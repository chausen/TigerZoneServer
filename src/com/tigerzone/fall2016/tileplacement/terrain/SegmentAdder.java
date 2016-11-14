package com.tigerzone.fall2016.tileplacement.terrain;

import com.tigerzone.fall2016.tileplacement.Direction;

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
    public void visitJungle(JungleTerrain jungleTerrain, Direction direction) {
        jungleTerrains.put(count++, jungleTerrain);
    }

    @Override
    public void visitDen(DenTerrain denTerrain, Direction direction) {
        denTerrains.put(count++, denTerrain);
    }

    @Override
    public void visitFree(FreeTerrain freeTerrain, Direction direction) {

    }

    @Override
    public void visitLake(LakeTerrain lakeTerrain, Direction direction) {
        lakeTerrains.put(count++, lakeTerrain);
    }

    public void visitTrail(TrailTerrain trailTerrain, Direction direction) {
        trailTerrains.put(count++, trailTerrain);
    }

    public void createJungleAreas(){

    }


}
