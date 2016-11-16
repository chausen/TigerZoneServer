package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class LakeAreaTest {
    private LakeArea lakeArea;
    private Predator crocodile;
    private Predator tiger;
    private Animal boar;
    private Animal buffalo;
    private Animal deer;

    @Before
    public void setUp() throws Exception {
        lakeArea = new LakeArea();

        crocodile = new Crocodile();
        tiger = new Tiger("0");

        boar = new Boar();
        buffalo = new Buffalo();
        deer = new Deer();
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = true;

        boolean actualResult = lakeArea.isPredatorPlaceable(crocodile);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;

        boolean actualResult = lakeArea.isPredatorPlaceable(tiger);
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
    public void testAddBoarFromAreaTile() throws Exception {
        int expectedUniquePreyCount = 1;

        lakeArea.addAnimal(boar);
        int actualNumberOfUniquePrey = lakeArea.getNumOfUniquePreyAnimalsAfterCrocodileEffect();
        assertEquals(expectedUniquePreyCount, actualNumberOfUniquePrey);
    }

    @Test
    public void testAddBuffaloFromAreaTile() throws Exception {
        int expectedUniquePreyCount = 1;

        lakeArea.addAnimal(buffalo);
        int actualNumberOfUniquePrey = lakeArea.getNumOfUniquePreyAnimalsAfterCrocodileEffect();
        assertEquals(expectedUniquePreyCount, actualNumberOfUniquePrey);
    }

    @Test
    public void testAddDeerFromAreaTile() throws Exception {
        int expectedUniquePreyCount = 1;

        lakeArea.addAnimal(deer);
        int actualNumberOfUniquePrey = lakeArea.getNumOfUniquePreyAnimalsAfterCrocodileEffect();
        assertEquals(expectedUniquePreyCount, actualNumberOfUniquePrey);
    }

    @Test
    public void testAddCrocodileFromAreaTile() throws Exception {
        lakeArea.addAnimal(crocodile);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}