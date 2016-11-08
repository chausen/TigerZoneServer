package com.tigerzone.fall2016.tileplacement.tile.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class FreeTerain extends Terrain {

    @Override
    public boolean accept(TerrainVisitor terrainVisitor) {
        return terrainVisitor.visit(this);
    }

    @Override
    public boolean visit(CityTerrain cityTerrain) {
        return true;
    }

    @Override
    public boolean visit(FarmTerrain farmTerrain) {
        return true;
    }

    @Override
    public boolean visit(RoadTerrain roadTerrain) {
        return true;
    }

    @Override
    public boolean visit(CloisterTerrain cloisterTerrainTerrain) {
        return true;
    }

    @Override
    public boolean visit(FreeTerain freeTerain) {
        return true;
    }

    @Override
    public boolean isFree() {
        return true;
    }
}
