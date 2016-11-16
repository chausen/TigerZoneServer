package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by lenovo on 11/12/2016.
 */

public class GameBoard { //make GameBoard a singleton?

    private AreaManager areaManager;
    private HashMap<Point, BoardTile> board = new HashMap<>();

    public void placeTile(Point position, BoardTile tile) {
        if (board.get(position) == null) {
            board.put(position, tile);

            areaManager.updateAreas(position);
            getleftAdjacentTile(position);
            getRightAdjacentTile(position);
            getAboveAdjacentTile(position);
            getBelowAdjacentTile(position);

        } else {
            System.out.println("there is already a tile there"); // TODO: 11/12/2016 alert GM that an illegal move has been made?
        }
    }

    //at some point perhaps we just go through each tile and check adjacent

    public BoardTile getTile(Point boardPosition) {
        return board.get(boardPosition);
    }

    public BoardTile getRightAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = boardPosition;
        adjacentTilePosition.translate(1,0);
        return getTile(adjacentTilePosition);
    }

    public BoardTile getleftAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = boardPosition;
        adjacentTilePosition.translate(-1,0);
        return getTile(adjacentTilePosition);
    }

    public BoardTile getAboveAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = boardPosition;
        adjacentTilePosition.translate(0,1);
        return getTile(adjacentTilePosition);
    }

    public BoardTile getBelowAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = boardPosition;
        adjacentTilePosition.translate(0,-1);
        return getTile(adjacentTilePosition);
    }

}
