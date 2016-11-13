package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public interface TerrainVisitor {

    public boolean visit(LakeTerrain lakeTerrain);
    public boolean visit(JungleTerrain jungleTerrain);
    public boolean visit(TrailTerrain trailTerrain);
    public boolean visit(DenTerrain denTerrainTerrain);
    public boolean visit(FreeTerrain freeTerrain);

}
