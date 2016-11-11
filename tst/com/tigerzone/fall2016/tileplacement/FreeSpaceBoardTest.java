package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
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
    //If I place a LakeTile which takes up the Lake sport of the initial tile
    //and doesnt provide any more lake edges I shouldnt be able to place an
    //all lake tile.
    @Test
    public void needToRemove() throws Exception {
        AreaTile areaTile1 = new AreaTile(
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile2 = new AreaTile(
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new JungleTerrain());
        freeSpaceBoard.placeTile(new Point2D(1.0, 0.0),areaTile1);
        assertTrue(freeSpaceBoard.needToRemove(areaTile2));
    }


    //I shouldnt be able to place a tile at the origin but can place a tile above it
    @Test
    public void isPlaceable() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile2 = new AreaTile(
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new JungleTerrain());
        assertFalse(freeSpaceBoard.isPlaceable(new Point2D(1.0, 0.0), areaTile1));
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(0.0, 1.0), areaTile2));
    }

    //This is the most complicated test case that is going over a random game progression.
    @Test
    public void placeTile() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new TrailTerrain());
        AreaTile areaTile2 = new AreaTile(
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile3 = new AreaTile(
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new TrailTerrain());
        AreaTile areaTile4 = new AreaTile(
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new LakeTerrain());
        AreaTile areaTile5 = new AreaTile(
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new LakeTerrain());
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(0.0, 1.0), areaTile1));
        freeSpaceBoard.placeTile(new Point2D(0.0, 1.0), areaTile1);
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, 0.0), areaTile2));
        freeSpaceBoard.placeTile(new Point2D(-1.0, 0.0), areaTile2);
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, -1.0), areaTile3));
        freeSpaceBoard.placeTile(new Point2D(-1.0, -1.0), areaTile3);
        FreeSpace freeSpace = freeSpaceBoard.freeSpaceMap.get(new Point2D(0.0, -1.0));
        assertTrue(freeSpace.getNorthTerrain().visit(new TrailTerrain()));
        assertTrue(freeSpace.getEastTerrain().visit(new LakeTerrain()));
        assertTrue(freeSpaceBoard.isPlaceable(new Point2D(-1.0, -2.0),areaTile4));
        freeSpaceBoard.placeTile(new Point2D(-1.0, -2.0),areaTile4);
        assertEquals(freeSpaceBoard.freeSpaceMap.size(), 10);
        assertFalse(freeSpaceBoard.isPlaceable(new Point2D(0.0, -1.0), areaTile5));
    }

}