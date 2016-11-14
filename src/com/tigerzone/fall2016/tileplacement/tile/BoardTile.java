package com.tigerzone.fall2016.tileplacement.tile;

import com.sun.javafx.geom.Point2D;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/13/2016.
 */
public class BoardTile {

    private HashMap<Terrain, TileArea> tileArea = new HashMap<>(); //this is simple, but probably stupid.
    private List<TileArea> tileAreas; //instead, can just loop through tileareas to see if contains

    public TileArea getTileAreaFromTerrain(Terrain terrain) {
        return tileArea.get(terrain);
    }

    private Terrain[] zones = new Terrain[9];

    public BoardTile(Terrain[] zones) {
        this.zones = zones;
    }

    public BoardTile(PlayableTile playableTile) {
        populateFaceZones(playableTile);
        zonesSpecialCase(playableTile.getTileString());
        if (zonesSpecialCase(playableTile.getTileString())) {
            return;
        } else {
            //populateCornerZones(playableTile);
        }

    }

    public Terrain getZone(int zone) {
        int zoneTranslation = zone-1;
        return zones[zone];
    }

    public void setZone(int zone, Terrain terrain) {
        int zoneTranslation = zone-1;
        zones[zoneTranslation] = terrain;
    }

    private void populateFaceZones(PlayableTile playableTile) {
        setZone(4, playableTile.getNorthFace());
        setZone(6, playableTile.getEastFace());
        setZone(8, playableTile.getSouthFace());
        setZone(2, playableTile.getWestFace());
    }

//    private void populateCornerZones(PlayableTile playableTile) {
//        setZone(3, getZone(2).checkAdjacent(getZone(6)));
//        setZone(9, getZone(6).checkAdjacent(getZone(8)));
//        setZone(7, getZone(8).checkAdjacent(getZone(4)));
//        setZone(1, getZone(4).checkAdjacent(getZone(2)));
//
//    }

    private boolean zonesSpecialCase(String specialCases) {
        switch (specialCases) {
            case "JLJL-":
                return true;

            case "LJLJ-":

                return true;

            case "JLLJ-":

                return true;

        }
        return false;
    }


    //zones 2,6,4,8

}
