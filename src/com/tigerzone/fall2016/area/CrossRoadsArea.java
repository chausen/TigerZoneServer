package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;

import java.util.List;

/**
 * Created by Aidan on 11/16/2016.
 */
public class CrossRoadsArea extends Area{

    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {
        return;
    }

    @Override
    public void mergeAnimals(Area area) {

    }

    @Override
    public void acceptAnimals(LakeArea area) {

    }

    @Override
    public void acceptAnimals(TrailArea area) {

    }

    @Override
    public void acceptAnimals(DenArea area) {

    }

    @Override
    public void acceptAnimals(JungleArea area) {

    }

    @Override
    boolean isPredatorPlaceable(Predator predator) {
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}