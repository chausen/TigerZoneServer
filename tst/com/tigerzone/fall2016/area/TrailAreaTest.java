package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class TrailAreaTest {
    private Area trailArea;
    private Predator crocodile;
    private Predator tiger;

    @Before
    public void setUp() throws Exception {
        trailArea = new TrailArea();
        crocodile = new Crocodile();
        tiger = new Tiger(0);
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = trailArea.isPredatorPlacable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = trailArea.isPredatorPlacable(crocodile);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}