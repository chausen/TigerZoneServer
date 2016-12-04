package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public class Boar extends Prey {
    @Override
    public void addToArea(Area area){ area.addAnimal(this);}

    @Override
    public boolean isDeer() {
        return false;
    }

    @Override
    public boolean isBoar() {
        return true;
    }

    @Override
    public boolean isBuffalo() {
        return false;
    }

    @Override
    public boolean isGoat() {
        return false;
    }
}
