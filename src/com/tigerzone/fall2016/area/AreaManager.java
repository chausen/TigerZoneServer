package com.tigerzone.fall2016.area;


import com.tigerzone.fall2016.animals.Placeable;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.*;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private GameBoard gameBoard;

    private Set<DenArea> denAreas;
    private Set<JungleArea> jungleAreas;
    private Set<LakeArea> lakeAreas;
    private Set<TrailArea> trailAreas;
    private Scorer scorer;

    public AreaManager(Scorer scorer) {
        this.denAreas = new HashSet<>();
        this.jungleAreas = new HashSet<>();
        this.lakeAreas = new HashSet<>();
        this.trailAreas = new HashSet<>();
        this.gameBoard = new GameBoard();
        this.scorer = scorer;
        addTile(new Point(0,0), new PlayableTile("TLTJ-"), 0);
    }

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;

    }

    private void addNewAreas(Set<Area> newAreas){
        if(!newAreas.isEmpty()) {
            for (Area area : newAreas) {
                if(area != null) {
                    area.addToAppropriateSet(trailAreas, jungleAreas, lakeAreas);
                }
            }
        }
    }

    private void deleteAreas(Set<Area> deletedAreas){
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
    }

    private void addUpdatedAreas(Set<Area> updatedAreas){
        for(Area area : updatedAreas){
            area.addToAppropriateSet(trailAreas, jungleAreas, lakeAreas);
        }
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
        addNewAreas(newAreas);
      // updatedAreas.removeAll(deletedAreas);
        deleteAreas(deletedAreas);
        addUpdatedAreas(updatedAreas);
        for(Area area : updatedAreas){
            if(area.isComplete() && area.hasOwner()){
                area.acceptScorer(scorer);
            }
        }
        if(playableTile.getTileString().contains("X")){
            TerrainNode denTerrainNode = boardTile.getTerrainNode(5);
            Area denArea = new DenArea(position);
            denArea.addBoardTile(boardTile);
            denTerrainNode.setArea(denArea);
            denAreas.add((DenArea)denArea);
        }
        updateDenArea();
        for(DenArea denArea: denAreas){
            if(denArea.isComplete() && denArea.hasOwner() && !denArea.isDenScored()){
                denArea.acceptScorer(scorer);
            }
        }
    }

    public boolean addTile(Point position, PlayableTile playableTile, Placeable animal, int animalPlacementZone, int degrees) {
        BoardTile boardTile = convertToBoardTile(playableTile);
        boardTile.rotateCCW(degrees);
        gameBoard.placeTile(position, boardTile);
        AreaBuilder areaBuilder = new AreaBuilder(gameBoard, boardTile);
        areaBuilder.build(position);
        Set<Area> newAreas = areaBuilder.buildNewAreas();
        Set<Area> updatedAreas = areaBuilder.getUpdatedAreas();
        Set<Area> deletedAreas = areaBuilder.getDeletedAreas();
        addNewAreas(newAreas);
        deleteAreas(deletedAreas);
        updatedAreas.removeAll(deletedAreas);
        addUpdatedAreas(updatedAreas);
        boolean tigerPlaceable = false;
        boolean animalPlaced = false;
        if (animalPlacementZone > 0) {
            TerrainNode tigerPlacementNode = boardTile.getTerrainNode(animalPlacementZone);
            if (tigerPlacementNode.getMinimumZoneValue() != animalPlacementZone) {
                tigerPlaceable = false;
            } else if (!tigerPlacementNode.getArea().isAnimalPlaceable(animal)) {
                tigerPlaceable = false;
            } else if (!tigerPlacementNode.getArea().canPlaceTiger()) {
                tigerPlaceable = false;
            } else {
                tigerPlacementNode.getArea().placeAnimal(animal); //need to check here as well. Changed from placePredator
                tigerPlaceable = true;
            }
        } else if (animalPlacementZone<=0){
            for (TerrainNode terrainNode: boardTile.getTerrainNodeList()) {
                if (terrainNode.getArea().isAnimalPlaceable(animal)) {
                    terrainNode.getArea().placeAnimal(animal); //changed from placePredator
                    animalPlaced = true; //was crocPlaced
                    break;
                }
                else {
                    animalPlaced = false; //was crocPlaced
                }
            }
        }
        for(Area area : updatedAreas){
            if(area.isComplete() && area.hasOwner()){
                area.acceptScorer(scorer);
            }
        }
        if(playableTile.getTileString().contains("X")){
            TerrainNode denTerrainNode = boardTile.getTerrainNode(5);
            //Area denArea = new DenArea(position);
            DenArea denArea = (DenArea)denTerrainNode.getArea();
            denArea.setCenter(position);
            denArea.addBoardTile(boardTile);
            //denTerrainNode.setArea(denArea);
            denAreas.add(denArea);
        }
        updateDenArea();
        for(DenArea denArea: denAreas){
            if(denArea.isComplete() && denArea.hasOwner() && !denArea.isDenScored()){
                denArea.acceptScorer(scorer);
            }
        }
        if (animalPlacementZone<=0) {
            return animalPlaced;
        }
        else {
            return tigerPlaceable;
        }
    }

    public boolean invalidMeeplePlacement(PlayableTile playableTile, int animalPlacementZone , int degrees){
        BoardTile boardTile = convertToBoardTile(playableTile);
        boardTile.rotateCCW(degrees);
        TerrainNode animalPlacementNode = boardTile.getTerrainNode(animalPlacementZone);
        if (animalPlacementNode.getMinimumZoneValue() != animalPlacementZone) {
            return true;
        }
        return false;
    }

    private void updateDenArea(){
        for(DenArea denArea: denAreas){
            Point denAreaCenter = denArea.getCenter();
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX(), (int)denAreaCenter.getY() + 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX(), (int)denAreaCenter.getY() + 1)));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY() + 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY() + 1)));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY())) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY())));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY() - 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() + 1, (int)denAreaCenter.getY() - 1)));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX(), (int)denAreaCenter.getY() - 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX(), (int)denAreaCenter.getY() - 1)));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY() - 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY() - 1)));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY())) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY())));
            }
            if(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY() + 1)) != null){
                denArea.addBoardTile(gameBoard.getTile(new Point((int)denAreaCenter.getX() - 1, (int)denAreaCenter.getY() + 1)));
            }
        }
    }

    /**
     * Returns the Gameboard of the AreaManager. Needed by TigerRetrieve() in GameSystem.
     * @return the Gameboard
     */
    public GameBoard getGameBoard() {
        return this.gameBoard;
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
