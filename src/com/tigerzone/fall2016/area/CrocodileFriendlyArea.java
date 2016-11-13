package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class CrocodileFriendlyArea extends Area {
    private List<Crocodile> crocodileList;

    public CrocodileFriendlyArea(Point2D position, AreaTile areaTile, HashMap<Point2D, FreeSpace > freeSpaceMap){
        super(position, areaTile, freeSpaceMap);
        crocodileList = new ArrayList<>();
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return false;
    }


    public boolean hasCrocodileInArea(){
        return (this.crocodileList.size() > 0);
    }

    /**
     * this method should be called when the Crocodile is from an AnimalAreaTile
     * @param crocodile
     */
    public void addCrocodileToArea(Crocodile crocodile){
        this.crocodileList.add(crocodile);
    }

    /**
     * this method should be called when user is placing a Crocodile
     * @param crocodile
     */
    @Override
    public void placePredator(Crocodile crocodile){
        if(!hasCrocodileInArea()){
            this.crocodileList.add(crocodile);
        }
    }

    @Override
    boolean isComplete() {
        return false;
    }
}
