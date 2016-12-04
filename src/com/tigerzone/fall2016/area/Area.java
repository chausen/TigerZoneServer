package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.animals.*;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;

import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public abstract class Area implements SetAddable{
    private Set<BoardTile> boardTiles;
    private Set<TerrainNode> terrainNodes;
    private Set<Tiger> tigerList;


    /**
     * This constructor is for testing purposes for now
     */
    public Area(){
        this.tigerList = new HashSet<>();
        this.boardTiles = new HashSet<>();
        this.terrainNodes = new HashSet<>();
    }

    public Set<Tiger> getTigerList() {return tigerList;}

    public void removeTiger(Tiger tiger) {
        tigerList.remove(tiger);
    }

    public void mergeArea(Area area){
        for(TerrainNode terrainNode : area.getTerrainNodes()){
            terrainNode.setArea(this);
        }
        addBoardTile(area.getBoardTiles());
        addTerrainNode(area.getTerrainNodes());
        mergeAnimals(area);
    }

    public abstract void mergeAnimals(Area area);

    public abstract void acceptAnimals(LakeArea area);
    public abstract void acceptAnimals(TrailArea area);
    public abstract void acceptAnimals(DenArea area);
    public abstract void acceptAnimals(JungleArea area);
    public abstract void acceptScorer(Scorer scorer);


    public void addBoardTile(BoardTile boardTile){
        boardTiles.add(boardTile);
    }

    public void addBoardTile(Set<BoardTile> boardTiles){
        this.boardTiles.addAll(boardTiles);
    }

    public void addTerrainNode(TerrainNode terrainNode){
        this.terrainNodes.add(terrainNode);
    }

    public void addTerrainNode(Set<TerrainNode> terrainNodes){
        this.terrainNodes.addAll(terrainNodes);
    }

    /**
     * Adds an animal from a Tile to an Area
     * @param animal
     */
    public void addAnimal(Animal animal){
        animal.addToArea(this);
    }

    public void addAnimal(Crocodile crocodile){}

    public void addAnimal(Goat goat){}

    public void addAnimal(Buffalo buffalo){}

    public void addAnimal(Deer deer){}

    public void addAnimal(Boar boar){}

    /**
     * this method should only be called When you are passing in PlaceableAnimal
     * however IOPort usually passes in a more specific type such as Tiger or Crocodile
     * @param placeableAnimal
     */
    public boolean placePlaceableAnimal(PlaceableAnimal placeableAnimal){
        if(isAnimalPlaceable(placeableAnimal)){
            return placeableAnimal.placeInArea(this);
        }
        return false;
    }

    /**
     * This method is used when a player tries to place a tiger to this Area
     * @param tiger
     */
    public boolean placePlaceableAnimal(Tiger tiger){
        if(this.tigerList.isEmpty()){
            this.tigerList.add(tiger);
            return true;
        }
        return false;
    }

    /**
     * This method is used when a player tries to place a crocodile to this Area
     * @param crocodile
     */
    public boolean placePlaceableAnimal(Crocodile crocodile){ return false;}

    /**
     * This method is used when a player tries to place a Goat to this Area
     * @param goat
     * @return
     */
    public boolean placePlaceableAnimal(Goat goat){ return false;}

    public boolean canPlaceTiger() {
        boolean placeable = false;
        if(this.tigerList.isEmpty()) {
            placeable = true;
        }
        return placeable;
    }

    /**
     * Checks to see if PlaceableAnimal is placeable in the area
     * NOTE: This method checks only if the placeableAnimal can be checked
     * based on the terrain type that the player is trying to place the placeableAnimal on.
     * @param placeableAnimal
     * @return
     */
    abstract boolean isAnimalPlaceable(PlaceableAnimal placeableAnimal);

    /**
     * This method adds a tiger to an area without any checking!
     * NOTE: This method should only be called in the special case where a tile is not placable.
     * @param tiger
     */
    public void placeTigerSpecialCase(Tiger tiger) {
        this.tigerList.add(tiger);
    }

    /**
     * Returns true if the Area is complete
     * @return
     */
    public boolean isComplete() { //complete when canConnectTo() list is empty
        boolean isComplete = true;
        for (TerrainNode terrainNode : terrainNodes) {
            if (!terrainNode.getCanConnectTo().isEmpty()) {
                isComplete = false;
            }
        }
        return isComplete;
    }

    /**
     * Returns a list of players that have equal max tiger counts for an area.
     * @return
     */
    public List<Player> getOwner() {
        List<Player> areaOwners = new ArrayList<>();
        if(tigerList.size() == 0){
            return areaOwners;
        }

        //key: player | value: count
        Map<Player, Integer> playerTigerCountMap = new HashMap<>();
        for(Tiger tiger : tigerList){
            Player tigerOwner = tiger.getOwner();
            if(playerTigerCountMap.containsKey(tigerOwner)){
                int newCount = playerTigerCountMap.get(tigerOwner) + 1;
                playerTigerCountMap.replace(tigerOwner, newCount);
            }else{
                playerTigerCountMap.put(tigerOwner, 1);
            }
        }
        Iterator<Map.Entry<Player, Integer>> iterator = playerTigerCountMap.entrySet().iterator();
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
            Map.Entry<Player, Integer> playerInfo = iterator.next();
            int playerCount = playerInfo.getValue();
            Player player = playerInfo.getKey();
            if(playerCount == maxCount){
                areaOwners.add(player);
            }
        }
        return areaOwners;
    }

    /**
     * Returns true if the area has an owner
     * @return
     */
    public boolean hasOwner(){
        return !(this.tigerList.isEmpty());
    }

    public int getSize(){
        return this.boardTiles.size();
    }

    int getTigerCountInArea(){
        return this.tigerList.size();
    }

    public void setBoardTiles(Set<BoardTile> boardTiles) {
        this.boardTiles = boardTiles;
    }

    public Set<BoardTile> getBoardTiles(){
        return this.boardTiles;
    }

    public Set<TerrainNode> getTerrainNodes(){
        return this.terrainNodes;
    }

}