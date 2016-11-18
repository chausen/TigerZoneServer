package com.tigerzone.fall2016.area.terrainnode;

import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.area.TrailArea;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 11/16/2016.
 */
public class TerrainNodeTest {

    TrailTerrainNode tn;

    @Before
    public void setUp() throws Exception {
        TrailTerrainNode terrainNode = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(2,8)), new ArrayList<Integer>(Arrays.asList(8,2)));
        tn=terrainNode;
    }

    @Test
    public void testRotateTerrainNode() throws Exception {
        tn.rotateZones(180);
       // assertTrue(tn.getZones().equals(new ArrayList<Integer>(Arrays.asList(6,4))));
        System.out.println(tn.getZones().get(0) + " " + tn.getZones().get(1));

    }


    @Test
    public void testPlaceTiger() throws Exception {


    }
}