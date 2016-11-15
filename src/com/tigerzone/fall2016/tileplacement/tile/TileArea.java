package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/12/2016.
 */
public class TileArea {

    private List<Terrain> tileAreaTerrains; //given a terrain, should be able to get the tileArea
    private HashMap<Terrain, Tile> tileAreaMap;

    private Terrain terrain;
    private PlayableTile tile;

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    public PlayableTile getTile() {
        return tile;
    }
    public void setTile(PlayableTile tile) {
        this.tile = tile;
    }

    public TileArea(Terrain terrain, Tile tile) {
        tileAreaMap.put(terrain,tile);


    }


    public boolean containsTerrain(Terrain terrain) { //this should be all that is needed to get a tilearea given a terrain,
        //because can loop through tileareas to check if contains specific terrain in the
        Boolean contains = tileAreaTerrains.contains(terrain) ? true:false;
        return contains;
    }



    //Would like to know what tileArea a terrain belongs to
}
