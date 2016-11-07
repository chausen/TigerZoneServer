package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public interface TerrainVisitor {

    public void visit(CityTerrain cityTerrain);
    public void visit(FarmTerrain farmTerrain);
    public void visit(RoadTerrain roadTerrain);
    public void visit(CloisterTerrain cloisterTerrainTerrain);

}
