package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerrain;

import java.util.HashMap;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBuilder {

    private FreeSpace freeSpace;
    private AreaTile areaTile;
    private HashMap<Point2D, FreeSpace> freeSpaceMap;
    private boolean addFreeSpaces;

    public FreeSpaceBuilder(FreeSpace freeSpace, AreaTile areaTile, HashMap<Point2D, FreeSpace> freeSpaceMap){
        this.freeSpace = freeSpace;
        this.areaTile = areaTile;
        this.freeSpaceMap = freeSpaceMap;
    }

    public void makeFreeSpace(Point2D position, boolean addFreeSpace){
        freeSpaceMap.remove(position);
        buildNorthTerrain(position, addFreeSpace);
        buildEastTerrain(position, addFreeSpace);
        buildSouthTerrain(position, addFreeSpace);
        buildWestTerrain(position, addFreeSpace);
    }

    private void buildNorthTerrain(Point2D position, boolean addFreeSpaces){
        if(!freeSpace.getNorthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, 1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setSouthTerrain(areaTile.getNorthEdgeCenter());
        }
        else if (addFreeSpaces){
            FreeSpace freeSpaceNorth = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    areaTile.getNorthEdgeCenter(), new FreeTerrain());
            freeSpaceMap.put(position.add(0.0, 1.0), freeSpaceNorth);
        }
    }

    private void buildEastTerrain(Point2D position, boolean addFreeSpaces){
        if(!freeSpace.getEastTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setWestTerrain(areaTile.getEastEdgeCenter());
        }
        else if (addFreeSpaces){
            FreeSpace freeSpaceEast = new FreeSpace(new FreeTerrain(), new FreeTerrain(),
                    new FreeTerrain(), areaTile.getNorthEdgeCenter());
            freeSpaceMap.put(position.add(1.0, 0.0), freeSpaceEast);
        }
    }

    private void buildSouthTerrain(Point2D position, boolean addFreeSpaces){
        if(!freeSpace.getSouthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, -1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setNorthTerrain(areaTile.getSouthEdgeCenter());
        }
        else if (addFreeSpaces){
            FreeSpace freeSpaceSouth = new FreeSpace(areaTile.getSouthEdgeCenter(), new FreeTerrain(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(position.add(0.0, -1.0), freeSpaceSouth);
        }
    }

    private void buildWestTerrain(Point2D position, boolean addFreeSpaces){
        if(!freeSpace.getWestTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(-1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setEastTerrain(areaTile.getWestEdgeCenter());
        }
        else if (addFreeSpaces){
            FreeSpace freeSpaceWest = new FreeSpace(areaTile.getWestEdgeCenter(), new FreeTerrain(),
                    new FreeTerrain(), new FreeTerrain());
            freeSpaceMap.put(position.add(-1.0, 0.0), freeSpaceWest);
        }
    }

}