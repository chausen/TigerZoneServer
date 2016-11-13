package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerrain;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * Created by Aidan on 11/7/2016.
 */
public class FreeSpaceBoard {

    //should be private for now, leaving public for testing
    //public List<FreeSpace> freeSpaceList = new ArrayList<FreeSpace>();
    public HashMap<Point2D, FreeSpace> freeSpaceMap = new HashMap<>();

    //when the game starts there should be 4 FreeSpaces in the FreeSpace list because one
    //tile is automatically added at the start of the game
    public FreeSpaceBoard() {
        freeSpaceMap.put(new Point2D(0.0, 1.0),
                new FreeSpace(new FreeTerrain(), new FreeTerrain(), new TrailTerrain(), new FreeTerrain()));
        freeSpaceMap.put(new Point2D(1.0, 0.0),
                new FreeSpace(new FreeTerrain(), new FreeTerrain(), new FreeTerrain(), new LakeTerrain()));
        freeSpaceMap.put(new Point2D(0.0, -1.0),
                new FreeSpace(new TrailTerrain(), new FreeTerrain(), new FreeTerrain(), new FreeTerrain()));
        freeSpaceMap.put(new Point2D(-1.0, 0.0),
                new FreeSpace(new FreeTerrain(), new JungleTerrain(), new FreeTerrain(), new FreeTerrain()));
    }

    public boolean needToRemove(AreaTile areaTile) {
        boolean placeable = false;
        Set<Point2D> keySet = freeSpaceMap.keySet();
        Iterator<Point2D> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Point2D point2D = iterator.next();
            for(int i = 0; i < 3 || placeable; i++) {
                if (freeSpaceMap.get(point2D).getNorthTerrain().accept(areaTile.getNorthEdgeCenter())
                        && freeSpaceMap.get(point2D).getEastTerrain().accept(areaTile.getEastEdgeCenter())
                        && freeSpaceMap.get(point2D).getSouthTerrain().accept(areaTile.getSouthEdgeCenter())
                        && freeSpaceMap.get(point2D).getWestTerrain().accept(areaTile.getWestEdgeCenter())) {
                    placeable = true;
                    break;
                }
                areaTile.rotateCW();
            }
            //areaTile.rotateCW();
        }
        return placeable;
    }

    public boolean isPlaceable(Point2D position, AreaTile areaTile){
        FreeSpace freeSpace = freeSpaceMap.get(position);
        if(freeSpace == null){
            return false;
        }
        return (freeSpace.getNorthTerrain().accept(areaTile.getNorthEdgeCenter())
                && freeSpace.getEastTerrain().accept(areaTile.getEastEdgeCenter())
                && freeSpace.getSouthTerrain().accept(areaTile.getSouthEdgeCenter())
                && freeSpace.getWestTerrain().accept(areaTile.getWestEdgeCenter()))
                ? true : false;
    }

    //when the player wants to add the tile, they need to specify the
    //location. To find the corresponding FreeSpace you would need to loop
    //through the whole freeSpaceList. If used a hashmap instead it would go
    //from O(n) to O(1). Need to ask team what they think -Aidan
    public void placeTile(Point2D position, AreaTile areaTile){
        FreeSpace freeSpace = freeSpaceMap.get(position);
        FreeSpaceBuilder freeSpaceBuilder = new FreeSpaceBuilder(freeSpace, areaTile, freeSpaceMap);
        makeFreeSpace(freeSpaceBuilder, position);
    }

    private void makeFreeSpace(FreeSpaceBuilder freeSpaceBuilder, Point2D position){
        freeSpaceMap.remove(position);
        freeSpaceBuilder.buildNorthTerrain(position);
        freeSpaceBuilder.buildEastTerrain(position);
        freeSpaceBuilder.buildSouthTerrain(position);
        freeSpaceBuilder.buildWestTerrain(position);
    }

    public FreeSpace getFreeSpace(Point2D position){
        return freeSpaceMap.get(position);
    }

}
