package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.*;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area {
    private HashMap<Point2D, FreeSpace> freeSpaceMap;
    private HashMap<Point2D, AreaTile> areaTileMap;
    private List<Tiger> tigerList;
    private List<FreeSpace> freeSpaces;

    public Area() {
        freeSpaceMap = new HashMap<>();
        areaTileMap = new HashMap<>();
        tigerList = new ArrayList<>();
    }

    /**
     * Returns a list of playerID's that have equal max tiger counts for an area.
     * @return
     */
    public List<Integer> getOwnerID() {
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
        int maxCount;
        List<Integer> areaOwners = new ArrayList<>();
        if(iterator.hasNext()){
            maxCount = iterator.next().getValue();
        }else{
            return areaOwners;
        }

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

    public void updateArea(AreaTile areaTile) {

    }

    private void addTile(AreaTile tile){

    }

    private void addAnimal(Animal animal){

    }

//    -
//    +updateArea(t:AreaTile):void
//    +updateArea(t:AnimalAreaTile):void
//    +hasAreaUpdated():bool
//    +isAnimalPlaceable(): bool

    public void placeTiger(Tiger tiger){
        this.tigerList.add(tiger);
    }

    abstract boolean isComplete();

    public HashMap<Point2D, FreeSpace> getFreeSpaceMap(){
        return freeSpaceMap;
    }

}
