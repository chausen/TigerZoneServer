package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class JungleTerrain extends Terrain implements AdjacentZoneVisitor {

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

    @Override
    public Terrain checkAdjacent(LakeTerrain lakeTerrain) {
        return this;
    }

    @Override
    public Terrain checkAjacent(JungleTerrain jungleTerrain) {
        jungleTerrain=this;
        return jungleTerrain;
    }

    @Override
    public Terrain checkAdjacent(TrailTerrain trailTerrain) {
        return this;
    }
}
