package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Predator;
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

        TerrainNode predatorPlacementNode = boardTile.getTerrainNodeFromZoneIndex(predatorPlacementZone);
        if (predatorPlacementNode.getMinimumZoneValue()!=predatorPlacementZone) {
            System.out.println("Player forfeits");
        } else if (!predatorPlacementNode.getArea().isPredatorPlaceable(predator)) {
            System.out.println("Player forfeits");
        } else {
            predatorPlacementNode.getArea().placePredator(predator);
        }
    }
}
