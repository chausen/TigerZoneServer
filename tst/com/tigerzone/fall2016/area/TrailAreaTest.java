package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.gamesystem.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class TrailAreaTest {
    private TrailArea trailArea;
    private Predator crocodile;
    private Predator tiger;
    private Animal deer;
    private Player owner;

    @Before
    public void setUp() throws Exception {
        owner = new Player("alan");
        trailArea = new TrailArea();
        crocodile = new Crocodile();
        tiger = new Tiger(owner);
        deer = new Deer();
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = trailArea.isAnimalPlaceable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = trailArea.isAnimalPlaceable(crocodile);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddPreyFromAreaTile() throws Exception {
        int expectedPreyCount = 1;

        trailArea.addAnimal(deer);
        int actualNumberOfPrey = trailArea.getNumOfPreyAfterCrocodileEffect();
        assertEquals(expectedPreyCount, actualNumberOfPrey);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }
}