package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Goat;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.scoring.Scorer;

import java.awt.*;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {
    private Point center;
    Set<JungleTerrainNode> jungleTerrainNodes;
    private boolean denScored = false;

    public  DenArea(){}

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
    public void acceptScorer(Scorer scorer) {
        scorer.score(this);
        denScored = true;
    }

    @Override
    public boolean placePlaceableAnimal(Crocodile crocodile) {
        return false;
    }

    public  DenArea(Point center){
        this.center = center;
    }

    @Override
    boolean isAnimalPlaceable(PlaceableAnimal placeableAnimal) {
        return placeableAnimal.placeableInDen();
    }

    @Override
    public boolean isComplete() {
        return this.getBoardTiles().size() == 9;
    }


    public Point getCenter() {
        return center;
    }

    public void setCenter(Point point){
        this.center = point;
    }

    public boolean isDenScored() {
        return denScored;
    }

    @Override
    public void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas) {
    }
}