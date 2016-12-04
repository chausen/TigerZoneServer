package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.scoring.Scorer;

import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class LakeArea extends PlaceableAnimalFriendlyArea {
    private boolean containsBoar;
    private boolean containsBuffalo;
    private boolean containsDeer;
    private boolean containsGoat;

    public LakeArea(){
        this.containsBoar = false;
        this.containsBuffalo = false;
        this.containsDeer = false;
        this.containsGoat = false;
    }

    @Override
    public void mergeAnimals(Area area) {
        area.acceptAnimals(this);
    }

    @Override
    public void acceptAnimals(LakeArea area) {
        area.getTigerList().addAll(this.getTigerList());
        area.getCrocodileList().addAll(this.getCrocodileList());
        area.getGoatList().addAll(this.getGoatList());
        if(area.containsBoar() || this.containsBoar()){
            area.containsBoar = true;
        }
        if(area.containsBuffalo() || this.containsBuffalo()){
            area.containsBuffalo = true;
        }
        if(area.containsDeer() || this.containsDeer()){
            area.containsDeer = true;
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

//    @Override
//    boolean isPredatorPlaceable(Predator predator) {
//        return predator.placeableInLake();
//    }

    @Override
    boolean isAnimalPlaceable(Placeable animal) {
        return animal.placeableInLake();
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
        if (this.containsGoat) {
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
    public boolean containsGoat() { return this.hasGoatInArea(); }

    @Override
    public void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas) {
        lakeAreas.add(this);
    }
}