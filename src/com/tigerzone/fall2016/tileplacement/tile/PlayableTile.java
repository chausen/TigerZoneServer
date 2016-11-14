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


    public Terrain getZone(int zone) {
        int zoneTranslation = zone - 1;
        return zones[zone];
    }

    public void setZone(int zone, Terrain terrain) {
        int zoneTranslation = zone - 1;
        zones[zoneTranslation] = terrain;
    }

    public PlayableTile(String tileString) {
        this.tileString = tileString;
        createZones(tileString);
    }

    public PlayableTile(String tileString, int rotation) {
        if (tileString.length() != 5) {
            System.out.println("Invalid tile specifications");
        } else {
            this.tileString = tileString;
            createZones(tileString);
            rotateCCW(rotation);
        }
    }


    private void createZones(String tileString) {
        JungleTerrain jungleTerrain;
        TrailTerrain trailTerrain;
        LakeTerrain lakeTerrain;
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
                setAnimal(null);
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
                setAnimal(null);
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
                setAnimal(null);
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
                setAnimal(null);
                break;
            case "TJTJ-":
                jungleTerrain = new JungleTerrain();
                JungleTerrain jungleTerrain2 = new JungleTerrain();
                trailTerrain = new TrailTerrain();
                setZone(1, jungleTerrain);
                setZone(2, trailTerrain);
                setZone(3, jungleTerrain2);
                setZone(4, jungleTerrain);
                setZone(5, trailTerrain); //delete this? define center?
                setZone(6, jungleTerrain2);
                setZone(7, jungleTerrain);
                setZone(8, trailTerrain);
                setZone(9, jungleTerrain2);
                setAnimal(null);
                break;
            case "TJJT-":
                JungleTerrain jungleTerrain1 = new JungleTerrain();
                trailTerrain = new TrailTerrain();
                setZone(1, new JungleTerrain());
                setZone(2, trailTerrain);
                setZone(3, jungleTerrain1);
                setZone(4, trailTerrain);
                setZone(5, trailTerrain); //delete this? define center?
                setZone(6, jungleTerrain1);
                setZone(7, jungleTerrain1);
                setZone(8, jungleTerrain1);
                setZone(9, jungleTerrain1);
                setAnimal(null);
                break;
            case "TJTT-":
                jungleTerrain = new JungleTerrain();
                setZone(1, new JungleTerrain());
                setZone(2, new TrailTerrain());
                setZone(3, jungleTerrain);
                setZone(4, new TrailTerrain());
                setZone(5, new TrailTerrain()); //delete this? define center?
                setZone(6, jungleTerrain);
                setZone(7, new JungleTerrain());
                setZone(8, new TrailTerrain());
                setZone(9, jungleTerrain);
                setAnimal(null);
                break;
            case "LLLL-":
                lakeTerrain = new LakeTerrain();
                setZone(1, lakeTerrain);
                setZone(2, lakeTerrain);
                setZone(3, lakeTerrain);
                setZone(4, lakeTerrain);
                setZone(5, lakeTerrain); //delete this? define center?
                setZone(6, lakeTerrain);
                setZone(7, lakeTerrain);
                setZone(8, lakeTerrain);
                setZone(9, lakeTerrain);
                setAnimal(null);
                break;
            case "JLLL-":
                jungleTerrain = new JungleTerrain();
                lakeTerrain = new LakeTerrain();
                setZone(1, jungleTerrain);
                setZone(2, jungleTerrain);
                setZone(3, jungleTerrain);
                setZone(4, lakeTerrain);
                setZone(5, lakeTerrain); //delete this? define center?
                setZone(6, lakeTerrain);
                setZone(7, lakeTerrain);
                setZone(8, lakeTerrain);
                setZone(9, lakeTerrain);
                setAnimal(null);
                break;
            case "LLJJ-":
                jungleTerrain = new JungleTerrain();
                lakeTerrain = new LakeTerrain();
                setZone(1, jungleTerrain);
                setZone(2, lakeTerrain);
                setZone(3, lakeTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain); //delete this?
                setZone(6, lakeTerrain);
                setZone(7, jungleTerrain);
                setZone(8, jungleTerrain);
                setZone(9, jungleTerrain);
                setAnimal(null);
                break;
            case "JLJL-":
                jungleTerrain = new JungleTerrain();
                lakeTerrain = new LakeTerrain();
                setZone(1, jungleTerrain);
                setZone(2, lakeTerrain);
                setZone(3, lakeTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain); //delete this?
                setZone(6, lakeTerrain);
                setZone(7, jungleTerrain);
                setZone(8, jungleTerrain);
                setZone(9, jungleTerrain);
                setAnimal(null);
                break;
            case "LJLJ-":
                jungleTerrain = new JungleTerrain();
                lakeTerrain = new LakeTerrain();
                LakeTerrain lakeTerrain2 = new LakeTerrain();
                setZone(1, jungleTerrain);
                setZone(2, lakeTerrain);
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain);
                setZone(6, jungleTerrain);
                setZone(7, jungleTerrain);
                setZone(8, lakeTerrain2);
                setZone(9, jungleTerrain);
                setAnimal(null);
                break;
            case "LJJJ-":
                jungleTerrain = new JungleTerrain();
                setZone(1, jungleTerrain);
                setZone(2, new LakeTerrain());
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain);
                setZone(6, jungleTerrain);
                setZone(7, jungleTerrain);
                setZone(8, jungleTerrain);
                setZone(9, jungleTerrain);
                setAnimal(null);
                break;
            case "JLLJ-":
                jungleTerrain = new JungleTerrain();
                setZone(1, jungleTerrain);
                setZone(2, jungleTerrain);
                setZone(3, jungleTerrain);
                setZone(4, jungleTerrain);
                setZone(5, jungleTerrain);
                setZone(6, new LakeTerrain());
                setZone(7, jungleTerrain);
                setZone(8, new LakeTerrain());
                setZone(9, jungleTerrain);
                setAnimal(null);
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

    public void rotateCCW2(int degrees){
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

    public void rotateCCW(int degrees){
        int rotationCount = degrees/90;
        for (int i = 0; i < rotationCount; i++) {
            Terrain temp1 = getZone(1);
            Terrain temp2 = getZone(2);
            setZone(1, getZone(3));
            setZone(2, getZone(6));
            setZone(3, getZone(9));
            setZone(6, getZone(8));
            setZone(9, getZone(7));
            setZone(8, getZone(4));
            setZone(7, temp1);
            setZone(4, temp2);
        }
    }




}
