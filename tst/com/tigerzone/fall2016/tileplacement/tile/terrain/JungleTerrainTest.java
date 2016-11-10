package com.tigerzone.fall2016.tileplacement.tile.terrain;

import com.tigerzone.fall2016.tileplacement.terrain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/8/2016.
 */
public class JungleTerrainTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void accept() throws Exception {
        LakeTerrain lakeTerrain = new LakeTerrain();
        DenTerrain denTerrain = new DenTerrain();
        JungleTerrain jungleTerrain = new JungleTerrain();
        TrailTerrain trailTerrain = new TrailTerrain();
        FreeTerain freeTerain = new FreeTerain();
        assertFalse(jungleTerrain.accept(lakeTerrain));
        assertFalse(jungleTerrain.accept(denTerrain));
        assertTrue(jungleTerrain.accept(jungleTerrain));
        assertFalse(jungleTerrain.accept(trailTerrain));
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