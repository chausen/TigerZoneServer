package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.tile.terrain.CityTerrain;
import com.tigerzone.fall2016.tileplacement.tile.terrain.Edge;
import com.tigerzone.fall2016.tileplacement.tile.terrain.FarmTerrain;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/7/2016.
 */
public class AreaTileTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void rotateCW() throws Exception {
        AreaTile areaTile = new AreaTile(new Point2D(0.0, 0.0),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new CityTerrain(), new CityTerrain(), new CityTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new Edge(new FarmTerrain(), new FarmTerrain(), new FarmTerrain()),
                new FarmTerrain());
        areaTile.rotateCW();
    }

    @Test
    public void rotateCCW() throws Exception {

    }

}