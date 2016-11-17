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
        assertTrue(areaManager.getLakeAreas().size() == 1);
        areaManager.addTile(new Point(1,0), new PlayableTile("LLLL-"), 0);
        assertTrue(areaManager.getLakeAreas().size() == 1);
    }

    @Test
    public void addTile2() throws Exception {
        areaManager.addTile(new Point(0,-1), new PlayableTile("TJTT-"), 270);
        areaManager.addTile(new Point(0,-2), new PlayableTile("TJJT-"), 180);
        areaManager.addTile(new Point(0,-3), new PlayableTile("TLLTB"), 0);
        assertTrue(areaManager.getJungleAreas().size() == 4);
        assertTrue(areaManager.getLakeAreas().size() == 2);
        assertTrue(areaManager.getTrailAreas().size() == 4);
        assertTrue(areaManager.getDenAreas().size() == 0);
    }

}