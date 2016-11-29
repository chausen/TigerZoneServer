package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Crocodile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by matthewdiaz on 11/13/16.
 */
public abstract class CrocodileFriendlyArea extends Area {
    private List<Crocodile> crocodileList;


    public CrocodileFriendlyArea() {
        this.crocodileList = new ArrayList<>();
    }


    public void mergeArea(CrocodileFriendlyArea area) {
        super.mergeArea(area);
        this.crocodileList.addAll(getCrocodileList());
    }


    /**
     * Returns true if list is not empty
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
    public boolean placePredator(Crocodile crocodile) {
        if (!hasCrocodileInArea()) {
            this.addAnimal(crocodile);
            return true;
        } else if (!crocodile.hasOwner()) {
            this.addAnimal(crocodile);
            return true;
        } else {
            //throw forfeit!! // TODO: 11/17/2016 add logic here

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