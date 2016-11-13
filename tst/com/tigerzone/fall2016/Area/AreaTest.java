package com.tigerzone.fall2016.Area;

import com.tigerzone.fall2016.tileplacement.Tiger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public class AreaTest {
    private Area area;

    @Before
    public void setUp() throws Exception {
        area = new JungleArea();
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

        area.placeTiger(t1);
        area.placeTiger(t2);
        area.placeTiger(t3);
        area.placeTiger(t4);
        area.placeTiger(t6);
        area.placeTiger(t7);
        List<Integer> expectedOwners = new ArrayList<>();
        expectedOwners.add(0);
        expectedOwners.add(1);
        expectedOwners.add(2);

        List<Integer> actualOwners = area.getOwnerID();
        assertEquals(expectedOwners,actualOwners);
    }
}