package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Goat;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.gamesystem.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public abstract class CrocodileFriendlyArea extends Area {
    private List<Crocodile> crocodileList;
    private List<Goat> goatList;

    public CrocodileFriendlyArea() {
        this.crocodileList = new ArrayList<>();
        this.goatList = new ArrayList<>();
    }


    public void mergeArea(CrocodileFriendlyArea area) {
        super.mergeArea(area);
        this.crocodileList.addAll(getCrocodileList());
    }

    /**
     * Returns true if Goat list is not empty
     * @return
     */
    private boolean hasGoatInArea(){
        return !(this.goatList.isEmpty());
    }

    /**
     * Returns true if Crocodile list is not empty
     *
     * @return
     */
    private boolean hasCrocodileInArea() {
        return !(this.crocodileList.isEmpty());
    }

    /**
     * this method should be called when user is placing a Crocodile
     *
     * @param crocodile
     */
    @Override
    public boolean placePlaceableAnimal(Crocodile crocodile) {
        if (!hasCrocodileInArea()) {
            this.addAnimal(crocodile);
            return true;
        } else if (!crocodile.hasOwner()) {
            this.addAnimal(crocodile);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method should be called when user is placing a Goat
     *
     * @param goat
     */
    @Override
    public boolean placePlaceableAnimal(Goat goat) {
        if (!hasGoatInArea()) {
            this.addAnimal(goat);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method should be called when a Crocodile is added from an AreaTile
     *
     * @param crocodile
     */
    @Override
    public void addAnimal(Crocodile crocodile) {
        this.crocodileList.add(crocodile);
    }

    /**
     * this method should be called when a Goat is added from an AreaTile
     * @param goat
     */
    @Override
    public void addAnimal(Goat goat){ this.goatList.add(goat);}

    /**
     * Returns the number of Crocodiles in this area
     *
     * @return
     */
    public int getNumOfCrocodiles() {
        return this.crocodileList.size();
    }

    public List<Crocodile> getCrocodileList() {
        return crocodileList;
    }
}