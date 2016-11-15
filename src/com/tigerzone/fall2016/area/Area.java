package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.tileplacement.tile.AnimalAreaTile;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;
import com.tigerzone.fall2016.tileplacement.tile.Tile;

import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area {
    private Map<Point2D, FreeSpace> freeSpaceMap;
    private Map<Point2D, Tile> areaTileMap;
    private List<Tiger> tigerList;

    public Area(){
        this.freeSpaceMap = new HashMap<>();
        this.areaTileMap = new HashMap<>();
        this.tigerList = new ArrayList<>();
    }

    public Area(Point2D position, AreaTile areaTile, Map<Point2D, FreeSpace> freeSpaceMap) {
        this.freeSpaceMap = freeSpaceMap;
        areaTileMap = new HashMap<>();
        areaTileMap.put(position, areaTile);
        tigerList = new ArrayList<>();
    }

    /**
     * Adds the parameter tile to areaTile Map
     * @param position
     * @param tile
     */
    private void addTile(Point2D position, Tile tile){
        areaTileMap.put(position, tile);
    }

    /**
     * Update Area given an areaTile
     * @param position
     * @param areaTile
     */
    public void updateArea(Point2D position, AreaTile areaTile){
        if(freeSpaceMap.containsKey(position)){
            addTile(position, areaTile);
        }
    }

    /**
     * Update Area given an AnimalAreaTile
     * @param position
     * @param animalAreaTile
     */
    public void updateArea(Point2D position, AnimalAreaTile animalAreaTile){
        if(freeSpaceMap.containsKey(position)){
            addTile(position, animalAreaTile);
        }
        Animal animalOnTile = animalAreaTile.getAnimal();
        addAnimalFromAreaTile(animalOnTile);
    }

    /**
     * Returns true only if area was updated with input tile
     * @return
     */
    public boolean hasAreaUpdated(){
        return true;
    }

    /**
     * Adds an animal from a Tile to an Area
     * @param animal
     */
    public void addAnimalFromAreaTile(Animal animal){
        animal.addToArea(this);
    }

    public void addAnimalFromAreaTile(Crocodile crocodile){}

    public void addAnimalFromAreaTile(Buffalo buffalo){}

    public void addAnimalFromAreaTile(Deer deer){}

    public void addAnimalFromAreaTile(Boar boar){}

    /**
     * This method is used when a player tries to place a predator to this Area
     * @param predator
     */
    public void placePredator(Predator predator){
        if (isPredatorPlacable(predator)) {
            predator.placeInArea(this);
        }
    }

    /**
     * This method is used when a player tries to place a tiger to this Area
     * @param tiger
     */
    public void placePredator(Tiger tiger){
        if(this.tigerList.isEmpty()){
            this.tigerList.add(tiger);
        }else{
            int playerID = tiger.getPlayerId();
            //player should forfeit
        }
    }

    /**
     * This method is used when a player tries to place a crocodile to this Area
     * @param crocodile
     */
    public void placePredator(Crocodile crocodile){}

    /**
     * Returns true if the predator is placable in the specific Area Type
     * @param predator
     * @return
     */
    abstract boolean isPredatorPlacable(Predator predator);

    /**
     * Returns true if the Area is complete
     * @return
     */
    public abstract boolean isComplete();

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

    /**
     * Returns the size of the area
     * @return
     */
    public int getSize(){
        return this.areaTileMap.size();
    }

    /**
     * Returns the freeSpaceMap of this Area
     * @return
     */
    public Map<Point2D, FreeSpace> getFreeSpaceMap(){
        return this.freeSpaceMap;
    }

    int numOfTigersInArea(){
        return this.tigerList.size();
    }
}