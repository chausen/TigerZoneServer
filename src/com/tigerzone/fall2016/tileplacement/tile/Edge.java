package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class Edge {

    Terrain leftTerrain;
    Terrain middleTerrain;
    Terrain rightTerrain;

    public Edge(Terrain leftTerrain, Terrain middleTerrain, Terrain rightTerrain) {
        this.leftTerrain = leftTerrain;
        this.middleTerrain = middleTerrain;
        this.rightTerrain = rightTerrain;
    }

    public Terrain getLeftTerrain() {
        return leftTerrain;
    }

    public Terrain getMiddleTerrain() {
        return middleTerrain;
    }

    public Terrain getRightTerrain() {
        return rightTerrain;
    }

}
