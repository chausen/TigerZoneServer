package com.tigerzone.fall2016.tileplacement;


import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.terrain.FreeTerrain;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
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

    public boolean needToRemove(PlayableTile playableTile) {
        PlayableTile tempTile = playableTile;
        boolean placeable = false;
        Set<Point2D> keySet = freeSpaceMap.keySet();
        Iterator<Point2D> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Point2D point2D = iterator.next();
            for(int i = 0; i < 3; i++) {
                if (freeSpaceMap.get(point2D).getNorthTerrain().accept(tempTile.getNorthFace())
                        && freeSpaceMap.get(point2D).getEastTerrain().accept(tempTile.getEastFace())
                        && freeSpaceMap.get(point2D).getSouthTerrain().accept(tempTile.getSouthFace())
                        && freeSpaceMap.get(point2D).getWestTerrain().accept(tempTile.getWestFace()))
                {
                    placeable = true;
                    break;
                }
                tempTile.rotateCCW(90);
            }
        }
        return placeable;
    }

    public boolean isPlaceable(Point2D position, PlayableTile playableTile){
        FreeSpace freeSpace = freeSpaceMap.get(position);
        if(freeSpace == null){
            return false;
        }
        return (freeSpace.getNorthTerrain().accept(playableTile.getNorthFace())
                && freeSpace.getEastTerrain().accept(playableTile.getEastFace())
                && freeSpace.getSouthTerrain().accept(playableTile.getSouthFace())
                && freeSpace.getWestTerrain().accept(playableTile.getWestFace()));
    }

    //when the player wants to add the tile, they need to specify the
    //location. To find the corresponding FreeSpace you would need to loop
    //through the whole freeSpaceList. If used a hashmap instead it would go
    //from O(n) to O(1). Need to ask team what they think -Aidan
    public void placeTile(Point2D position, PlayableTile playableTile){
        FreeSpace freeSpace = freeSpaceMap.get(position);
        FreeSpaceBuilder freeSpaceBuilder = new FreeSpaceBuilder(freeSpace, playableTile, freeSpaceMap);
        freeSpaceBuilder.makeFreeSpace(position);
    }

    public FreeSpace getFreeSpace(Point2D position){
        return freeSpaceMap.get(position);
    }

}