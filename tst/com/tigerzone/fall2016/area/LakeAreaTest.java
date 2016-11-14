package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import org.junit.Before;
import org.junit.Test;

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
        lakeArea = new LakeArea();
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