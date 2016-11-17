
package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.DenTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.LakeTerrainNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area {
    
    //List<JungleTerrainNode> jungleTerrainNodes;
    Set<JungleTerrainNode> jungleTerrainNodes;

    private Set<LakeArea> lakeAreas;
    private Set<DenArea> denAreas;

    public int countCompletedLakes() {
        findLakeAreas();
        int completedLakeCount=0;
        for (LakeArea lakeArea: lakeAreas) {
            if(lakeArea.isComplete()) {
                completedLakeCount++;
                this.lakeAreas.remove(lakeArea); //don't want to double score lakeAreas
            }
        }
        return completedLakeCount;
    }

    public void findLakeAreas() {
        this.lakeAreas = new HashSet<>();
        for (JungleTerrainNode jungleTerrainNode: jungleTerrainNodes) {
            for (LakeTerrainNode lakeTerrainNode: jungleTerrainNode.getAdjacentLakes()) { //get adjacentLakes returns set of LakeTerrainNodes
                lakeAreas.add((LakeArea)lakeTerrainNode.getArea());
            }
        }
    }

    public int countCompletedDens() {
        int completedDenCount = 0;
        for (JungleTerrainNode jungleTerrainNode: jungleTerrainNodes) { //what if have multiple jungleTerrainNodes with same Lake?
            for (DenTerrainNode dens: jungleTerrainNode.getAdjacentDens()) {
                if (dens.getArea().isComplete()) { // TODO: 11/15/2016 how to avoid double counting???
                    completedDenCount++;
                }
            }
        }
        return completedDenCount;
    }


    public JungleArea getArea() {
        return this.getArea();
    }

    @Override
    boolean isPredatorPlaceable(Predator predator) {
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