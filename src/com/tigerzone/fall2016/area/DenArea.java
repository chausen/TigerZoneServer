package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;

import java.awt.*;
import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {
    private Point center;
    Set<JungleTerrainNode> jungleTerrainNodes;

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


    public  DenArea(Point center){
        this.center = center;
    }

    @Override
    boolean isPredatorPlaceable(Predator predator) {
        return predator.placeableInDen();
    }

    @Override
    public boolean isComplete() {
        return this.getBoardTiles().size() == 8;
    }

    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {

    }

    public Point getCenter() {
        return center;
    }
}