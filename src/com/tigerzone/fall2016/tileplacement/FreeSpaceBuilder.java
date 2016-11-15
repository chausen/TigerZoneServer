package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerrain;

import java.util.HashMap;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBuilder {

    private FreeSpace freeSpace;
    private PlayableTile playableTile;
    private HashMap<Point2D, FreeSpace> freeSpaceMap;

    public FreeSpaceBuilder(FreeSpace freeSpace, PlayableTile playableTile, HashMap<Point2D, FreeSpace> freeSpaceMap){
        this.freeSpace = freeSpace;
        this.playableTile = playableTile;
        this.freeSpaceMap = freeSpaceMap;
    }

    public void makeFreeSpace(Point2D position){
        freeSpaceMap.remove(position);
        buildNorthTerrain(position);
        buildEastTerrain(position);
        buildSouthTerrain(position);
        buildWestTerrain(position);
    }

    private void buildNorthTerrain(Point2D position){
        if(!freeSpace.getNorthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, 1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setSouthTerrain(playableTile.getNorthFace());
        }
        else {
            FreeSpace freeSpaceNorth = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    playableTile.getNorthFace(), new FreeTerrain());
            freeSpaceMap.put(position.add(0.0, 1.0), freeSpaceNorth);
        }
    }

    private void buildEastTerrain(Point2D position){
        if(!freeSpace.getEastTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setWestTerrain(playableTile.getEastFace());
        }
        else {
            FreeSpace freeSpaceEast = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    new FreeTerrain(), playableTile.getEastFace());
            freeSpaceMap.put(position.add(1.0, 0.0), freeSpaceEast);
        }
    }

    private void buildSouthTerrain(Point2D position){
        if(!freeSpace.getSouthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, -1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setNorthTerrain(playableTile.getSouthFace());
        }
        else {
            FreeSpace freeSpaceSouth = new FreeSpace(playableTile.getSouthFace(), new FreeTerrain(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(position.add(0.0, -1.0), freeSpaceSouth);
        }
    }

    private void buildWestTerrain(Point2D position){
        if(!freeSpace.getWestTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(-1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setEastTerrain(playableTile.getWestFace());
        }
        else {
            FreeSpace freeSpaceWest = new FreeSpace(new FreeTerrain(), playableTile.getEastFace(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(position.add(-1.0, 0.0), freeSpaceWest);
        }
    }

}