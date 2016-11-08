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
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new FarmTerrain());
        AreaTile areaTile2 = new AreaTile(null,
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new FarmTerrain());
        freeSpaceBoard.placeTile(areaTile1);
        assertTrue(freeSpaceBoard.needToRemove(areaTile2));
    }

    @Test
    public void isPlaceable() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile1 = new AreaTile(new Point2D(0.0, 0.0),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new FarmTerrain());
        AreaTile areaTile2 = new AreaTile(new Point2D(0.0, 1.0),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new FarmTerrain());
        assertFalse(freeSpaceBoard.isPlaceable(areaTile1));
        assertTrue(freeSpaceBoard.isPlaceable(areaTile2));
    }

    @Test
    public void placeTile() throws Exception {
        FreeSpaceBoard freeSpaceBoard = new FreeSpaceBoard();
        AreaTile areaTile = new AreaTile(new Point2D(0.0, 1.0),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new RoadTerrain(), new FarmTerrain()),
                new FarmTerrain());
        freeSpaceBoard.placeTile(areaTile);
        assertTrue(freeSpaceBoard.freeSpaceMap.size() == 6);
    }

}