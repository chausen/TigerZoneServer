package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by lenovo on 12/3/2016.
 */
public class Goat extends Prey implements Placeable {

    @Override
    public void addToArea(Area area){ area.addAnimal(this);}

    @Override
    public boolean placeableInDen() {
        return false;
    }

    @Override
    public boolean placeableInJungle() {
        return false;
    }

    @Override
    public boolean placeableInTrail() {
        return true;
    }

    @Override
    public boolean placeableInLake() {
        return true;
    }

    @Override
    public void placeInArea(Area area) {
        area.placeAnimal(this);
    }

    @Override
    public boolean isDeer() {
        return false;
    }

    @Override
    public boolean isBoar() {
        return false;
    }

    @Override
    public boolean isBuffalo() {
        return false;
    }

    @Override
    public boolean isGoat() {
        return true;
    }
}
