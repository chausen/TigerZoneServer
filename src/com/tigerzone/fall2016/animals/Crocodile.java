package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Crocodile extends Predator{
    public Crocodile(){}
    public Crocodile(Player owner) {
        super(owner);
    }

    @Override
    public boolean placeableInDen() {
        return false;
    }

    @Override
    public boolean placeableInJungle() {
        return false;
    }

    @Override
    public boolean placeInArea(Area area) {
        return area.placePlaceableAnimal(this);
    }

    @Override
    public void addToArea(Area area){ area.addAnimal(this);}

    public boolean hasOwner() {
        if(getOwner()!= null) {
            return true;
        } else {
            return false;
        }
    }
}