package com.tigerzone.fall2016.area;


import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private GameBoard gameBoard;

    private Set<DenArea> denAreas;
    private Set<JungleArea> jungleAreas;
    private Set<LakeArea> lakeAreas;
    private Set<TrailArea> trailAreas;

    public AreaManager() {
        this.denAreas = new HashSet<DenArea>();
        this.jungleAreas = new HashSet<JungleArea>();
        this.lakeAreas = new HashSet<LakeArea>();
        this.trailAreas = new HashSet<TrailArea>();
        this.gameBoard = new GameBoard();
        addTile(new Point(0,0), new PlayableTile("TLTJ-"), 0);
    }

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;

    }

    public void addTile(Point position, PlayableTile playableTile, int degrees){

        BoardTile boardTile = convertToBoardTile(playableTile);
        boardTile.rotateCCW(degrees);
        gameBoard.placeTile(position, boardTile);
        AreaBuilder areaBuilder = new AreaBuilder(gameBoard, boardTile);
        areaBuilder.build(position);
        Set<Area> newAreas = areaBuilder.buildNewAreas();
        Set<Area> updatedAreas = areaBuilder.getUpdatedAreas();
        Set<Area> deletedAreas = areaBuilder.getDeletedAreas();
        if(!newAreas.isEmpty()) {
            for (Area area : newAreas) {
                if(area != null) {
                    area.addToAppropriateSet(trailAreas, jungleAreas, lakeAreas);
                }
            }
        }
        for(Area area : deletedAreas){
            if(trailAreas.contains(area)){
                trailAreas.remove(area);
            }
            else if(jungleAreas.contains(area)){
                jungleAreas.remove(area);
            }
            else if(lakeAreas.contains(area)){
                lakeAreas.remove(area);
            }
        }
        for(Area area : updatedAreas){
            area.addToAppropriateSet(trailAreas, jungleAreas, lakeAreas);
        }

        if(playableTile.getTileString().contains("X")){
            TerrainNode denTerrainNode = boardTile.getTerrainNode(5);
            Area denArea = new DenArea(position);
            denArea.addBoardTile(boardTile);
            denTerrainNode.setArea(denArea);
            denAreas.add((DenArea)denArea);
        }
        placeDenArea(position, boardTile);
    }

    public boolean addTile(Point position, PlayableTile playableTile, Predator predator, int predatorPlacementZone, int degrees) {
        addTile(position, playableTile, degrees);
        BoardTile boardTile = gameBoard.getTile(position);
        if (predatorPlacementZone>0) {
            TerrainNode predatorPlacementNode = boardTile.getTerrainNode(predatorPlacementZone);
            if (predatorPlacementNode.getMinimumZoneValue() != predatorPlacementZone) {
                return false;
            } else if (!predatorPlacementNode.getArea().isPredatorPlaceable(predator)) {
                return false;
            } else {
                predatorPlacementNode.getArea().placePredator(predator); //need to check here as well
                return true;
            }
        } else if (predatorPlacementZone==0){
            boolean crocPlaced = false;
            for (TerrainNode terrainNode: boardTile.getTerrainNodeList()) {
                if (terrainNode.getArea().isPredatorPlaceable(predator)) {
                    terrainNode.getArea().placePredator(predator);
                    crocPlaced = true;
                }
            }
            return crocPlaced;
        }
        return true;
    }

    private void placeDenArea(Point position, BoardTile boardTile){
        for(DenArea denArea: denAreas){
            Point denAreaCenter = denArea.getCenter();
            if(denAreaCenter.getX() == position.getX() && denAreaCenter.getY() + 1 == position.getY()
                    || denAreaCenter.getX() + 1 == position.getX() && denAreaCenter.getY() + 1 == position.getY()
                    || denAreaCenter.getX() + 1 == position.getX() && denAreaCenter.getY() == position.getY()
                    || denAreaCenter.getX() + 1 == position.getX() && denAreaCenter.getY() - 1 == position.getY()
                    || denAreaCenter.getX() == position.getX() && denAreaCenter.getY() - 1 == position.getY()
                    || denAreaCenter.getX() - 1 == position.getX() && denAreaCenter.getY() - 1 == position.getY()
                    || denAreaCenter.getX() - 1 == position.getX() && denAreaCenter.getY() == position.getY()
                    || denAreaCenter.getX() - 1 == position.getX() && denAreaCenter.getY() + 1 == position.getY()){
                denArea.addBoardTile(boardTile);
            }
        }
    }

    /**
     * Returns the list of Den Areas
     * @return
     */
    public Set<DenArea> getDenAreas() {
        return this.denAreas;
    }

    /**
     * Returns the list of Jungle Areas
     * @return
     */
    public Set<JungleArea> getJungleAreas() {
        return this.jungleAreas;
    }

    /**
     * Returns the list of Lake Areas
     * @return
     */
    public Set<LakeArea> getLakeAreas() {
        return this.lakeAreas;
    }

    /**
     * Returns the list of Trail Areas
     * @return
     */
    public Set<TrailArea> getTrailAreas() {
        return this.trailAreas;
    }
}
