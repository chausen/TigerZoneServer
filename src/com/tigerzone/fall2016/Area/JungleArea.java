package com.tigerzone.fall2016.Area;

/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area implements Mergeable {

    public JungleArea() {
    }

    @Override
    boolean isComplete() {
        return false;
    }

    @Override
    public void merge(Mergeable mergeable) {

    }
}
