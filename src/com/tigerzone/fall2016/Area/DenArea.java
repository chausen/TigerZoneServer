package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;

/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {

    public DenArea() {
    }

    @Override
    boolean isAnimalPlacable(Animal animal) {
        return false;
    }

    @Override
    boolean isComplete() {
        return false;
    }
}
