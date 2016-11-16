package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

import java.util.List;

/**
 * Created by lenovo on 11/14/2016.
 */
public abstract class TerrainNode {

    Area area;
    Terrain terrain;
    private BoardTile boardTile;
    List<TerrainNode> terrainNodeList;
    private Animal animal;
    private List<Integer> canConnectTo;
    private List<Integer> zones;


    public void setCanConnectTo(List<Integer> canConnectTo) {
        this.canConnectTo = canConnectTo;
    }
    public void setZones(List<Integer> zones) {
        this.zones = zones;
    }
    public BoardTile getBoardTile() {
        return boardTile;
    }
    public void setBoardTile(BoardTile boardTile) {
        this.boardTile = boardTile;
    }
    public List<TerrainNode> getTerrainNodeList() {
        return terrainNodeList;
    }
    public List<Integer> getCanConnectTo() {
        return canConnectTo;
    }
    public List<Integer> getZones() {
        return zones;
    }


    public TerrainNode(List<Integer> canConnectTo, List<Integer> zones, List<TerrainNode> terrainNodeList, Animal animal) {
        this.canConnectTo = canConnectTo;
        this.zones = zones;
        this.terrainNodeList = terrainNodeList;
        this.animal=animal;
    }

    public TerrainNode() {

    }

    public Integer getMinimumZoneValue() {
        Integer minimum = zones.get(0);
        for (Integer integer: zones) {
            if(integer<minimum) {
                minimum = integer;
            }
        }
        return minimum;
    }


    public Area getArea(){
        return this.area;
    }

    public void setArea(Area area){
        this.area = area;
    }

    public abstract Area createArea();


    public void addTerrainNode(TerrainNode terrainNode) {
        terrainNodeList.add(terrainNode);
    }

    private void mergeTerrainNodeLists(TerrainNode terrainNode) {
        for (TerrainNode node: terrainNode.getTerrainNodeList()) {
            terrainNodeList.add(node);
        }
    }

    public void rotateTerrainNode(int rotationDegrees) {
        rotateCanConnectTo(rotationDegrees);
        rotateZones(rotationDegrees);
    }

    private void rotateCanConnectTo(int degrees) {
        rotateCCW(degrees, getCanConnectTo());
    }
    private void rotateZones(int degrees) {
        rotateCCW(degrees, getZones());
    }
    private void rotateCCW(int degrees, List<Integer> integerList) {
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
