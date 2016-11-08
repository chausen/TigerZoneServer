package com.tigerzone.fall2016.tileplacement.tile.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class RoadTerrain extends Terrain{

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
    }

    @Override
    public boolean visit(CityTerrain cityTerrain) {
        return false;
    }

    @Override
    public boolean visit(FarmTerrain farmTerrain) {
        return false;
    }

    @Override
    public boolean visit(RoadTerrain roadTerrain) {
        return true;
    }

    @Override
    public boolean visit(CloisterTerrain cloisterTerrainTerrain) {
        return false;
    }

    @Override
    public boolean visit(FreeTerain freeTerain) {
        return true;
    }
}
