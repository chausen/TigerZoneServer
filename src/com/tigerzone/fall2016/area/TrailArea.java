package com.tigerzone.fall2016.area;
import com.tigerzone.fall2016.animals.Predator;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.TrailTerrainNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by lenovo on 11/7/2016.
 */
public class TrailArea extends CrocodileFriendlyArea {

    Set<TrailTerrainNode> trailTerrainNodes;

    private List<Prey> preyList;
    public TrailArea(){
        this.preyList = new ArrayList<>();
    }

    public List<Prey> getPreyList() {
        return preyList;
    }

    @Override
    public void mergeAnimals(Area area) { //area is what I am merging with
        area.acceptAnimals(this);
    }

    /**
     * this method should be called when a Boar is added from an AreaTile
     * @param boar
     */
    @Override
    public void addAnimal(Boar boar){
        this.preyList.add(boar);
    }

    /**
     * this method should be called when a Buffalo is added from an AreaTile
     * @param buffalo
     */
    @Override
    public void addAnimal(Buffalo buffalo){
        this.preyList.add(buffalo);
    }

    /**
     * this method should be called when a Deer is added from an AreaTile
     * @param deer
     */
    @Override
    public void addAnimal(Deer deer){
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
    boolean isPredatorPlaceable(Predator predator) {
        return predator.placeableInTrail();
    }


    @Override
    public void addToAppropriateList(List<TrailArea> trailAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas) {
        trailAreas.add(this);
    }


    @Override
    public void acceptAnimals(LakeArea area) {
        System.out.println("Wwhy is this getting called in TrailArea?");
    }
    @Override
    public void acceptAnimals(TrailArea area) {
        area.getPreyList().addAll(this.getPreyList());
        area.getTigerList().addAll(this.getTigerList());
    }
    @Override
    public void acceptAnimals(DenArea area) {
        System.out.println("Wwhy is this getting called in TrailArea?");
    }
    @Override
    public void acceptAnimals(JungleArea area) {
        System.out.println("Wwhy is this getting called in TrailArea?");
    }


}