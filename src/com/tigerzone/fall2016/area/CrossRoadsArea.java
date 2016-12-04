package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Goat;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.scoring.Scorer;

import java.util.Set;

/**
 * Created by Aidan on 11/16/2016.
 */
public class CrossRoadsArea extends Area{

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
    public void acceptScorer(Scorer scorer) {}

    @Override
    public boolean placePlaceableAnimal(Crocodile crocodile) {
        return false;
    }

    @Override
    boolean isAnimalPlaceable(PlaceableAnimal placeableAnimal) {
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas) {

    }
}