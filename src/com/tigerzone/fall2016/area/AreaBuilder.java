package com.tigerzone.fall2016.area;

import com.sun.javafx.geom.Point2D;
import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.tileplacement.Board;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

/**
 * Created by lenovo on 11/15/2016.
 */
public class AreaBuilder {
    Board gameBoard;
    Point2D position;



    public AreaBuilder(Board gameBoard, Point2D position) {
        this.gameBoard = gameBoard;
        this.position=position;
    }

    public Area buildNorthArea() {

        BoardTile baseTile = gameBoard.getTile(position);
        BoardTile northTile = gameBoard.getAboveAdjacentTile(position);

        return null;
    }

}
