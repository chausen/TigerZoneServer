package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class LakeAreaTest {
    private Area lakeArea;
    private Predator crocodile;
    private Predator tiger;

    @Before
    public void setUp() throws Exception {
        AreaTile areaTile1 = new AreaTile(
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
                new TrailTerrain());
        lakeArea = new LakeArea(new Point2D(0,0), areaTile1, new HashMap<Point2D, FreeSpace>());
        crocodile = new Crocodile();
        tiger = new Tiger(0);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = true;

        boolean actualResult = lakeArea.isPredatorPlacable(crocodile);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;

        boolean actualResult = lakeArea.isPredatorPlacable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPlacePredatorTiger() throws Exception{
        lakeArea.placePredator(tiger);
    }

    @Test
    public void testPlacePredatorCrocodile() throws Exception{
        lakeArea.placePredator(crocodile);
    }

    @Test
    public void testAddAnimalFromAnimalAreaTile() throws Exception {

    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}