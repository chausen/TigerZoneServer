package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public abstract class Predator extends Animal implements PlaceableAnimal{
    private Player owner;

    public Predator(){this.owner = null;}
    public Predator(Player owner){
        this.owner = owner;
    }

    @Override
    public boolean placeableInTrail() {
        return true;
    }

    @Override
    public boolean placeableInLake() {
        return true;
    }

    public Player getOwner(){
        return this.owner;
    }
}
