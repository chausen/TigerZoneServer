package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.terrainnode.LakeTerrainNode;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public class JungleAreaTest {
    private Area jungleArea;
    private PlaceableAnimal crocodile;
    private PlaceableAnimal tiger;
    private Player owner;

    @Before
    public void setUp() throws Exception {
        PlayableTile areaTile1 = new PlayableTile("TTTT-");
        jungleArea = new JungleArea();
        crocodile = new Crocodile();
        owner = new Player("matt");
        tiger = new Tiger(owner);
    }

    @Test
    public void testIsTigerPlacable() throws Exception {
        boolean expectedResult = true;
        boolean actualResult = jungleArea.isAnimalPlaceable(tiger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsCrocodilePlacable() throws Exception {
        boolean expectedResult = false;
        boolean actualResult = jungleArea.isAnimalPlaceable(crocodile);
        String me = null;


        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsComplete() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }

    @Test
    public void testFindLakeAreas() throws Exception {
        LakeArea lakeArea = new LakeArea();
        LakeTerrainNode lakeTerrainNode = new LakeTerrainNode(null, Arrays.asList(2));
        LakeTerrainNode lakeTerrainNode2 = new LakeTerrainNode(null, Arrays.asList(8));
    }

    @Test
    public void testFindDenAreas() throws Exception {

    }
}