package com.tigerzone.fall2016.tileplacement;

import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.tile.terrain.*;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FreeSpaceBoardTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void needToRemove() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(new Point2D(1.0, 0.0),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile2 = new AreaTile(null,
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new JungleTerrain());
        freeSpaceBoard.placeTile(areaTile1);
        assertTrue(freeSpaceBoard.needToRemove(areaTile2));
    }

    @Test
    public void isPlaceable() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(new Point2D(0.0, 0.0),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile2 = new AreaTile(new Point2D(0.0, 1.0),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new JungleTerrain());
        assertFalse(freeSpaceBoard.isPlaceable(areaTile1));
        assertTrue(freeSpaceBoard.isPlaceable(areaTile2));
    }

    @Test
    public void placeTile() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(new Point2D(0.0, 1.0),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new TrailTerrain());
        AreaTile areaTile2 = new AreaTile(new Point2D(-1.0, 0.0),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new JungleTerrain());
        AreaTile areaTile3 = new AreaTile(new Point2D(-1.0, -1.0),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new TrailTerrain());
        AreaTile areaTile4 = new AreaTile(new Point2D(-1.0, -2.0),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new LakeTerrain());
        AreaTile areaTile5 = new AreaTile(new Point2D(0.0, -1.0),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new Edge(new LakeTerrain(), new LakeTerrain(), new LakeTerrain()),
                new LakeTerrain());
        assertTrue(freeSpaceBoard.isPlaceable(areaTile1));
        freeSpaceBoard.placeTile(areaTile1);
        assertTrue(freeSpaceBoard.isPlaceable(areaTile2));
        freeSpaceBoard.placeTile(areaTile2);
        assertTrue(freeSpaceBoard.isPlaceable(areaTile3));
        freeSpaceBoard.placeTile(areaTile3);
        FreeSpace freeSpace = freeSpaceBoard.freeSpaceMap.get(new Point2D(0.0, -1.0));
        assertTrue(freeSpace.getNorthTerrain().visit(new TrailTerrain()));
        assertTrue(freeSpace.getEastTerrain().visit(new LakeTerrain()));
        assertTrue(freeSpaceBoard.isPlaceable(areaTile4));
        freeSpaceBoard.placeTile(areaTile4);
        assertEquals(freeSpaceBoard.freeSpaceMap.size(), 10);
        assertFalse(freeSpaceBoard.isPlaceable(areaTile5));
    }

}