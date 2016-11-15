
package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.LakeTerrainNode;

import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area {
    
    //List<JungleTerrainNode> jungleTerrainNodes;
    Set<JungleTerrainNode> jungleTerrainNodes;
    
    public int countCompletedLakes() {
        int completedLakeCount = 0;
        for (JungleTerrainNode jungleTerrainNode: jungleTerrainNodes) { //what if have multiple jungleTerrainNodes with same Lake?
            for (LakeTerrainNode lakes: jungleTerrainNode.getAdjacentLakes()) {
                if (lakes.getArea().isComplete()) { // TODO: 11/15/2016 how to avoid double counting??? 
                    completedLakeCount++;
                }
            }
        }
        return completedLakeCount;
    }
    
    
    
    public JungleArea(){}


    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInJungle();
    }

    @Override
    public boolean isComplete() {
        return false;
    }


    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {
        jungleAreas.add(this);
    }
}