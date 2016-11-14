
package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.HashMap;


/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {
    public  DenArea(){}

    public DenArea(Point2D position, AreaTile areaTile, HashMap<Point2D, FreeSpace> freeSpaceMap) {
        super(position, areaTile, freeSpaceMap);
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInDen();
    }


    @Override
    boolean isComplete() {
        return false;
    }
}
