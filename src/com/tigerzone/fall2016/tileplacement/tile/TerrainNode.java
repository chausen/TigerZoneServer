package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

import java.util.List;

/**
 * Created by lenovo on 11/14/2016.
 */
public class TerrainNode {

    Terrain terrain;
    BoardTile boardTile;
    List<TerrainNode> terrainNodeList;
    Animal animal;
    private List<Integer> canConnectTo;
    private List<Integer> zones;

    public List<TerrainNode> getTerrainNodeList() {
        return terrainNodeList;
    }
    public List<Integer> getCanConnectTo() {
        return canConnectTo;
    }
    public List<Integer> getZones() {
        return zones;
    }

    public void join(TerrainNode connectionNode){
        for (Integer canConnect: canConnectTo) {
            for (Integer connectionZone: connectionNode.getZones()) {
                if (canConnect==connectionZone) {
                    terrainNodeList.add(connectionNode);
                    connectionNode.addTerrainNode(this);
                    canConnectTo.remove(canConnect);
                    connectionNode.getCanConnectTo().remove(connectionZone);
                }
            }
        }
    }

    public void addTerrainNode(TerrainNode terrainNode) {
        terrainNodeList.add(terrainNode);
    }

    private void mergeTerrainNodeLists(TerrainNode terrainNode) {
        for (TerrainNode node: terrainNode.getTerrainNodeList()) {
            terrainNodeList.add(node);
        }
    }

    private void rotateCanConnectTo(int degrees) {
        rotateCCW(degrees, getCanConnectTo());
    }
    private void rotateZones(int degrees) {
        rotateCCW(degrees, getZones());
    }

    public void rotateCCW(int degrees, List<Integer> integerList) {
        int rotations = degrees/90;
        for (int i=0; i<rotations; i++) {
            for (Integer integer: integerList) {
                switch(integer) {
                    case 1: integer=3;
                        break;
                    case 2: integer=6;
                        break;
                    case 3: integer=9;
                        break;
                    case 4: integer=2;
                        break;
                    case 5: integer=5;
                        break;
                    case 6: integer=8;
                        break;
                    case 7: integer=1;
                        break;
                    case 8: integer=4;
                        break;
                    case 9: integer=7;
                        break;
                }
            }
        }
    }


}
