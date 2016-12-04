package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public class Buffalo extends Prey {
    @Override
    public void addToArea(Area area){ area.addAnimal(this);}

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
        return true;
    }

    @Override
    public boolean isGoat() {
        return false;
    }
}
