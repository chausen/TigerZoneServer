package com.tigerzone.fall2016.tileplacement.tile.terrain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/8/2016.
 */
public class FarmTerrainTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void accept() throws Exception {
        CityTerrain cityTerrain = new CityTerrain();
        CloisterTerrain cloisterTerrain = new CloisterTerrain();
        FarmTerrain farmTerrain = new FarmTerrain();
        RoadTerrain roadTerrain = new RoadTerrain();
        FreeTerain freeTerain = new FreeTerain();
        assertFalse(farmTerrain.accept(cityTerrain));
        assertFalse(farmTerrain.accept(cloisterTerrain));
        assertTrue(farmTerrain.accept(farmTerrain));
        assertFalse(farmTerrain.accept(roadTerrain));
    }

    @Test
    public void visit() throws Exception {

    }

    @Test
    public void visit1() throws Exception {

    }

    @Test
    public void visit2() throws Exception {

    }

    @Test
    public void visit3() throws Exception {

    }

    @Test
    public void visit4() throws Exception {

    }

}