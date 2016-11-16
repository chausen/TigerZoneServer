package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.*;
import com.tigerzone.fall2016.gamesystem.Turn;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by lenovo on 11/13/2016.
 */

public class BoardTile {

    private List<TerrainNode> terrainNodes;

    public BoardTile(List<TerrainNode> terrainNodes) {
        setTerrainNodes(terrainNodes);
    }

    public BoardTile(PlayableTile playableTile){
        createTerrainNodes(playableTile.getTileString());
    }

    public BoardTile(PlayableTile playableTile, int rotationDegrees) {
        createTerrainNodes(playableTile.getTileString());
        rotateCCW(rotationDegrees);
    }

    public void setTerrainNodes(List<TerrainNode> terrainNodes) {
        this.terrainNodes = terrainNodes;
        for (TerrainNode terrainNode: this.terrainNodes) {
            terrainNode.setBoardTile(this);
        }
    }

    public List<TerrainNode> getTerrainNodeList(){
        return this.terrainNodes;
    }

    public void rotateCCW(int rotationDegrees) {
        for (TerrainNode terrainNode: this.terrainNodes) {
            terrainNode.rotateTerrainNode(rotationDegrees);
        }
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


    private void createTerrainNodes(String tileString) {
        switch (tileString) {
            case "JJJJ-":
                List<Integer> zoneList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                List<Integer> cc2List = new ArrayList<>(Arrays.asList(7,8,9,1,4,7,1,2,3,3,6,9));
                TerrainNode terrainNode = new JungleTerrainNode(cc2List, zoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                List<TerrainNode> terrainNodes1 = new ArrayList<>(Arrays.asList(terrainNode));
                this.terrainNodes = terrainNodes1;
                break;
            case "JJJJX":
                List<Integer> zoneList1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                List<Integer> cc2List1 = new ArrayList<>(Arrays.asList(7,8,9,1,4,7,1,2,3,3,6,9));
                DenTerrainNode terrainNode1 = new DenTerrainNode();
                TerrainNode terrainNode2 = new JungleTerrainNode(cc2List1, zoneList1, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>(Arrays.asList(terrainNode1)));
                this.terrainNodes = new ArrayList<>(Arrays.asList(terrainNode1, terrainNode2));
                break;
            case "JJTJX":
                List<Integer> zoneList2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                List<Integer> cc2List2 = new ArrayList<>(Arrays.asList(7,9,1,4,7,1,2,3,3,6,9));
                DenTerrainNode terrainNode3 = new DenTerrainNode();
                TerrainNode terrainNode4 = new JungleTerrainNode(cc2List2, zoneList2, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>(Arrays.asList(terrainNode3)));
                TerrainNode terrainNode5 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(2)), new ArrayList<Integer>(Arrays.asList(8)));
                this.terrainNodes = new ArrayList<>(Arrays.asList(terrainNode3, terrainNode4));
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


}
