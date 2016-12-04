package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Tiger extends Predator{
    public Tiger(Player owner) {
        super(owner);
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
    public boolean placeInArea(Area area) {
        return area.placePlaceableAnimal(this);
    }
}
