package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Prey;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends CrocodileFriendlyArea implements Mergeable {
    private List<Prey> preyList;

    public LakeArea(){}

    public LakeArea(Point2D position, AreaTile areaTile, HashMap<Point2D, FreeSpace> freeSpaceMap) {
        super(position, areaTile, freeSpaceMap);
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInLake();
    }

    @Override
    void addAnimalFromAnimalAreaTile(Prey prey){
        preyList.add(prey);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {
    }


    public boolean containsBoar() {
        return false;
    }

    public boolean containsDeer() {
        return false;
    }

    public boolean containsBuffalo() {
        return false;
    }

    public int getCrocs() {
        return 0;
    }
}
