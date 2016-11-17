package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/16/2016.
 */
public class AreaManagerTest {

    AreaManager areaManager;

    @Before
    public void setUp() throws Exception {
        areaManager = new AreaManager();
    }

    @Test
    public void addTile() throws Exception {
        areaManager.addTile(new Point(-1,0), new PlayableTile("TJTJ-"), 0);
        assertTrue(areaManager.getTrailAreas().size() ==  2);
        assertTrue(areaManager.getJungleAreas().size() == 3);
        areaManager.addTile(new Point(0,1), new PlayableTile("TJTT-"), 90);
        assertTrue(areaManager.getJungleAreas().size() == 4);
    }

}