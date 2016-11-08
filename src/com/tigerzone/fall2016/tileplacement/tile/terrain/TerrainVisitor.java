package com.tigerzone.fall2016.tileplacement.tile.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public interface TerrainVisitor {

    public boolean visit(CityTerrain cityTerrain);
    public boolean visit(FarmTerrain farmTerrain);
    public boolean visit(RoadTerrain roadTerrain);
    public boolean visit(CloisterTerrain cloisterTerrainTerrain);
    public boolean visit(FreeTerain freeTerain);

}
