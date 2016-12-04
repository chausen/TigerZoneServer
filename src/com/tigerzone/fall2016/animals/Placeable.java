package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by lenovo on 12/3/2016.
 */
public interface Placeable {

    boolean placeableInDen();
    boolean placeableInJungle();
    boolean placeableInTrail();
    boolean placeableInLake();
    void placeInArea(Area area);
}
