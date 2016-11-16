package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/12/16.
 */
public class DenAreaTest {
    private Area denArea;
    private Predator crocodile;
    private Predator tiger;

    @Before
    public void setUp() throws Exception {
        PlayableTile areaTile1 = new PlayableTile("TTTT-");
        denArea = new DenArea(new Point(0,0));
        crocodile = new Crocodile();
        tiger = new Tiger("0");
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = denArea.isPredatorPlaceable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = false;
        boolean actualResult = denArea.isPredatorPlaceable(crocodile);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCPlacable() throws Exception {

    }

    @Test
    public void testIsAnimalPlacable() throws Exception {

    }

    @Test
    public void testIsComplete() throws Exception {

    }
}