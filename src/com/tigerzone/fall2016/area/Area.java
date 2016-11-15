package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area implements ListAddable{

    private Set<BoardTile> boardTiles;
    private Set<TerrainNode> terrainNodes;
    private List<Tiger> tigerList;

    public Area(){

        tigerList = new ArrayList<>();
    }

    // TODO: 11/14/2016 commented out for push 
//    public void visit(Crocodile crocodile){
//        crocodile.accept(this);
//    }
//    public void visit(Tiger tiger){
//        tiger.accept(this);
//    }

    void addAnimalFromAnimalAreaTile(Prey prey){}

    void addAnimalFromAnimalAreaTile(Predator crocodile){
        //this.crocodileList.add(crocodile);
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

    public int getSize(){
        return boardTiles.size();
    }

    int numOfTigersInArea(){
        return this.tigerList.size();
    }

    public void mergeArea(Area area){
        for(TerrainNode terrainNode : area.getTerrainNodes()){
            terrainNode.setArea(this);
        }
        addBoardTile(area.getBoardTiles());
        addTerrainNode(area.getTerrainNodes());
    }

    public void addBoardTile(Set<BoardTile> boardTiles){
        boardTiles.addAll(boardTiles);
    }

    public void addTerrainNode(Set<TerrainNode> terrainNodes){
        terrainNodes.addAll(terrainNodes);
    }

    public Set<BoardTile> getBoardTiles(){
        return boardTiles;
    }

    public Set<TerrainNode> getTerrainNodes(){
        return this.terrainNodes;
    }

}