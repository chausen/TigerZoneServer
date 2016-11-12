package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;

/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area implements Mergeable {

    public JungleArea() {
    }

    @Override
    boolean isAnimalPlacable(Animal animal) {
        return false;
    }

    @Override
    boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {

    }
}
