package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by lenovo on 11/12/2016.
 */

public class GameBoard { //make GameBoard a singleton?

    private HashMap<Point, BoardTile> board = new HashMap<>();

    public void placeTile(Point position, BoardTile tile) {
        if (board.get(position) == null) {
            board.put(position, tile);
        } else {
            System.out.println("there is already a tile there"); // TODO: 11/12/2016 alert GM that an illegal move has been made?
        }
    }

    //at some point perhaps we just go through each tile and check adjacent

    public BoardTile getTile(Point boardPosition) {
        return board.get(boardPosition);
    }

    public String getPointRepresentation(Set<BoardTile> boardtiles){
        StringBuilder sb = new StringBuilder("TILES [");
        for(Point p : board.keySet()){
            for(BoardTile t : boardtiles) {
                if (board.get(p) == t) {
                    sb.append("(");
                    sb.append(p.getX());
                    sb.append(",");
                    sb.append(p.getY());
                    sb.append(")");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public BoardTile getRightAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = new Point((int)boardPosition.getX() + 1, (int)boardPosition.getY());
        return getTile(adjacentTilePosition);
    }

    public BoardTile getLeftAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = new Point((int)boardPosition.getX() - 1, (int)boardPosition.getY());
        return getTile(adjacentTilePosition);
    }

    public BoardTile getAboveAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = new Point((int)boardPosition.getX(), (int)boardPosition.getY() + 1);
        return getTile(adjacentTilePosition);
    }

    public BoardTile getBelowAdjacentTile(Point boardPosition) {
        Point adjacentTilePosition = new Point((int)boardPosition.getX(), (int)boardPosition.getY() - 1);
        return getTile(adjacentTilePosition);
    }

}
