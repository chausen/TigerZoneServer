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
//        populateFaceZones(playableTile);
//        zonesSpecialCase(playableTile.getTileString());
//        if (zonesSpecialCase(playableTile.getTileString())) {
//            return;
//        } else {
//            //populateCornerZones(playableTile);
//        }
        populateZones(playableTile);
    }

    public Terrain getZone(int zone) {
        int zoneTranslation = zone-1;
        return zones[zone];
    }

    public void setZone(int zone, Terrain terrain) {
        int zoneTranslation = zone-1;
        zones[zoneTranslation] = terrain;
    }

    private void populateZones(PlayableTile playableTile) {
        setZone(2, playableTile.getNorthFace());
        setZone(6, playableTile.getEastFace());
        setZone(8, playableTile.getSouthFace());
        setZone(4, playableTile.getWestFace());
    }


    public Terrain getNorthFace(){
        return zones[1];
    }

    public Terrain getEastFace(){
        return zones[5];

    }

    public Terrain getSouthFace(){
        return zones[7];
    }

    public Terrain getWestFace(){
        return zones[3];
    }

    //zones 2,6,4,8

}
