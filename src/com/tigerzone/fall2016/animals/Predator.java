package com.tigerzone.fall2016.animals;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public abstract class Predator extends Animal{
    public abstract boolean placeableInDen();
    public abstract boolean placeableInJungle();
    public abstract boolean placeableInTrail();
    public abstract boolean placeableInLake();
    public abstract void accept(Area area);
}
