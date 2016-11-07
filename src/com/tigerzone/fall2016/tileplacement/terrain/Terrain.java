package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public abstract class Terrain implements TerrainVisitor {

    public abstract void accept(TerrainVisitor terrainVisitor);

}
