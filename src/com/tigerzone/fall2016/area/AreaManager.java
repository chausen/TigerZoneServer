package com.tigerzone.fall2016.area;


import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private GameBoard gameBoard;

    private List<DenArea> denAreas;
    private List<JungleArea> jungleAreas;
    private List<LakeArea> lakeAreas;
    private List<TrailArea> trailAreas;
    private Map<Point, DenArea> denAreaMap;

    public AreaManager(List<DenArea> denAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas, List<TrailArea> trailAreas) {
        this.denAreas = denAreas;
        this.jungleAreas = jungleAreas;
        this.lakeAreas = lakeAreas;
        this.trailAreas = trailAreas;
        gameBoard = new GameBoard();
    }

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;

    }

    public void addTile(Point position, PlayableTile playableTile, Predator predator, int predatorPlacementZone) {
        BoardTile boardTile = convertToBoardTile(playableTile);
        gameBoard.placeTile(position, boardTile);
        AreaBuilder areaBuilder = new AreaBuilder(gameBoard, boardTile);
        Set<Area> newAreas = areaBuilder.build(position);
        for(Area area: newAreas){
            area.addToAppropriateList(trailAreas, jungleAreas, lakeAreas);
        }
        if(playableTile.getTileString().contains("X")){
            TerrainNode denTerrainNode = boardTile.getTerrainNode(5);
            Area denArea = new DenArea(position);
            denTerrainNode.setArea(denArea);
            denAreas.add((DenArea)denArea);
        }
        placeDenArea(position, boardTile);
        TerrainNode predatorPlacementNode = boardTile.getTerrainNode(predatorPlacementZone);
        if (predatorPlacementNode.getMinimumZoneValue()!=predatorPlacementZone) {
            System.out.println("Player forfeits");
        } else if (!predatorPlacementNode.getArea().isPredatorPlaceable(predator)) {
            System.out.println("Player forfeits");
        } else {
            predatorPlacementNode.getArea().placePredator(predator);
        }
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
    public List<DenArea> getDenAreas() {
        return this.denAreas;
    }

    /**
     * Returns the list of Jungle Areas
     * @return
     */
    public List<JungleArea> getJungleAreas() {
        return this.jungleAreas;
    }

    /**
     * Returns the list of Lake Areas
     * @return
     */
    public List<LakeArea> getLakeAreas() {
        return this.lakeAreas;
    }

    /**
     * Returns the list of Trail Areas
     * @return
     */
    public List<TrailArea> getTrailAreas() {
        return this.trailAreas;
    }
}
