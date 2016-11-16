package com.tigerzone.fall2016.tileplacement.tile;


import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;
import com.tigerzone.fall2016.gamesystem.Turn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/13/2016.
 */
public class BoardTile {

    private List<TerrainNode> terrainNodes;


    HashMap<Integer, TerrainNode> zoneTerrainNodeMap;

    TerrainNode[] zones = new TerrainNode[9];

    public TerrainNode[] getZones() {
        return zones;
    }

    public BoardTile(List<TerrainNode> terrainNodes) {
        setTerrainNodes(terrainNodes);
        }


    public void setTerrainNodes(List<TerrainNode> terrainNodes) {
        this.terrainNodes = terrainNodes;
        for (TerrainNode terrainNode: this.terrainNodes) {
            terrainNode.setBoardTile(this);
        }
    }

    public void rotateCCW(int rotationDegrees) {
        int rotationCount = rotationDegrees/90;
        for (int i=0; i<rotationCount; i++) {
            TerrainNode temp = getTerrainNodeFromZoneIndex(1);
            TerrainNode temp2 = getTerrainNodeFromZoneIndex(2);
            setZone(1, getTerrainNodeFromZoneIndex(3));
            setZone(2, getTerrainNodeFromZoneIndex(6));
            setZone(3, getTerrainNodeFromZoneIndex(9));
            setZone(6, getTerrainNodeFromZoneIndex(8));
            setZone(9, getTerrainNodeFromZoneIndex(7));
            setZone(8, getTerrainNodeFromZoneIndex(4));
            setZone(7, temp);
            setZone(4, temp2);
        }
    }

    public void rotateTerrainNodes(int rotationDegrees) {
        for (TerrainNode terrainNode: this.terrainNodes) {
            terrainNode.rotateTerrainNode(rotationDegrees);
        }
    }

    public TerrainNode getTerrainNodeFromZoneIndex(int index) {
        index--;
        return zones[index];
    }

    public void setZone(int index, TerrainNode terrainNode) {
        index--;
        zones[index] = terrainNode;
    }

    public BoardTile(PlayableTile playableTile){
        createTerrainNodes(playableTile.getTileString());
    }

    public BoardTile(PlayableTile playableTile, int rotationDegrees) {
        createTerrainNodes(playableTile.getTileString());
        rotateTerrainNodes(rotationDegrees);
    }


    public void generateZones(TerrainNode terrainNode) {
        for (Integer integer: terrainNode.getZones()) {
            setZone(integer,terrainNode);
        }
    }

    private void createTerrainNodes(String tileString) {
        switch (tileString) {
            case "JJJJ-":
                List<Integer> zoneList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                List<Integer> cc2List = new ArrayList<>(Arrays.asList(1,2,3,4,6,7,8,9));
                TerrainNode terrainNode = new JungleTerrainNode();
                break;
            case "JJJJX":

                break;
            case "JJTJX":

                break;
            case "TTTT-":

                break;
            case "TJTJ-":

                break;
            case "TJJT-":

                break;
            case "TJTT-":

                break;
            case "LLLL-":

                break;
            case "JLLL-":

                break;
            case "LLJJ-":

                break;
            case "JLJL-":

                break;
            case "LJLJ-":

                break;
            case "LJJJ-":

                break;
            case "JLLJ-":

                break;
            case "TLJT-": //checked

                break;
            case "TLJTP": //checked
                List<Integer> jungle1ZoneList = new ArrayList<>(Arrays.asList(1));
                List<Integer> jungle2ZoneList = new ArrayList<>(Arrays.asList(3,7,8,9));
                List<Integer> trailZoneList = new ArrayList<>(Arrays.asList(2,4,5));
                List<Integer> lakeZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungle1CanConnect = new ArrayList<>(Arrays.asList(7,3));
                List<Integer> jungle2CanConnect = new ArrayList<>(Arrays.asList(9,9,1,2,3));
                List<Integer> trailCanConnect = new ArrayList<>(Arrays.asList(6,8));
                List<Integer> lakeCanConnect = new ArrayList<>(Arrays.asList(4));


                break;
            case "JLTT-": //checked


                break;
            case "JLTTB": //checked

                break;
            case "TLTJ-": //checked

                break;
            case "TLTJD": //checked

                break;
            case "TLLL-": //checked

                break;
            case "TLTT-": //checked

                break;
            case "TLTTP": //checked

                break;
            case "TLLT-": //checked

                break;
            case "TLLTB": //checked

                break;
            case "LJTJ-": //checked

                break;
            case "LJTJD": //checked

                break;
            case "TLLLC": //checked

                break;
        }
    }

    public List<TerrainNode> getTerrainNodeList(){
        return this.terrainNodes;
    }

    public TerrainNode getTerrainNode(int n){
        for(TerrainNode terrainNode: terrainNodes){
            if(terrainNode.getZones().contains(n)){
                return terrainNode;
            }
        }
        return null;
    }

    public void setTerrainNode(int n, TerrainNode terrainNode){
        TerrainNode removeTerrainNode = null;
        for(TerrainNode tn: terrainNodes){
            if(tn.getZones().contains(n)){
                removeTerrainNode = tn;
                break;
            }
        }
        terrainNodes.remove(removeTerrainNode);
        terrainNodes.add(terrainNode);
    }

}
