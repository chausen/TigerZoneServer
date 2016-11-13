package com.tigerzone.fall2016.tileplacement.terrain;

import com.tigerzone.fall2016.tileplacement.Direction;

/**
 * Created by Aidan on 11/7/2016.
 */
public class JungleTerrain extends Terrain {

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
    }

    @Override
<<<<<<< HEAD
    public void accept(SegmentVisitor segmentVisitor, Direction direction) {
        segmentVisitor.visitJungle(this, direction);
=======
    public void accept(SegmentVisitor segmentVisitor) {
        segmentVisitor.visitJungle(this);
>>>>>>> 9151f4f99e54e59172a7942c7093ac3823736a31
    }

    @Override
    public boolean visit(LakeTerrain lakeTerrain) {
        return false;
    }

    @Override
    public boolean visit(JungleTerrain jungleTerrain) {
        return true;
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
}
