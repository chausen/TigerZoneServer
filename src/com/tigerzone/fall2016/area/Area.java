package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;

import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area {

    private List<Tiger> tigerList;

    public Area(){

        tigerList = new ArrayList<>();
    }



    public void visit(Crocodile crocodile){
        crocodile.accept(this);
    }
    public void visit(Tiger tiger){
        tiger.accept(this);
    }

    void addAnimalFromAnimalAreaTile(Prey prey){}

    void addAnimalFromAnimalAreaTile(Predator crocodile){
        //this.crocodileList.add(crocodile);
    }



//    public void updateArea(Point2D position, AnimalAreaTile animalAreaTile){
//        if(freeSpaceMap.containsKey(position)){
//            addTile(position, animalAreaTile);
//        }
//        Animal animalOnTile = animalAreaTile.getAnimal();
//        //if(isAnimalPlacable(animalOnTile)){
//            addAnimalFromAnimalAreaTile(animalOnTile);
//        //}
//    }

    public boolean hasAreaUpdated(){
        return true;
    }

    public void placePredator(Tiger tiger){
        this.tigerList.add(tiger);
    }

    public void placePredator(Crocodile crocodile){}

    abstract boolean isPredatorPlacable(Predator predator);

    public abstract boolean isComplete();

    public void placePredator(Predator predator){
        if (isPredatorPlacable(predator)) {
            predator.accept(this);
        }
    }


    /**
     * Returns a list of playerID's that have equal max tiger counts for an area.
     * @return
     */
    public List<Integer> getOwnerID() {
        List<Integer> areaOwners = new ArrayList<>();
        if(tigerList.size() == 0){
            return areaOwners;
        }

        //key: player | value: count
        Map<Integer, Integer> playerTigerCountMap = new HashMap<>();
        for(Tiger tiger : tigerList){
            int tigerOwner = tiger.getPlayerId();
            if(playerTigerCountMap.containsKey(tigerOwner)){
                int newCount = playerTigerCountMap.get(tigerOwner) + 1;
                playerTigerCountMap.replace(tigerOwner, newCount);
            }else{
                playerTigerCountMap.put(tigerOwner, 1);
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = playerTigerCountMap.entrySet().iterator();
        int maxCount = iterator.next().getValue();

        //find max number count
        while(iterator.hasNext()){
            int playerCount = iterator.next().getValue();
            if(maxCount < playerCount){
                maxCount = playerCount;
            }
        }

        //add all owners in the area that have equal number of tigers to areaOwners list
        iterator = playerTigerCountMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> player = iterator.next();
            int playerCount = player.getValue();
            int playerID = player.getKey();
            if(playerCount == maxCount){
                areaOwners.add(playerID);
            }
        }
        return areaOwners;
    }




}