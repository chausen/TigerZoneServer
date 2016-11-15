package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.animals.Buffalo;
import com.tigerzone.fall2016.animals.Deer;
import com.tigerzone.fall2016.animals.Predator;

import java.util.List;


/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends CrocodileFriendlyArea {
    private boolean hasBoar;
    private boolean hasBuffalo;
    private boolean hasDeer;

    public LakeArea(){
        this.hasBoar = false;
        this.hasBuffalo = false;
        this.hasDeer = false;
    }


    @Override
    boolean isPredatorPlacable(Predator predator) {
        return predator.placeableInLake();
    }

    @Override
    public boolean isComplete() {
        return false;
    }


    public boolean containsBoar() {
        return false;


        /**
         * this method should be called when a Boar is added from an AreaTile
         * @param boar
         */
    }
    @Override
    public void addAnimalFromAreaTile(Boar boar){
        this.hasBoar = true;
    }

    /**
     * this method should be called when a Buffalo is added from an AreaTile
     * @param buffalo
     */
    @Override
    public void addAnimalFromAreaTile(Buffalo buffalo){
        this.hasBuffalo = true;
    }

    /**
     * this method should be called when a Deer is added from an AreaTile
     * @param deer
     */
    @Override
    public void addAnimalFromAreaTile(Deer deer){
        this.hasDeer = true;
    }


    /**
     * Returns the number of unique prey after predation
     * NOTE: This method should only be called for scoring purposes
     *       that is either when the trail is completed or at the end of the
     *       game.
     * @return
     */
    public int getNumOfUniquePreyAnimalsAfterCrocodileEffect(){
        int count = 0;
        if(this.hasBoar){
            count++;
        }

        if(this.hasBuffalo){
            count++;
        }

        if(this.hasDeer){
            count++;
        }

        int numOfCrocodiles = getNumOfCrocodiles();
        while(count > 0 && numOfCrocodiles > 0){
            count--;
            numOfCrocodiles--;
        }

        return count;
    }

    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {
        lakeAreas.add(this);
    }
}