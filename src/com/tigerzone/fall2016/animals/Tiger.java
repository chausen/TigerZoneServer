package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Tiger extends Predator {
    private String playerId;

    public Tiger(String playerId){
        this.playerId = playerId;
    }

    public String getPlayerId(){
        return this.playerId;
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
}
