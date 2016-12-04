
package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Goat;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.area.terrainnode.DenTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.LakeTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.scoring.Scorer;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class JungleArea extends Area {
    
    Set<JungleTerrainNode> jungleTerrainNodes;

    private Set<Area> lakeAreas = new HashSet<>();
    private Set<Area> denAreas = new HashSet<>();
    private Set<Area> scoredAreas = new HashSet<>();

    public JungleArea(){
        super();
        jungleTerrainNodes = new HashSet<>();
    }


    void findLakeAreas() {
        for (JungleTerrainNode jungleTerrainNode: jungleTerrainNodes) {
            for (LakeTerrainNode lakeTerrainNode: jungleTerrainNode.getAdjacentLakes()) { //get adjacentLakes returns set of LakeTerrainNodes
                lakeAreas.add(lakeTerrainNode.getArea());
            }
        }
    }

    void findDenAreas() {
        for (JungleTerrainNode jungleTerrainNode: jungleTerrainNodes) {
            for (DenTerrainNode denTerrainNode: jungleTerrainNode.getAdjacentDens()) {
                denAreas.add(denTerrainNode.getArea());
            }
        }
    }

    public int countCompletedLakes() {
        findLakeAreas();
        int completedLakeCount = 0;
        for (Area lakeArea: lakeAreas) {
            if(lakeArea.isComplete()) {
                if (!scoredAreas.contains(lakeArea)) {
                    completedLakeCount++;
                    scoredAreas.add(lakeArea);
                }
            }
    }
        return completedLakeCount;
    }

    public int countCompletedDens() {
        findDenAreas();
        int completedDenCount = 0;
        for (Area denArea: denAreas) {
            if(denArea.isComplete()) {
                if (!scoredAreas.contains(denArea)) {
                    completedDenCount++;
                    scoredAreas.add(denArea);
                }
            }
        }
        return completedDenCount;
    }


    @Override
    public void mergeAnimals(Area area) {
        area.acceptAnimals(this);
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
        area.getTigerList().addAll(this.getTigerList());
    }

    @Override
    public void acceptScorer(Scorer scorer) {}

    @Override
    boolean isAnimalPlaceable(PlaceableAnimal placeableAnimal) {
        return placeableAnimal.placeableInJungle();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas) {
        jungleAreas.add(this);
    }

    @Override
    public void addTerrainNode(TerrainNode terrainNode) {
        super.addTerrainNode(terrainNode);
        jungleTerrainNodes.add((JungleTerrainNode)terrainNode);
    }

    @Override
    public void addTerrainNode(Set<TerrainNode> terrainNodes) {
        super.addTerrainNode(terrainNodes);
        for(TerrainNode terrainNode: terrainNodes){
            jungleTerrainNodes.add((JungleTerrainNode)terrainNode);
        }
    }

    @Override
    public boolean placePlaceableAnimal(Crocodile crocodile) {
        return false;
    }
}