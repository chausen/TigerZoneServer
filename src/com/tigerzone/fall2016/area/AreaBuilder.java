package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Aidan on 11/15/2016.
 */
public class AreaBuilder {

    private GameBoard gameBoard;
    private BoardTile boardTile;

    private Point position;
    private Set<TerrainNode> completeTerrainNodes = new HashSet<>();

    public AreaBuilder(GameBoard gameGameBoard, BoardTile boardTile){
        this.gameBoard = gameGameBoard;
        this.boardTile = boardTile;
    }


    public Set<Area> build(Point position){
        this.position = position;
        buildNorthFace();
        buildEastFace();
        buildSouthFace();
        buildWestFace();
        return buildNewAreas();
    }

    private Set<Area> buildNewAreas() {
        Set<Area> areaSet = new HashSet<>();
        for(TerrainNode terrainNode: boardTile.getTerrainNodeList()){
            if(!completeTerrainNodes.contains(terrainNode)){
                Area area = terrainNode.createArea();
                terrainNode.createArea();
                areaSet.add(area);
            }
        }
        return areaSet;
    }

    private void buildWestFace() {
        if(gameBoard.getLeftAdjacentTile(position) != null) {
            updateTerrainNodes(1, 3); //3 = west
            updateTerrainNodes(4, 3);
            updateTerrainNodes(7, 3);
        }
    }

    private void buildSouthFace() {
        if(gameBoard.getBelowAdjacentTile(position) != null) {
            updateTerrainNodes(7, 2);
            updateTerrainNodes(8, 2);
            updateTerrainNodes(9, 2);
        }
    }

    private void buildEastFace() {
        if (gameBoard.getRightAdjacentTile(position) != null) {
            updateTerrainNodes(3, 1);
            updateTerrainNodes(6, 1);
            updateTerrainNodes(9, 1);
        }
    }

    private void buildNorthFace() {
        if(gameBoard.getAboveAdjacentTile(position) != null) {
            updateTerrainNodes(1, 0);
            updateTerrainNodes(2, 0);
            updateTerrainNodes(3, 0);
        }
    }

    private void updateTerrainNodes(int x, int dir){
        int y = -1;
        switch(x){
            case 1:
                if(dir == 0){
                    y = 7;
                }
                else{
                    y = 3;
                }
                break;
            case 2:
                y = 8;
                break;
            case 3:
                if(dir == 0){
                    y = 9;
                }
                else{
                    y = 1;
                }
                break;
            case 4:
                y = 6;
                break;
            case 5:
                break;
            case 6:
                y = 4;
                break;
            case 7:
                if(dir == 2){
                    y = 1;
                }
                else{
                    y = 9;
                }
                break;
            case 8:
                y = 2;
                break;
            case 9:
                if(dir == 2){
                    y = 3;
                }
                else{
                    y = 7;
                }
                break;
            default:
                break;
        }
        TerrainNode terrainNode = boardTile.getTerrainNode(x);
        TerrainNode terrainNodeCompared;
        if(dir == 0){
            terrainNodeCompared = gameBoard.getAboveAdjacentTile(position).getTerrainNode(y);
        }
        else if(dir == 1){
            terrainNodeCompared = gameBoard.getRightAdjacentTile(position).getTerrainNode(y);
        }
        else if(dir == 2){
            terrainNodeCompared = gameBoard.getBelowAdjacentTile(position).getTerrainNode(y);
        }
        else{
            terrainNodeCompared = gameBoard.getLeftAdjacentTile(position).getTerrainNode(y);
        }
        if(terrainNode.getCanConnectTo().contains(y) && terrainNodeCompared.getCanConnectTo().contains(x)) {
            terrainNode.getCanConnectTo().remove(y);
            terrainNodeCompared.getCanConnectTo().remove(x);
            //Area updatedArea = terrainNode.getArea();
            //make sure later that the area was actually updated
            terrainNode.getArea().mergeArea(terrainNodeCompared.getArea());
            completeTerrainNodes.add(terrainNode);
        }
    }

}

