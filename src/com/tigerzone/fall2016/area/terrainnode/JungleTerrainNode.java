package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;

import java.util.List;
import java.util.Set;

/**
 * Created by Aidan on 11/15/2016.
 */
public class JungleTerrainNode extends TerrainNode {

//    List<LakeTerrainNode> adjacentLakes;
//    List<DenTerrainNode> adjacentDens;
    Set<LakeTerrainNode> adjacentLakes;
    Set<DenTerrainNode> adjacentDens;


    public Set<LakeTerrainNode> getAdjacentLakes() {
        return adjacentLakes;
    }
    public Set<DenTerrainNode> getAdjacentDens() {
        return adjacentDens;
    }

    public JungleTerrainNode(){
        this.terrain = new JungleTerrain();
    }

    @Override
    public Area createArea() {
        return new JungleArea();
    }


    public void mergeNodes(JungleTerrainNode otherNode) {
        adjacentLakes.addAll(otherNode.getAdjacentLakes());
        adjacentDens.addAll(otherNode.getAdjacentDens());
        otherNode = this;
        otherNode.setArea(this.getArea());
    }

}
