package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.animals.Buffalo;
import com.tigerzone.fall2016.animals.Deer;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.LakeTerrainNode;
import com.tigerzone.fall2016.scoring.Scorer;

import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends CrocodileFriendlyArea {
    private boolean containsBoar;
    private boolean containsBuffalo;
    private boolean containsDeer;

    public LakeArea(){
        this.containsBoar = false;
        this.containsBuffalo = false;
        this.containsDeer = false;
    }

    @Override
    public void mergeAnimals(Area area) {
        area.acceptAnimals(this);
    }

    @Override
    public void acceptAnimals(LakeArea area) {
        area.getCrocodileList().addAll(this.getCrocodileList());
        if(area.containsBoar() || this.containsBoar()){
            area.setContainsBoarToTrue();
        }
        if(area.containsBuffalo() || this.containsBuffalo()){
            area.setContainsBuffaloToTrue();
        }
        if(area.containsDeer() || this.containsDeer()){
            area.setContainsDeerToTrue();
        }
    }

    @Override
    public void acceptAnimals(TrailArea area) {

    }

    @Override
    public void acceptAnimals(DenArea area) {

    }

    @Override
    public void acceptAnimals(JungleArea area) {

    }

    @Override
    public void acceptScorer(Scorer scorer) {
        scorer.score(this);
    }

    @Override
    boolean isPredatorPlaceable(Predator predator) {
        return predator.placeableInLake();
    }


    /**
     * this method should be called when a Boar is added from an AreaTile
     * @param boar
     */
    @Override
    public void addAnimal(Boar boar){
        this.containsBoar = true;
    }

    /**
     * this method should be called when a Buffalo is added from an AreaTile
     * @param buffalo
     */
    @Override
    public void addAnimal(Buffalo buffalo){
        this.containsBuffalo = true;
    }

    /**
     * this method should be called when a Deer is added from an AreaTile
     * @param deer
     */
    @Override
    public void addAnimal(Deer deer){
        this.containsDeer = true;
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
        if(this.containsBoar){
            count++;
        }

        if(this.containsBuffalo){
            count++;
        }

        if(this.containsDeer){
            count++;
        }

        int numOfCrocodiles = getNumOfCrocodiles();
        while(count > 0 && numOfCrocodiles > 0){
            count--;
            numOfCrocodiles--;
        }

        return count;
    }

    public boolean containsBoar(){
        return this.containsBoar;
    }

    public boolean containsBuffalo(){
        return this.containsBuffalo;
    }

    public boolean containsDeer(){
        return this.containsDeer;
    }

    void setContainsBoarToTrue(){
        this.containsBoar = true;
    }

    void setContainsBuffaloToTrue(){
        this.containsBuffalo = true;
    }

    void setContainsDeerToTrue(){
        this.containsDeer = true;
    }



    @Override
    public void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas) {
        lakeAreas.add(this);
    }
}