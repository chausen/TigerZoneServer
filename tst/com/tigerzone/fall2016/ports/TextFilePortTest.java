package com.tigerzone.fall2016.ports;
import com.tigerzone.fall2016.tileplacement.terrain.*;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import jdk.nashorn.internal.objects.annotations.Function;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by Jeff on 2016/11/10.
 */
public class TextFilePortTest {
    TextFilePort tfp;
    @org.junit.Before
    public void setUp() throws Exception {
        tfp = new TextFilePort();
    }

    @Test
    public void createTiles() throws Exception{
        LinkedList<PlayableTile> ll = tfp.createTiles();
        Assert.assertNotNull(ll);//We have an actual LinkedList.
        PlayableTile origin = ll.getFirst();
        Assert.assertNotNull(origin);//We have an element.
        /*
        //Start Origin Tile Testing
        Assert.assertTrue(origin.getNorthFace().visit(new TrailTerrain()));
        Assert.assertTrue(origin.getCenterTerrain().visit(new TrailTerrain()));
        Assert.assertTrue(origin.getSouthFace().visit(new TrailTerrain()));
        Assert.assertTrue(origin.getWestFace().visit(new JungleTerrain()));
        Assert.assertTrue(origin.getEastFace().visit(new LakeTerrain()));*/
        Assert.assertEquals("TLTJ-", origin.getTileString());
        //End Origin Tile Testing
        Assert.assertEquals(77, ll.size());//Assert we have 77 tiles total.
    }
}
