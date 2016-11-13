package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.Animal;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;

/**
 * Created by lenovo on 11/13/2016.
 */
public class PlayableTile {

    private String tileString;
    private Terrain northEdge;
    private Terrain eastEdge;
    private Terrain southEdge;
    private Terrain westEdge;
    private Animal animal;

    public String getTileString() {
        return tileString;
    }
    public void setTileString(String tileString) {
        this.tileString = tileString;
    }
    public Terrain getNorthEdge() {
        return northEdge;
    }
    public void setNorthEdge(Terrain northEdge) {
        this.northEdge = northEdge;
    }
    public Terrain getEastEdge() {
        return eastEdge;
    }
    public void setEastEdge(Terrain eastEdge) {
        this.eastEdge = eastEdge;
    }
    public Terrain getSouthEdge() {
        return southEdge;
    }
    public void setSouthEdge(Terrain southEdge) {
        this.southEdge = southEdge;
    }
    public Terrain getWestEdge() {
        return westEdge;
    }
    public void setWestEdge(Terrain westEdge) {
        this.westEdge = westEdge;
    }

    public PlayableTile(String tileString) {
        if (tileString.length()==5) {
            this.tileString = tileString;
        } else {
            System.out.println("Invalid tile specifications");
        }
    }

    private Terrain translateCharToTerrain(char terrainChar) {
        Terrain terrain = new JungleTerrain();
        switch(terrainChar) {
            case 'T': terrain = new TrailTerrain();
                break;
            case 'J': terrain = new JungleTerrain();
                break;
            case 'L': terrain = new LakeTerrain();
                break;
        }
        return terrain;
    }

    private void generateTerrainsFromTileString() {
        setNorthEdge(translateCharToTerrain(getTileString().charAt(0)));
        setEastEdge(translateCharToTerrain(getTileString().charAt(1)));
        setSouthEdge(translateCharToTerrain(getTileString().charAt(2)));
        setWestEdge(translateCharToTerrain(getTileString().charAt(3)));
    }




}
