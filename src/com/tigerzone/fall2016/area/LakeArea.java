package com.tigerzone.fall2016.area;

/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends Area implements Mergeable {

    public LakeArea() {
    }

    @Override
    boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {

    }


}
