package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;


/**
 * Created by Aidan on 11/7/2016.
 */
public class FreeSpace {

    Terrain northTerrain;
    Terrain eastTerrain;
    Terrain southTerrain;
    Terrain westTerrain;

    public FreeSpace(Terrain northTerrain, Terrain eastTerrain, Terrain southTerrain, Terrain westTerrain) {
        this.northTerrain = northTerrain;
        this.eastTerrain = eastTerrain;
        this.southTerrain = southTerrain;
        this.westTerrain = westTerrain;
    }

    public Terrain getNorthTerrain() {
        return northTerrain;
    }

    public void setNorthTerrain(Terrain northTerrain) {
        this.northTerrain = northTerrain;
    }

    public Terrain getEastTerrain() {
        return eastTerrain;
    }

    public void setEastTerrain(Terrain eastTerrain) {
        this.eastTerrain = eastTerrain;
    }

    public Terrain getSouthTerrain() {
        return southTerrain;
    }

    public void setSouthTerrain(Terrain southTerrain) {
        this.southTerrain = southTerrain;
    }

    public Terrain getWestTerrain() {
        return westTerrain;
    }

    public void setWestTerrain(Terrain westTerrain) {
        this.westTerrain = westTerrain;
    }
}
