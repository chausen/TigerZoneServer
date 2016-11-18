package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Tiger extends Predator {
    private Player owner;

    public Tiger(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean placeableInDen() {
        return true;
    }

    @Override
    public boolean placeableInJungle() {
        return true;
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
        area.placePredator(this);
    }

    public Player getOwner(){
        return this.owner;
    }
}
