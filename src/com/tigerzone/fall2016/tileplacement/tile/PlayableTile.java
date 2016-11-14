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


    private void createZones(String tileString) {
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
                setZone(1, new JungleTerrain());
                TrailTerrain trailTerrain1 = new TrailTerrain();
                setZone(2,trailTerrain1);
                JungleTerrain jungleTerrain1 = new JungleTerrain();
                setZone(3, jungleTerrain1);
                setZone(4, trailTerrain1);
                setZone(5, trailTerrain1);
                setZone(6, new LakeTerrain());
                setZone(7, jungleTerrain1);
                setZone(8, jungleTerrain1);
                setZone(9, jungleTerrain1);
                break;
            case "TLJTP":
                setZone(1, new JungleTerrain());
                TrailTerrain trailTerrain2 = new TrailTerrain();
                setZone(2,trailTerrain2);
                JungleTerrain jungleTerrain3 = new JungleTerrain();
                setZone(3, jungleTerrain3);
                setZone(4, trailTerrain2);
                setZone(5, trailTerrain2);
                setZone(6, new LakeTerrain());
                setZone(7, jungleTerrain3);
                setZone(8, jungleTerrain3);
                setZone(9, jungleTerrain3);
                setAnimal(new Boar());
                break;
            case "JLTT-":
                JungleTerrain j1 = new JungleTerrain();
                setZone(1, j1);
                setZone(2, j1);
                setZone(3, j1);
                TrailTerrain t1 = new TrailTerrain();
                setZone(4, t1);
                setZone(5, t1);
                setZone(6, new LakeTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, t1);
                setZone(9, j1);
                break;
            case "JLTTB":
                JungleTerrain j2 = new JungleTerrain();
                setZone(1, j2);
                setZone(2, j2);
                setZone(3, j2);
                TrailTerrain t2 = new TrailTerrain();
                setZone(4, t2);
                setZone(5, t2);
                setZone(6, new LakeTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, t2);
                setZone(9, j2);
                setAnimal(new Buffalo());
                break;
            case "TLTJ-":
                JungleTerrain j3 = new JungleTerrain();
                setZone(1, j3);
                TrailTerrain t3 = new TrailTerrain();
                setZone(2, t3);
                JungleTerrain j4 = new JungleTerrain();
                setZone(3, j4);
                setZone(4, j3);
                setZone(5, t3);
                setZone(6, new LakeTerrain());
                setZone(7, j3);
                setZone(8, t3);
                setZone(9, j4);
                break;
            case "TLTJD":
                JungleTerrain j5 = new JungleTerrain();
                setZone(1, j5);
                TrailTerrain t4 = new TrailTerrain();
                setZone(2, t4);
                JungleTerrain j6 = new JungleTerrain();
                setZone(3, j6);
                setZone(4, j5);
                setZone(5, t4);
                setZone(6, new LakeTerrain());
                setZone(7, j5);
                setZone(8, t4);
                setZone(9, j6);
                setAnimal(new Deer());
                break;
            case "TLLL-":
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                setZone(3, new JungleTerrain());
                LakeTerrain l1 = new LakeTerrain();
                setZone(4, l1);
                setZone(5, l1);
                setZone(6, l1);
                setZone(7, l1);
                setZone(8, l1);
                setZone(9, l1);
                break;
            case "TLTT-":
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                JungleTerrain j7 = new JungleTerrain();
                setZone(3, j7);
                setZone(4, new TrailTerrain());
                setZone(5, new TrailTerrain());
                setZone(6, new LakeTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, new TrailTerrain());
                setZone(9, j7);
                break;
            case "TLTTP":
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                JungleTerrain j8 = new JungleTerrain();
                setZone(3, j8);
                setZone(4, new TrailTerrain());
                setZone(5, new TrailTerrain());
                setZone(6, new LakeTerrain());
                setZone(7, new JungleTerrain());
                setZone(8, new TrailTerrain());
                setZone(9, j8);
                setAnimal(new Boar());
                break;
            case "TLLT-":
                setZone(1, new JungleTerrain());
                TrailTerrain t5 = new TrailTerrain();
                setZone(2, t5);
                JungleTerrain j9 = new JungleTerrain();
                setZone(3, j9);
                setZone(4, t5);
                setZone(5, t5);
                LakeTerrain l2 = new LakeTerrain();
                setZone(6, l2);
                setZone(7, j9);
                setZone(8, l2);
                setZone(9, l2);
                break;
            case "TLLTB":
                setZone(1, new JungleTerrain());
                TrailTerrain t6 = new TrailTerrain();
                setZone(2, t6);
                JungleTerrain j10 = new JungleTerrain();
                setZone(3, j10);
                setZone(4, t6);
                setZone(5, t6);
                LakeTerrain l3 = new LakeTerrain();
                setZone(6, l3);
                setZone(7, j10);
                setZone(8, l3);
                setZone(9, l3);
                setAnimal(new Buffalo());
                break;
            case "LJTJ-":
                JungleTerrain j11 = new JungleTerrain();
                setZone(1, j11);
                setZone(2, new LakeTerrain());
                JungleTerrain j12 = new JungleTerrain();
                setZone(3, j12);
                setZone(4, j11);
                TrailTerrain t7 = new TrailTerrain();
                setZone(5, t7);
                setZone(6, j12);
                setZone(7, j11);
                setZone(8, t7);
                setZone(9, j12);
                break;
            case "LJTJD":
                JungleTerrain j13 = new JungleTerrain();
                setZone(1, j13);
                setZone(2, new LakeTerrain());
                JungleTerrain j14 = new JungleTerrain();
                setZone(3, j14);
                setZone(4, j13);
                TrailTerrain t8 = new TrailTerrain();
                setZone(5, t8);
                setZone(6, j14);
                setZone(7, j13);
                setZone(8, t8);
                setZone(9, j14);
                setAnimal(new Deer());
                break;
            case "TLLLC":
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                setZone(3, new JungleTerrain());
                LakeTerrain l4 = new LakeTerrain();
                setZone(4, l4);
                setZone(5, l4);
                setZone(6, l4);
                setZone(7, l4);
                setZone(8, l4);
                setZone(9, l4);
                setAnimal(new Crocodile());
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
