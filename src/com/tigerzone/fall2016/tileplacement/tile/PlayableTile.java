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
    private boolean specialCase = false;
    private String tileString;
    private int trailCount;
    private int jungleCount;
    private int lakeCount;

    private Terrain[] zones = new Terrain[9];

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

    public boolean isSpecialCase() {
        return specialCase;
    }

    public void setSpecialCase(boolean specialCase) {
        this.specialCase = specialCase;
    }

    public String getTileString() {
        return tileString;
    }

    public int getTrailCount() {
        return trailCount;
    }

    public int getJungleCount() {
        return jungleCount;
    }

    public int getLakeCount() {
        return lakeCount;
    }

    public PlayableTile(String tileString) {
        this.tileString = tileString;

    }

    public Terrain getZone(int zone) {
        int zoneTranslation = zone - 1;
        return zones[zone];
    }

    public void setZone(int zone, Terrain terrain) {
        int zoneTranslation = zone - 1;
        zones[zoneTranslation] = terrain;
    }

    public PlayableTile(String tileString, int rotation) {
        if (tileString.length() != 5) {
            System.out.println("Invalid tile specifications");
        } else {
            this.tileString = tileString;
            generateTerrainsFromTileString(tileString);
            generateSpecialFeature(tileString);
            rotateCCW(rotation);
        }
    }

    private Terrain translateCharToTerrain(char terrainChar) {
        Terrain terrain = new JungleTerrain();
        switch (terrainChar) {
            case 'T':
                terrain = new TrailTerrain();
                trailCount++;
                break;
            case 'J':
                terrain = new JungleTerrain();
                jungleCount++;
                break;
            case 'L':
                terrain = new LakeTerrain();
                lakeCount++;
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
        char specialFeatureChar = tileString.charAt(4);
        switch (specialFeatureChar) {
            case 'X':
                setCenterTerrain(new DenTerrain());
                break;
            case '-':
                setCenterTerrain(null);
                setAnimal(null);
                break;
            case 'C':
                setAnimal(new Crocodile());
                break;
            case 'D':
                setAnimal(new Deer());
                break;
            case 'B':
                setAnimal(new Buffalo());
                break;
            case 'P':
                setAnimal(new Boar());
                break;
        }
    }


    private void zonesSpecialCase(String specialCases) {
        JungleTerrain jungleTerrain;
        switch (tileString) {
            case "JJJJ-":
                jungleTerrain = new JungleTerrain();
                setZone(1, jungleTerrain);
                setZone(2, jungleTerrain);
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain);
                setZone(6, jungleTerrain);
                setZone(7, jungleTerrain);
                setZone(8, jungleTerrain);
                setZone(9, jungleTerrain);
                break;
            case "JJJJX":
                jungleTerrain = new JungleTerrain();
                setZone(1, jungleTerrain);
                setZone(2, jungleTerrain);
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, new DenTerrain());
                setZone(6, jungleTerrain);
                setZone(7, jungleTerrain);
                setZone(8, jungleTerrain);
                setZone(9, jungleTerrain);
                break;
            case "JJTJX":
                jungleTerrain = new JungleTerrain();
                setZone(1, jungleTerrain);
                setZone(2, jungleTerrain);
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, new DenTerrain());
                setZone(6, jungleTerrain);
                setZone(7, jungleTerrain);
                setZone(8, new TrailTerrain());
                setZone(9, jungleTerrain);
                break;
            case "TTTT-":
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                setZone(3, new JungleTerrain());
                setZone(4, new TrailTerrain());
                setZone(5, new TrailTerrain()); //delete this? define center?
                setZone(6, new TrailTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, new TrailTerrain());
                setZone(9, new JungleTerrain());
                break;
            case "TJTJ-":
                jungleTerrain = new JungleTerrain();
                JungleTerrain jungleTerrain2 = new JungleTerrain();
                TrailTerrain trailTerrain = new TrailTerrain();
                setZone(1, jungleTerrain);
                setZone(2, trailTerrain);
                setZone(3, jungleTerrain2);
                setZone(4, jungleTerrain);
                setZone(5, trailTerrain); //delete this? define center?
                setZone(6, jungleTerrain2);
                setZone(7, jungleTerrain);
                setZone(8, trailTerrain);
                setZone(9, jungleTerrain2);
                break;
            case "TJJT-":
                setZone(1, new TrailTerrain());
                setZone(2, new TrailTerrain());
                setZone(3, new JungleTerrain());
                setZone(4, new TrailTerrain());
                setZone(5, new TrailTerrain()); //delete this? define center?
                setZone(6, new TrailTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, new TrailTerrain());
                setZone(9, new JungleTerrain());
                break;
            case "TJTT-":

                break;
            case "LLLL-":

                break;
            case "JLLL-":

                break;
            case "LLJJ-":

                break;
            case "JLJL-":

                break;
            case "LJLJ-":

                break;
            case "LJJJ-":

                break;
            case "JLLJ-":

                break;
            case "TLJT-":

                break;
            case "TLJTP":

                break;
            case "JLTT-":

                break;
            case "JLTTB":

                break;
            case "TLTJ-":

                break;
            case "TLTJD":

                break;
            case "TLLL-":

                break;
            case "TLTT-":

                break;
            case "TLTTP":

                break;
            case "TLLT-":

                break;
            case "TLLTB":

                break;
            case "LJTJ-":

                break;
            case "LJTJD":

                break;
            case "TLLLC":

                break;
        }
    }

    public void generateJungleCorners() {
        JungleTerrain jungleTerrain = new JungleTerrain();
        zones[1] = new JungleTerrain();
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
