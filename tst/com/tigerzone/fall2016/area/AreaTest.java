package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.gamesystem.Player;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by matthewdiaz on 11/11/16.
 */
public class AreaTest {
    private Player owner;
    private Area area;
    private Predator tiger;
    private Predator crocodile;

    @Before
    public void setUp() throws Exception {
        owner = new Player("matt");
        area = new JungleArea();
        tiger = new Tiger(owner);
        crocodile = new Crocodile();
    }

    @Test
    public void testGetOwnerID() throws Exception {

    }

    @Test
    public void testPlacePredatorCrocodile() throws Exception {
        area.placePlaceableAnimal(crocodile);
    }

    @Test
    public void testPlacePredatorTiger() throws Exception {
        area.placePlaceableAnimal(tiger);
    }

    @Test
    public void testGetSize() throws Exception {

    }
}
