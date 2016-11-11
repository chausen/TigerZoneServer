package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerain;

import java.util.HashMap;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBuilder {

    private FreeSpace freeSpace;
    private AreaTile areaTile;
    private HashMap<Point2D, FreeSpace> freeSpaceMap;

    public FreeSpaceBuilder(FreeSpace freeSpace, AreaTile areaTile, HashMap<Point2D, FreeSpace> freeSpaceMap){
        this.freeSpace = freeSpace;
        this.areaTile = areaTile;
        this.freeSpaceMap = freeSpaceMap;
    }

    public void buildNorthTerrain(Point2D position){
        if(!freeSpace.getNorthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, 1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setSouthTerrain(areaTile.getNorthEdgeCenter());
        }
        else{
            FreeSpace freeSpaceNorth = new FreeSpace(new FreeTerain(), new FreeTerain(),
                    areaTile.getNorthEdgeCenter(), new FreeTerain());
            freeSpaceMap.put(position.add(0.0, 1.0), freeSpaceNorth);
        }
    }

    public void buildEastTerrain(Point2D position){
        if(!freeSpace.getEastTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setWestTerrain(areaTile.getEastEdgeCenter());
        }
        else{
            FreeSpace freeSpaceEast = new FreeSpace(new FreeTerain(), new FreeTerain(),
                    new FreeTerain(), areaTile.getNorthEdgeCenter());
            freeSpaceMap.put(position.add(1.0, 0.0), freeSpaceEast);
        }
    }

    public void buildSouthTerrain(Point2D position){
        if(!freeSpace.getSouthTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(0.0, -1.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setNorthTerrain(areaTile.getSouthEdgeCenter());
        }
        else{
            FreeSpace freeSpaceSouth = new FreeSpace(areaTile.getSouthEdgeCenter(), new FreeTerain(),
                    new FreeTerain(), new FreeTerain());
            freeSpaceMap.put(position.add(0.0, -1.0), freeSpaceSouth);
        }
    }

    public void buildWestTerrain(Point2D position){
        if(!freeSpace.getWestTerrain().isFree()){
            return;
        }
        FreeSpace needUpdateFreeSpace = freeSpaceMap.get(position.add(-1.0, 0.0));
        if(needUpdateFreeSpace != null){
            needUpdateFreeSpace.setEastTerrain(areaTile.getWestEdgeCenter());
        }
        else{
            FreeSpace freeSpaceWest = new FreeSpace(areaTile.getWestEdgeCenter(), new FreeTerain(),
                    new FreeTerain(), new FreeTerain());
            freeSpaceMap.put(position.add(-1.0, 0.0), freeSpaceWest);
        }
    }

}
