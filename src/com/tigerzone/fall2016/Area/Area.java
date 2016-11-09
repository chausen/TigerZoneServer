package com.tigerzone.fall2016.Area;

import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;

import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area {

    private List<FreeSpace> freeSpaces;
//    private List<Followers> followerList;

    public Area() {
    }

    public int getOwnerID() {
        return 0;
    }

    public void addTile(AreaTile areaTile) {
    }

    public boolean hasAreaUpdated() {
        return false;
    }

    abstract boolean isComplete();

}
