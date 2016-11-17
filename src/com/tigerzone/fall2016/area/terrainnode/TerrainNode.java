package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

import java.util.List;
import java.util.Stack;

/**
 * Created by lenovo on 11/14/2016.
 */
public abstract class TerrainNode {

    private Area area;
    private BoardTile boardTile;
    private List<Integer> canConnectTo;
    private List<Integer> zones;
    private Stack<Tiger> tigers = new Stack<>();

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
    public List<Integer> getCanConnectTo() {
        return canConnectTo;
    }
    public List<Integer> getZones() {
        return zones;
    }


    public TerrainNode(List<Integer> canConnectTo, List<Integer> zones) {
        this.canConnectTo = canConnectTo;
        this.zones = zones;
    }

    public TerrainNode() {

    }

    public boolean placeTiger(Tiger tiger) {
        boolean placed = false;
        if (this.hasTiger()) {
            if (tiger.getPlayerId().equalsIgnoreCase(tigers.peek().getPlayerId())) {
                tigers.push(tiger);
                this.getArea().placeTigerSpecialCase(tiger);
                placed = true;
            } else {
                placed = false;
            }
        }
        return placed;
    }

    public boolean removeTiger(String playerID) {
        boolean removed = false;
        if (this.hasTiger()) {
            Tiger tiger = tigers.peek();
            if (playerID.equalsIgnoreCase(tiger.getPlayerId())) {
                tigers.remove(tiger);
                this.getArea().removeTiger(tiger);
                removed = true;
            }
        }
        return removed;
    }

    public boolean hasTiger() {
        boolean hasTiger;
        if (tigers.size()>0) {
            hasTiger = true;
        } else {
            hasTiger=false;
        }
        return hasTiger;
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
