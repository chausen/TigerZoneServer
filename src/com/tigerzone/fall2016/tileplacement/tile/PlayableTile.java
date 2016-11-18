package com.tigerzone.fall2016.tileplacement.tile;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.terrain.*;

/**
 * Created by lenovo on 11/13/2016.
 */
public class PlayableTile {

    private Terrain northFace;
    private Terrain eastFace;
    private Terrain southFace;
    private Terrain westFace;
    private String tileString;

    private String playedBy;

    public Terrain getNorthFace() {
        return northFace;
    }
    public void setNorthFace(Terrain northFace) {
        this.northFace = northFace;
    }
    public Terrain getEastFace() {
        return eastFace;
    }
    public void setEastFace(Terrain eastFace) {
        this.eastFace = eastFace;
    }
    public Terrain getSouthFace() {
        return southFace;
    }
    public void setSouthFace(Terrain southFace) {
        this.southFace = southFace;
    }
    public Terrain getWestFace() {
        return westFace;
    }
    public void setWestFace(Terrain westFace) {
        this.westFace = westFace;
    }
    public String getTileString() {
        return tileString;
    }
    public void setTileString(String tileString) {
        this.tileString = tileString;
    }
    public String getPlayedBy() {
        return playedBy;
    }
    public void setPlayedBy(String playedBy) {
        this.playedBy = playedBy;
    }


    public PlayableTile(String tileString) {
        this.tileString = tileString;
        setFaces(tileString);
    }

    public PlayableTile(String tileString, int rotations) {
        this.tileString = tileString;
        setFaces(tileString);
        rotateCCW(rotations);
    }


    private void setFaces(String tileString) {
        setNorthFace(generateFace(tileString.charAt(0)));
        setEastFace(generateFace(tileString.charAt(1)));
        setSouthFace(generateFace(tileString.charAt(2)));
        setWestFace(generateFace(tileString.charAt(3)));
    }

    private Terrain generateFace(char faceCharacter) {
        switch (faceCharacter) {
            case 'J': return new JungleTerrain();
            case 'L': return new LakeTerrain();
            case 'T': return new TrailTerrain();
        }
        return null;
    }


    public void rotateCCW(int degrees){
        int rotationCount = degrees/90;
        Terrain tempFace;
        for (int i=0; i<rotationCount; i++) {
            tempFace = getNorthFace();
            setNorthFace(getEastFace());
            setEastFace(getSouthFace());
            setSouthFace(getWestFace());
            setWestFace(tempFace);
        }
    }

    /**
     * Compares the tileStrings of two PlayableTiles for equality
     *
     * @param tile the tile you're comparing this tile to
     * @return true if the tileStrings are equal; false otherwise
     */
    public boolean equals(PlayableTile tile) {
        boolean result = (this.tileString.equals(tile.getTileString())) ? true : false;
        return result;
    }

}
