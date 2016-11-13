package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public class AreaTest {
    private Area area;

    @Before
    public void setUp() throws Exception {
        AreaTile areaTile1 = new AreaTile(
            new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
            new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
            new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
            new Edge(new JungleTerrain(), new TrailTerrain(), new JungleTerrain()),
            new TrailTerrain());

        area = new JungleArea(new Point2D(0,0), areaTile1, new HashMap<Point2D, FreeSpace>());
    }

    @Test
    public void testGetOwnerID() throws Exception {

    }

    @Test
    public void testPlaceTiger() throws Exception {
        Tiger t1 = new Tiger(1);
        Tiger t2 = new Tiger(1);

        Tiger t3 = new Tiger(0);
        Tiger t4 = new Tiger(0);

        Tiger t6 = new Tiger(2);
        Tiger t7 = new Tiger(2);

        area.placePredator(t1);
        area.placePredator(t2);
        area.placePredator(t3);
        area.placePredator(t4);
        area.placePredator(t6);
        area.placePredator(t7);
        List<Integer> expectedOwners = new ArrayList<>();
        expectedOwners.add(0);
        expectedOwners.add(1);
        expectedOwners.add(2);

        List<Integer> actualOwners = area.getOwnerID();
        assertEquals(expectedOwners,actualOwners);
    }
}