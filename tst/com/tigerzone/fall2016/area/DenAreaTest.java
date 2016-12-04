package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/12/16.
 */
public class DenAreaTest {
    private Area denArea;
    private Predator crocodile;
    private Predator tiger;
    private Player owner;

    @Before
    public void setUp() throws Exception {
        PlayableTile areaTile1 = new PlayableTile("TTTT-");
        denArea = new DenArea(new Point(0,0));
        crocodile = new Crocodile();
        owner = new Player("matt");
        tiger = new Tiger(owner);
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = denArea.isAnimalPlaceable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = false;
        boolean actualResult = denArea.isAnimalPlaceable(crocodile);
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