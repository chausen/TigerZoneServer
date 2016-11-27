package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public abstract class Prey extends Animal{
    public abstract boolean isDeer(Deer deer);
    public abstract boolean isBoar(Boar boar);
    public abstract boolean isBuffalo(Buffalo buffalo);
}

