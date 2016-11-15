package com.tigerzone.fall2016.area;

import javafx.geometry.Point2D;
import com.tigerzone.fall2016.tileplacement.Board;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private Board gameBoard;

    private BoardTile convertToBoardTile(PlayableTile playableTile) {
        BoardTile boardTile = new BoardTile(playableTile);
        return boardTile;

    }

    public void placeTile(Point2D position, PlayableTile playableTile) {
        gameBoard.placeTile(position, convertToBoardTile(playableTile));
        BoardTile northTile = gameBoard.getAboveAdjacentTile(position);
        BoardTile westTile = gameBoard.getAboveAdjacentTile(position);
        BoardTile southTile = gameBoard.getAboveAdjacentTile(position);
        BoardTile eastTile = gameBoard.getAboveAdjacentTile(position);

    }

    public void updateAreas() {

    }

    private void areaMerge(Point2D position, BoardTile boardTile1) {
        gameBoard.getTile(position); //edit
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
