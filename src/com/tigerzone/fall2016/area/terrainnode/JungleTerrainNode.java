package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.area.LakeArea;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;

import java.util.List;
import java.util.Set;

/**
 * Created by Aidan on 11/15/2016.
 */
public class JungleTerrainNode extends TerrainNode {

    Set<LakeTerrainNode> adjacentLakes;
    Set<DenTerrainNode> adjacentDens;

    public Set<LakeTerrainNode> getAdjacentLakes() {
        return adjacentLakes;
    }
    public Set<DenTerrainNode> getAdjacentDens() {
        return adjacentDens;
    }

    public JungleTerrainNode(List<Integer> canConnectTo, List<Integer> zones, Set<LakeTerrainNode> lakeTerrainNodes, Set<DenTerrainNode> denTerrainNodes) {
        setCanConnectTo(canConnectTo);
        setZones(zones);
        this.adjacentLakes = lakeTerrainNodes;
        this.adjacentDens = denTerrainNodes;
        setArea(createArea());

    }

    public Area getArea() {
        return super.getArea();
    }


    @Override
    public Area createArea() {
        JungleArea jungleArea = new JungleArea();
        jungleArea.addTerrainNode(this);
        return jungleArea;
    }

    public void mergeNodes(JungleTerrainNode otherNode) {
        adjacentLakes.addAll(otherNode.getAdjacentLakes());
        adjacentDens.addAll(otherNode.getAdjacentDens());
        otherNode = this;
        otherNode.setArea(this.getArea());
    }

    @Override
    public boolean isSingleEdge() {
        return true;
    }
}
