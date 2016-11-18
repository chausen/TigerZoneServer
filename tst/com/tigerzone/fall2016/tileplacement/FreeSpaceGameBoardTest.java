package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.*;
import com.tigerzone.fall2016.tileplacement.terrain.*;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceGameBoardTest {

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
        PlayableTile playableTile1 = new PlayableTile("LJJJ-");
        PlayableTile playableTile2 = new PlayableTile("LLLL-");
        assertTrue(freeSpaceBoard.isPlaceable(new Point(1, 0), playableTile1, 90));
        freeSpaceBoard.placeTile(new Point(1, 0), playableTile1);
        assertTrue(freeSpaceBoard.needToRemove(playableTile2));
    }


    //I shouldnt be able to place a tile at the origin but can place a tile above it
    @Test
    public void isPlaceable() throws Exception {
        PlayableTile playableTile1 = new PlayableTile("LLJJ-");
        PlayableTile playableTil2 = new PlayableTile("TTTT-");
        assertFalse(freeSpaceBoard.isPlaceable(new Point(1, 0), playableTile1));
        assertTrue(freeSpaceBoard.isPlaceable(new Point(0, 1), playableTil2));
    }

    //This is the most complicated test case that is going over a random game progression.
    @Test
    public void placeTile() throws Exception {
        PlayableTile playableTile1 = new PlayableTile("TJTJ-");
        PlayableTile playableTile2 = new PlayableTile("TJTT-");
        PlayableTile playableTile3 = new PlayableTile("TLLL-");
        PlayableTile playableTile4 = new PlayableTile("LLLL-");
        PlayableTile playableTile5 = new PlayableTile("LLLL-");
        assertTrue(freeSpaceBoard.isPlaceable(new Point(0, 1), playableTile1));
        freeSpaceBoard.placeTile(new Point(0, 1), playableTile1);
        assertTrue(freeSpaceBoard.isPlaceable(new Point(-1, 0), playableTile2));
        freeSpaceBoard.placeTile(new Point(-1, 0), playableTile2);
        assertTrue(freeSpaceBoard.isPlaceable(new Point(-1, -1), playableTile3));
        freeSpaceBoard.placeTile(new Point(-1, -1), playableTile3);
        FreeSpace freeSpace = freeSpaceBoard.freeSpaceMap.get(new Point(0, -1));
        assertTrue(freeSpace.getNorthTerrain().visit(new TrailTerrain()));
        assertTrue(freeSpace.getEastTerrain().visit(new LakeTerrain()));
        assertTrue(freeSpaceBoard.isPlaceable(new Point(-1, -2),playableTile4));
        freeSpaceBoard.placeTile(new Point(-1, -2),playableTile4);
        assertEquals(freeSpaceBoard.freeSpaceMap.size(), 10);
        assertFalse(freeSpaceBoard.isPlaceable(new Point(0, -1), playableTile5));
    }

}