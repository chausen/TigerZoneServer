package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
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
    private Predator tiger;
    private Predator crocodile;

    @Before
    public void setUp() throws Exception {
        area = new JungleArea();
        tiger = new Tiger(0);
        crocodile = new Crocodile();
    }

    @Test
    public void testGetOwnerID() throws Exception {

    }

    @Test
    public void testPlacePredatorCrocodile() throws Exception {
        area.placePredator(crocodile);
    }

    @Test
    public void testPlacePredatorTiger() throws Exception {
        area.placePredator(tiger);
    }

    @Test
    public void testGetSize() throws Exception {

    }
}