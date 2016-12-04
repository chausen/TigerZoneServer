package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by matthewdiaz on 12/3/16.
 */
public interface PlaceableAnimal {
    boolean placeableInDen();
    boolean placeableInJungle();
    boolean placeableInTrail();
    boolean placeableInLake();
    boolean placeInArea(Area area);
}
