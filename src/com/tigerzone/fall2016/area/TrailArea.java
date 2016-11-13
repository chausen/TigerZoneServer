package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;

/**
 * Created by lenovo on 11/7/2016.
 */
public class TrailArea extends CrocodileFriendlyArea implements Mergeable {
    public TrailArea() {
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInTrail();
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
