package com.tigerzone.fall2016.area;
import com.tigerzone.fall2016.animals.Predator;

import com.tigerzone.fall2016.animals.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 11/7/2016.
 */
public class TrailArea extends CrocodileFriendlyArea {

    private List<Prey> preyList;


    public TrailArea(){
        this.preyList = new ArrayList<>();
    }


    /**
     * this method should be called when a Boar is added from an AreaTile
     * @param boar
     */
    @Override
    public void addAnimalFromAreaTile(Boar boar){
        this.preyList.add(boar);
    }

    /**
     * this method should be called when a Buffalo is added from an AreaTile
     * @param buffalo
     */
    @Override
    public void addAnimalFromAreaTile(Buffalo buffalo){
        this.preyList.add(buffalo);
    }

    /**
     * this method should be called when a Deer is added from an AreaTile
     * @param deer
     */
    @Override
    public void addAnimalFromAreaTile(Deer deer){
        this.preyList.add(deer);
    }

    /**
     * Returns the number of prey after taking into account predation
     * from crocodiles
     * NOTE: This method should only be called for scoring purposes
     *       that is either when the trail is completed or at the end of the
     *       game.
     * @return
     */
    public int getNumOfPreyAfterCrocodileEffect(){
        int numOfCrocodiles = getNumOfCrocodiles();
        while(!this.preyList.isEmpty() && numOfCrocodiles > 0){
            this.preyList.remove(0);
            numOfCrocodiles--;
        }

        return this.preyList.size();
    }

    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInTrail();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

}