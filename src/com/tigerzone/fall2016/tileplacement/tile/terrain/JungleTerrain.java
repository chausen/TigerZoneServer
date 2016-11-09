package com.tigerzone.fall2016.tileplacement.tile.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class JungleTerrain extends Terrain {

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
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
    public boolean visit(FreeTerain freeTerain) {
        return true;
    }
}
