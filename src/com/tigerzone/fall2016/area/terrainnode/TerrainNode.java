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
    BoardTile boardTile;
    List<TerrainNode> terrainNodeList;
    Animal animal;
    private List<Integer> canConnectTo;
    private List<Integer> zones;

    public List<Integer> northCompatible;
    public List<Integer> eastCompatible;
    public List<Integer> southCompatible;
    public List<Integer> westCompatible;

    public List<Integer> getNorthCompatible() {
        return northCompatible;
    }
    public List<Integer> getEastCompatible() {
        return eastCompatible;
    }
    public List<Integer> getSouthCompatible() {
        return southCompatible;
    }
    public List<Integer> getWestCompatible() {
        return westCompatible;
    }

    //if your southConnections == my northConnections


    public void mergeTerrainNodes(TerrainNode terrainNode) {

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

    public void northMerge(TerrainNode terrainNode) {
        if (!terrainNode.southCompatible.isEmpty()) { //if the northerntiles southCompatible list is not empty
            for (Integer integer: northCompatible) {
                if (terrainNode.southCompatible.contains(aboveTileCompatibleZones(integer))) {
                    northCompatible.clear();
                    terrainNode.southCompatible.clear();
                    this.mergeTerrainNodes(terrainNode);
                    //or
                    this.area.mergeArea(terrainNode.getArea());
                }
            }
        }
    }

    public void eastMerge(TerrainNode terrainNode) {
        if (!terrainNode.westCompatible.isEmpty()) { //if the northerntiles southCompatible list is not empty
            for (Integer integer: eastCompatible) {
                if (terrainNode.westCompatible.contains(rightTileCompatibleZones(integer))) {
                    eastCompatible.clear();
                    terrainNode.westCompatible.clear();
                    this.mergeTerrainNodes(terrainNode);
                    //or
                    this.area.mergeArea(terrainNode.getArea());
                }
            }
        }
    }

    public void westMerge(TerrainNode terrainNode) {
        if (!terrainNode.eastCompatible.isEmpty()) { //if the northerntiles southCompatible list is not empty
            for (Integer integer: westCompatible) {
                if (terrainNode.eastCompatible.contains(leftTileCompatibleZones(integer))) {
                    eastCompatible.clear();
                    terrainNode.westCompatible.clear();
                    this.mergeTerrainNodes(terrainNode);
                    //or
                    this.area.mergeArea(terrainNode.getArea());
                }
            }
        }
    }

    public void southMerge(TerrainNode terrainNode) {
        if (!terrainNode.northCompatible.isEmpty()) { //if the northerntiles southCompatible list is not empty
            for (Integer integer: southCompatible) {
                if (terrainNode.northCompatible.contains(belowTileCompatibleZones(integer))) {
                    southCompatible.clear();
                    terrainNode.northCompatible.clear();
                    this.mergeTerrainNodes(terrainNode);
                    //or
                    this.area.mergeArea(terrainNode.getArea());
                }
            }
        }
    }

    private Integer aboveTileCompatibleZones(Integer zone) {
        Integer compatible = 0;
        switch (zone) {
            case 1: compatible = 7;
                break;
            case 2: compatible = 8;
                break;
            case 3: compatible = 9;
                break;
        }
        return compatible;
    }

    private Integer rightTileCompatibleZones(Integer zone) {
        Integer compatible = 0;
        switch (zone) {
            case 3: compatible = 1;
                break;
            case 6: compatible = 4;
                break;
            case 9: compatible = 7;
                break;
        }
        return compatible;
    }

    private Integer leftTileCompatibleZones(Integer zone) {
        Integer compatible = 0;
        switch (zone) {
            case 1: compatible = 3;
                break;
            case 4: compatible = 6;
                break;
            case 7: compatible = 9;
                break;
        }
        return compatible;
    }

    private Integer belowTileCompatibleZones(Integer zone) {
        Integer compatible = 0;
        switch (zone) {
            case 7: compatible = 1;
                break;
            case 8: compatible = 2;
                break;
            case 9: compatible = 3;
                break;
        }
        return compatible;
    }


    public Area getArea(){
        return this.area;
    }

    public void setArea(Area area){
        this.area = area;
    }

    public abstract Area createArea();

    public List<TerrainNode> getTerrainNodeList() {
        return terrainNodeList;
    }
    public List<Integer> getCanConnectTo() {
        return canConnectTo;
    }
    public List<Integer> getZones() {
        return zones;
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
