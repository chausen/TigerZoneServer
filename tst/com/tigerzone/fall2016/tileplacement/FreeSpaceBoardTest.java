package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.*;
import com.tigerzone.fall2016.tileplacement.terrain.*;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBoardTest {

    FreeSpaceBoard freeSpaceBoard;

    @Before
    public void setUp() throws Exception {
        freeSpaceBoard = new FreeSpaceBoard();
    }

    //This is testing the method that enables the FreeSpaceBoard
    //to know whether a tile on the stack cannot be placed.
    //The FreeSpace board starts with 4 freespaces created by the initial tile.
    //If I place a LakeTile which takes up the Lake spot of the initial tile
    //and doesnt provide any more lake edges I shouldnt be able to place an
    //all lake tile.
    @Test
    public void needToRemove() throws Exception {
        PlayableTile playableTile1 = new PlayableTile("LJJJ-", 180);
        BoardTile boardTile1 = new BoardTile(playableTile1);
        PlayableTile playableTile2 = new PlayableTile("LLLL-", 0);
        assertFalse(freeSpaceBoard.isPlaceable(new Point2D(1.0, 0.0), boardTile1));
        freeSpaceBoard.placeTile(new Point2D(1.0, 0.0), boardTile1);
        assertTrue(freeSpaceBoard.needToRemove(playableTile2));
    }


    //I shouldnt be able to place a tile at the origin but can place a tile above it
    @Test
    public void isPlaceable() throws Exception {
        BoardTile boardTile1 = new BoardTile(new PlayableTile("LLJJ-", 0));
        BoardTile boardTile2 = new BoardTile(new PlayableTile("TTTT-", 0));
        assertFalse(freeSpaceBoard.isPlaceable(new Point2D(1.0, 0.0), boardTile1));
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(0.0, 1.0), boardTile2));
    }

    //This is the most complicated test case that is going over a random game progression.
    @Test
    public void placeTile() throws Exception {
        BoardTile boardTile1 = new BoardTile(new PlayableTile("TJTJ-", 0));
        BoardTile boardTile2 = new BoardTile(new PlayableTile("TJTT-", 0));
        BoardTile boardTile3 = new BoardTile(new PlayableTile("TLLL-", 0));
        BoardTile boardTile4 = new BoardTile(new PlayableTile("LLLL-", 0));
        BoardTile boardTile5 = new BoardTile(new PlayableTile("LLLL-", 0));
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(0.0, 1.0), boardTile1));
        freeSpaceBoard.placeTile(new Point2D(0.0, 1.0), boardTile1);
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, 0.0), boardTile2));
        freeSpaceBoard.placeTile(new Point2D(-1.0, 0.0), boardTile2);
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, -1.0), boardTile3));
        freeSpaceBoard.placeTile(new Point2D(-1.0, -1.0), boardTile3);
        FreeSpace freeSpace = freeSpaceBoard.freeSpaceMap.get(new Point2D(0.0, -1.0));
        assertTrue(freeSpace.getNorthTerrain().visit(new TrailTerrain()));
        assertTrue(freeSpace.getEastTerrain().visit(new LakeTerrain()));
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, -2.0),boardTile4));
        freeSpaceBoard.placeTile(new Point2D(-1.0, -2.0),boardTile4);
        assertEquals(freeSpaceBoard.freeSpaceMap.size(), 10);
        assertFalse(freeSpaceBoard.isPlaceable(new Point2D(0.0, -1.0), boardTile5));
    }

}