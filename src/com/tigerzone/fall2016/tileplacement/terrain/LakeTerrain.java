package com.tigerzone.fall2016.tileplacement.terrain;

import com.tigerzone.fall2016.tileplacement.Direction;

/**
 * Created by Aidan on 11/7/2016.
 */
public class LakeTerrain extends Terrain implements AdjacentZoneVisitor {

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
    }

    @Override
    public void accept(SegmentVisitor segmentVisitor, Direction direction) {
        segmentVisitor.visitLake(this, direction);
    }

    @Override
    public boolean visit(LakeTerrain lakeTerrain) {
        return true;
    }

    @Override
    public boolean visit(JungleTerrain jungleTerrain) {
        return false;
    }

    @Override
    public boolean visit(TrailTerrain trailTerrain) {
        return false;
    }

    @Override
    public boolean visit(DenTerrain denTerrainTerrain) {
        return false;
    }

    @Override
    public boolean visit(FreeTerrain freeTerrain) {
        return true;
    }

    @Override
    public Terrain checkAdjacent(LakeTerrain lakeTerrain) {
      return lakeTerrain; // TODO: 11/13/2016 need to handle special case of
    }

    @Override
    public Terrain checkAjacent(JungleTerrain jungleTerrain) {
        return jungleTerrain;
    }

    @Override
    public Terrain checkAdjacent(TrailTerrain trailTerrain) {
        return new JungleTerrain();
    }
}
