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

    /**
     * This constructor is for testing purposes for now
     */
    public Area(){
        tigerList = new ArrayList<>();
        boardTiles = new HashSet<>();
        terrainNodes = new HashSet<>();
    }

    public void mergeArea(Area area){
        for(TerrainNode terrainNode : area.getTerrainNodes()){
            terrainNode.setArea(this);
        }
        addBoardTile(area.getBoardTiles());
        addTerrainNode(area.getTerrainNodes());
    }

    public void addBoardTile(BoardTile boardTile){
        boardTiles.add(boardTile);
    }

    public void addBoardTile(Set<BoardTile> boardTiles){
        this.boardTiles.addAll(boardTiles);
    }

    public void addTerrainNode(Set<TerrainNode> terrainNodes){
        this.terrainNodes.addAll(terrainNodes);
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
    public void addAnimal(Animal animal){
        animal.addToArea(this);
    }

    public void addAnimal(Crocodile crocodile){}

    public void addAnimal(Buffalo buffalo){}

    public void addAnimal(Deer deer){}

    public void addAnimal(Boar boar){}

    /**
     * This method is used when a player tries to place a predator to this Area
     * @param predator
     */
    public void placePredator(Predator predator){
        if (isPredatorPlaceable(predator)) {
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
            String playerID = tiger.getPlayerId();
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
    abstract boolean isPredatorPlaceable(Predator predator);

    /**
     * Returns true if the Area is complete
     * @return
     */
    public abstract boolean isComplete();

    /**
     * Returns a list of playerID's that have equal max tiger counts for an area.
     * @return
     */
    public List<String> getOwnerID() {
        List<String> areaOwners = new ArrayList<>();
        if(tigerList.size() == 0){
            return areaOwners;
        }

        //key: player | value: count
        Map<String, Integer> playerTigerCountMap = new HashMap<>();
        for(Tiger tiger : tigerList){
            String tigerOwner = tiger.getPlayerId();
            if(playerTigerCountMap.containsKey(tigerOwner)){
                int newCount = playerTigerCountMap.get(tigerOwner) + 1;
                playerTigerCountMap.replace(tigerOwner, newCount);
            }else{
                playerTigerCountMap.put(tigerOwner, 1);
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = playerTigerCountMap.entrySet().iterator();
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
            Map.Entry<String, Integer> player = iterator.next();
            int playerCount = player.getValue();
            String playerID = player.getKey();
            if(playerCount == maxCount){
                areaOwners.add(playerID);
            }
        }
        return areaOwners;
    }

    public int getSize(){
        return this.boardTiles.size();
    }

    int numOfTigersInArea(){
        return this.tigerList.size();
    }


    public Set<BoardTile> getBoardTiles(){
        return this.boardTiles;
    }

    public Set<TerrainNode> getTerrainNodes(){
        return this.terrainNodes;
    }

}