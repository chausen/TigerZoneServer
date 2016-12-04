package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by matthewdiaz on 12/3/16.
 */
public class Goat extends Prey implements PlaceableAnimal{
    private Player owner;

    public Goat(Player owner){
        this.owner = owner;
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

    public Player getOwner(){
        return this.owner;
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
    public boolean placeableInTrail() {
        return true;
    }

    @Override
    public boolean placeableInLake() {
        return true;
    }

    @Override
    public boolean placeInArea(Area area) {
        return area.placePlaceableAnimal(this);
    }
}
