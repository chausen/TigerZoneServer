package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Prey;

import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends CrocodileFriendlyArea implements Mergeable {
    private List<Prey> preyList;

    public LakeArea() {}

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
