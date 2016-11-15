package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public abstract class CrocodileFriendlyArea extends Area {
    private List<Crocodile> crocodileList;


    public CrocodileFriendlyArea(){
        this.crocodileList = new ArrayList<>();
    }

    public CrocodileFriendlyArea(Point2D position, AreaTile areaTile, HashMap<Point2D, FreeSpace> freeSpaceMap) {
        super(position, areaTile, freeSpaceMap);
        this.crocodileList = new ArrayList<>();
    }

    /**
     * Returns true if list is not empty
     * @return
     */
    private boolean hasCrocodileInArea(){
        return this.crocodileList.isEmpty();
    }

    /**
     * this method should be called when user is placing a Crocodile
     * @param crocodile
     */
    @Override
    public void placePredator(Crocodile crocodile){
        if(hasCrocodileInArea()){
            this.addAnimalFromAreaTile(crocodile);
        }else{
            //throw forfeit!!
        }
    }

    /**
     * this method should be called when a Crocodile is added from an AreaTile
     * @param crocodile
     */
    @Override
    public void addAnimalFromAreaTile(Crocodile crocodile){
        this.crocodileList.add(crocodile);
    }

    /**
     * Returns the number of Crocodiles in this area
     * @return
     */
    public int getNumOfCrocodiles() {
        return this.crocodileList.size();
    }
}