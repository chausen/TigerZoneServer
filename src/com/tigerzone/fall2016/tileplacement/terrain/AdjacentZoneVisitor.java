package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by lenovo on 11/13/2016.
 */
interface AdjacentZoneVisitor {

    Terrain checkAdjacent(LakeTerrain lakeTerrain);
    Terrain checkAjacent(JungleTerrain jungleTerrain);
    Terrain checkAdjacent(TrailTerrain trailTerrain);


}
