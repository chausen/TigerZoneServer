package com.tigerzone.fall2016.tileplacement.tile;


import com.tigerzone.fall2016.area.terrainnode.JungleTerrainNode;
import com.tigerzone.fall2016.area.terrainnode.TerrainNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/13/2016.
 */
public class BoardTile {

    List<TerrainNode> terrainNodes;

    List<Integer> northConnections;
    List<Integer> eastConnections;
    List<Integer> southConnections;
    List<Integer> westConnections;

    List<TerrainNode> southZones;

    HashMap<Integer, TerrainNode> zoneTerrainNodeMap;

    TerrainNode[] zones = new TerrainNode[9];

    public TerrainNode[] getZones() {
        return zones;
    }

    public void rotateCCW(int rotationDegrees) {
        int rotationCount = rotationDegrees/90;
        for (int i=0; i<rotationCount; i++) {
            TerrainNode temp = getZone(1);
            TerrainNode temp2 = getZone(2);
            setZone(1, getZone(3));
            setZone(2, getZone(6));
            setZone(3, getZone(9));
            setZone(6, getZone(8));
            setZone(9, getZone(7));
            setZone(8, getZone(4));
            setZone(7, temp);
            setZone(4, temp2);
        }

    }

    public TerrainNode getZone(int index) {
        index--;
        return zones[index];
    }

    public void setZone(int index, TerrainNode terrainNode) {
        index--;
        zones[index] = terrainNode;
    }

//
//    public void checkNorthCompatible(BoardTile toCompare) {
//        for (TerrainNode terrainNode: this.terrainNodes) {
//            for (TerrainNode compareNodes: toCompare.getTerrainNodeList()) {
//                if (terrainNode.northCompatible.contains(compareNodes.getZones())) {
//
//                }
//            }
//        }
//    }

    public BoardTile(PlayableTile playableTile){
        createZones(playableTile.getTileString());
    }

    public BoardTile(PlayableTile playableTile, int rotationDegrees) {

    }

    private void createZones(String tileString) {
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
