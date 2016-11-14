package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class LakeAreaTest {
    private CrocodileFriendlyArea lakeArea;
    private Predator crocodile;
    private Predator tiger;
    private Prey deer;

    @Before
    public void setUp() throws Exception {
        lakeArea = new LakeArea();
        crocodile = new Crocodile();
        tiger = new Tiger(0);
        deer = new Deer();
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
        int expectedTigerListSize = 1;

        lakeArea.placePredator(tiger);
        int actualTigerListSize = lakeArea.numOfTigersInArea();
        assertEquals(expectedTigerListSize, actualTigerListSize);
    }

    @Test
    public void testPlacePredatorCrocodile() throws Exception{
        int expectedCrocodileListSize = 1;

        lakeArea.placePredator(crocodile);
        int actualCrocodileListSize = lakeArea.getNumOfCrocodiles();
        assertEquals(expectedCrocodileListSize, actualCrocodileListSize);
    }

    @Test
    public void testAddPreyFromAreaTile() throws Exception {
        int expectedPreyCount = 1;

        lakeArea.addAnimalFromAreaTile(deer);
        int actualPreyCount = lakeArea.getNumOfPrey();
        assertEquals(expectedPreyCount, actualPreyCount);
    }

    @Test
    public void testAddCrocodileFromAreaTile() throws Exception {
        lakeArea.addAnimalFromAreaTile(crocodile);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}