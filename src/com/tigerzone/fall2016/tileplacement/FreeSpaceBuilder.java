package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerrain;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBuilder {

    private FreeSpace freeSpace;
    private PlayableTile playableTile;
    private HashMap<Point, FreeSpace> freeSpaceMap;

    public FreeSpaceBuilder(FreeSpace freeSpace, PlayableTile playableTile, HashMap<Point, FreeSpace> freeSpaceMap){
        this.freeSpace = freeSpace;
        this.playableTile = playableTile;
        this.freeSpaceMap = freeSpaceMap;
    }

    public void makeFreeSpace(Point position){
        freeSpaceMap.remove(position);
        buildNorthTerrain(position);
        buildEastTerrain(position);
        buildSouthTerrain(position);
        buildWestTerrain(position);
    }

    private void buildNorthTerrain(Point position){
        if(!freeSpace.getNorthTerrain().isFree()){
            return;
        }
        Point northPoint = position;
        northPoint.translate(0,1);
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(northPoint);
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setSouthTerrain(playableTile.getNorthFace());
        }
        else {
            FreeSpace freeSpaceNorth = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    playableTile.getNorthFace(), new FreeTerrain());
            //freeSpaceMap.put(position.add(0.0, 1.0), freeSpaceNorth);
            freeSpaceMap.put(northPoint, freeSpaceNorth);
        }
    }

    private void buildEastTerrain(Point position){
        if(!freeSpace.getEastTerrain().isFree()){
            return;
        }
        Point eastPoint = position;
        eastPoint.translate(1,0);
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(eastPoint);
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setWestTerrain(playableTile.getEastFace());
        }
        else {
            FreeSpace freeSpaceEast = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    new FreeTerrain(), playableTile.getEastFace());
            //freeSpaceMap.put(position.add(1.0, 0.0), freeSpaceEast);
            freeSpaceMap.put(eastPoint, freeSpaceEast);
        }
    }

    private void buildSouthTerrain(Point position){
        if(!freeSpace.getSouthTerrain().isFree()){
            return;
        }
        Point southPoint = position;
        southPoint.translate(0,-1);
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(southPoint);
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setNorthTerrain(playableTile.getSouthFace());
        }
        else {
            FreeSpace freeSpaceSouth = new FreeSpace(playableTile.getSouthFace(), new FreeTerrain(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(southPoint, freeSpaceSouth);
        }
    }

    private void buildWestTerrain(Point position){
        if(!freeSpace.getWestTerrain().isFree()){
            return;
        }
        Point westPoint = position;
        westPoint.translate(-1,0);
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(westPoint);
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setEastTerrain(playableTile.getWestFace());
        }
        else {
            FreeSpace freeSpaceWest = new FreeSpace(new FreeTerrain(), playableTile.getEastFace(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(westPoint, freeSpaceWest);
        }
    }

}