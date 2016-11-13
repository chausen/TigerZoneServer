package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;

/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {

    public DenArea() {
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
