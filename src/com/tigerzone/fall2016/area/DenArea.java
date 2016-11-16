package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;

import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class DenArea extends Area {

    Set<JungleTerrainNode> jungleTerrainNodes;

    public  DenArea(){}

    @Override
    boolean isPredatorPlaceable(Predator predator) {
        return predator.placeableInDen();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {

    }
}