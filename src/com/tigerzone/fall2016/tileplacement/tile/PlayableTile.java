package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.tileplacement.terrain.*;

/**
 * Created by lenovo on 11/13/2016.
 */
public class PlayableTile {

    private Terrain northFace;
    private Terrain eastFace;
    private Terrain southFace;
    private Terrain westFace;
    private Animal animal;
    private Terrain centerTerrain;

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
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public Terrain getCenterTerrain() {
        return centerTerrain;
    }
    public void setCenterTerrain(Terrain centerTerrain) {
        this.centerTerrain = centerTerrain;
    }

    public PlayableTile(String tileString, int rotation) {
        if (tileString.length()!=5) {
            System.out.println("Invalid tile specifications");
        } else {
            generateTerrainsFromTileString(tileString);
            generateSpecialFeature(tileString);
            rotateCCW(rotation);
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

    private void generateTerrainsFromTileString(String tileString) {
        setNorthFace(translateCharToTerrain(tileString.charAt(0)));
        setEastFace(translateCharToTerrain(tileString.charAt(1)));
        setSouthFace(translateCharToTerrain(tileString.charAt(2)));
        setWestFace(translateCharToTerrain(tileString.charAt(3)));
    }

    private void generateSpecialFeature(String tileString) {
        char specialFeatureChar = tileString.charAt(5);
        switch (specialFeatureChar) {
            case 'X': setCenterTerrain(new DenTerrain());
                break;
            case '-': setCenterTerrain(null);
                setAnimal(null);
                break;
            case 'C':
                setAnimal(new Crocodile());
                break;
            case 'D': setAnimal(new Deer());
                break;
            case 'B': setAnimal(new Buffalo());
                break;
            case 'P': setAnimal(new Boar());
                break;
        }
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




}
