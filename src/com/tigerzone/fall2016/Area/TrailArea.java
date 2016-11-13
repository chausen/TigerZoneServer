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
    boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {

    }
}
