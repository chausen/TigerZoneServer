package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class JungleAreaTest {
    private Area jungleArea;
    private Predator crocodile;
    private Predator tiger;

    @Before
    public void setUp() throws Exception {
        PlayableTile areaTile1 = new PlayableTile("TTTT-");
        jungleArea = new JungleArea();
        crocodile = new Crocodile();
        tiger = new Tiger(0);
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = jungleArea.isPredatorPlacable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = false;
        boolean actualResult = jungleArea.isPredatorPlacable(crocodile);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}