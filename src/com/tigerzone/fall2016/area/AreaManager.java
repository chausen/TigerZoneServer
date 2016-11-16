package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private GameBoard gameBoard;

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;

    }

    public void addTile(Point position, PlayableTile playableTile) {
        BoardTile boardTile = convertToBoardTile(playableTile);
        gameBoard.placeTile(position, boardTile);
        AreaBuilder areaBuilder = new AreaBuilder(gameBoard, boardTile);
        Set<Area> newAreas = areaBuilder.build(position);
        for(Area area: newAreas){
            area.addToAppropriateList(trailAreas, jungleAreas, lakeAreas);
        }
    }


    public void updateAreas(Point position) {
        gameBoard.getTile(position);
        gameBoard.getAboveAdjacentTile(position);
        gameBoard.getRightAdjacentTile(position);
        gameBoard.getAboveAdjacentTile(position);
        gameBoard.getBelowAdjacentTile(position);

        updateNorth(gameBoard.getTile(position), gameBoard.getAboveAdjacentTile(position));


    }

    public void updateNorth(BoardTile placedTile, BoardTile northTile) {
        for (TerrainNode placedtileNode: placedTile.getTerrainNodeList()) {
            for (TerrainNode northtileNode: northTile.getTerrainNodeList()) {
                placedtileNode.northMerge(northtileNode);
            }
        }
    }
    public void updateEast(BoardTile placedTile, BoardTile eastTile) {
        for (TerrainNode placedtileNode: placedTile.getTerrainNodeList()) {
            for (TerrainNode easttileNode: eastTile.getTerrainNodeList()) {
                placedtileNode.eastMerge(easttileNode);
            }
        }
    }
    public void updateSouth(BoardTile placedTile, BoardTile southTile) {
        for (TerrainNode placedtileNode: placedTile.getTerrainNodeList()) {
            for (TerrainNode southtileNode: southTile.getTerrainNodeList()) {
                placedtileNode.southMerge(southtileNode);
            }
        }
    }
    public void updateWest(BoardTile placedTile, BoardTile westTile) {
        for (TerrainNode placedtileNode: placedTile.getTerrainNodeList()) {
            for (TerrainNode westtileNode: westTile.getTerrainNodeList()) {
                placedtileNode.westMerge(westtileNode);
            }
        }
    }



    private List<DenArea> denAreas;
    private List<JungleArea> jungleAreas;
    private List<LakeArea> lakeAreas;
    private List<TrailArea> trailAreas;

    public AreaManager(List<DenArea> denAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas, List<TrailArea> trailAreas) {
        this.denAreas = denAreas;
        this.jungleAreas = jungleAreas;
        this.lakeAreas = lakeAreas;
        this.trailAreas = trailAreas;
        gameBoard = new GameBoard();
    }



}
