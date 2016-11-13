package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;

/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area implements Mergeable {

    public JungleArea() {
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInJungle();
    }


    @Override
    boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {

    }
}
