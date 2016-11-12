package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.tileplacement.tile.AnimalAreaTile;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import com.tigerzone.fall2016.tileplacement.tile.Tile;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area {
    private Map<Point2D, FreeSpace> freeSpaceMap;
    private Map<Point2D, Tile> areaTileMap;
    private List<Tiger> tigerList;

    public Area() {
        freeSpaceMap = new HashMap<>();
        areaTileMap = new HashMap<>();
        tigerList = new ArrayList<>();
    }

    private void addTile(Point2D position, Tile tile){
        freeSpaceMap.remove(position);
        areaTileMap.put(position, tile);
    }

    private void addAnimal(Animal animal){

    }

    public void updateArea(Point2D position, AreaTile areaTile){
        if(freeSpaceMap.containsKey(position)){
            addTile(position, areaTile);
        }
    }

    public void updateArea(Point2D position, AnimalAreaTile animalAreaTile){
        if(freeSpaceMap.containsKey(position)){
            addTile(position, animalAreaTile);
        }
        Animal animalOnTile = animalAreaTile.getAnimal();
        if(isAnimalPlacable(animalOnTile)){
            addAnimal(animalOnTile);
        }
    }

    public boolean hasAreaUpdated(){
        return true;
    }

    public void placeTiger(Tiger tiger){
        this.tigerList.add(tiger);
    }

    abstract boolean isAnimalPlacable(Animal animal);

    abstract boolean isComplete();

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

}
