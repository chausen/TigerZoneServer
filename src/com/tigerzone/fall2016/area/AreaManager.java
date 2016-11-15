package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.*;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;

import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private GameBoard gameGameBoard;

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;
    }

    public void placeTile(Point2D position, PlayableTile playableTile) {
        BoardTile boardTile = convertToBoardTile(playableTile);
        gameGameBoard.placeTile(position, boardTile);
        AreaBuilder areaBuilder = new AreaBuilder(gameGameBoard, boardTile);
        List<Area> newAreas = areaBuilder.build(position);
    }

    private void areaMerge(Point2D position, BoardTile boardTile1) {
        gameGameBoard.getTile(position); //edit
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
    }



}
