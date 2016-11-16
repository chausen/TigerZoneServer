package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.area.terrainnode.*;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.terrain.LakeTerrain;

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
                List<Integer> J1zoneList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                List<Integer> J1cc2List = new ArrayList<>(Arrays.asList(7,8,9,1,4,7,1,2,3,3,6,9));
                TerrainNode J1Node = new JungleTerrainNode(J1cc2List, J1zoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                List<TerrainNode> terrainNodes1 = new ArrayList<>(Arrays.asList(J1Node));
                this.terrainNodes = terrainNodes1;
                break;
            case "JJJJX":
                List<Integer> J1zoneList1 = new ArrayList<>(Arrays.asList(1,2,3,4,6,7,8,9));
                List<Integer> J1cc2List1 = new ArrayList<>(Arrays.asList(7,8,9,1,4,7,1,2,3,3,6,9));
                DenTerrainNode D1terrainNode1 = new DenTerrainNode();
                TerrainNode J1terrainNode1 = new JungleTerrainNode(J1zoneList1, J1cc2List1, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>(Arrays.asList(D1terrainNode1)));
                this.terrainNodes = new ArrayList<>(Arrays.asList(J1terrainNode1, D1terrainNode1));
                break;
            case "JJTJX":
                List<Integer> J1zoneList2 = new ArrayList<>(Arrays.asList(1,2,3,4,6,7,8,9));
                List<Integer> J1cc2List2 = new ArrayList<>(Arrays.asList(7,9,1,4,7,1,2,3,3,6,9));
                DenTerrainNode D1terrainNode2 = new DenTerrainNode();
                TerrainNode J1terrainNode2 = new JungleTerrainNode(J1cc2List2, J1zoneList2, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>(Arrays.asList(D1terrainNode2)));
                TerrainNode T1terrainNode2 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(2)), new ArrayList<Integer>(Arrays.asList(8)));
                this.terrainNodes = new ArrayList<>(Arrays.asList(D1terrainNode2, J1terrainNode2, T1terrainNode2));
                break;
            case "TTTT-":
                List<Integer> J1zoneList3 = new ArrayList<>(Arrays.asList(1));
                List<Integer> J1cc2List3 = new ArrayList<>(Arrays.asList(7, 3));
                TerrainNode J1terrainNode3 = new JungleTerrainNode(J1cc2List3, J1zoneList3, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                List<Integer> J2zoneList3 = new ArrayList<>(Arrays.asList(3));
                List<Integer> J2cc2List3 = new ArrayList<>(Arrays.asList(9, 1));
                TerrainNode J2terrainNode3 = new JungleTerrainNode(J2cc2List3, J2zoneList3, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                List<Integer> J3zoneList3 = new ArrayList<>(Arrays.asList(7));
                List<Integer> J3cc2List3 = new ArrayList<>(Arrays.asList(9, 1));
                TerrainNode J3terrainNode3 = new JungleTerrainNode(J3cc2List3, J3zoneList3, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                List<Integer> J4zoneList3 = new ArrayList<>(Arrays.asList(9));
                List<Integer> J4cc2List3 = new ArrayList<>(Arrays.asList(3, 7));
                TerrainNode J4terrainNode3 = new JungleTerrainNode(J4cc2List3, J4zoneList3, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode T1terrainNode3 = new TrailTerrainNode(Arrays.asList(8), Arrays.asList(2));
                TerrainNode T2terrainNode3 = new TrailTerrainNode(Arrays.asList(4), Arrays.asList(6));
                TerrainNode T3terrainNode3 = new TrailTerrainNode(Arrays.asList(2), Arrays.asList(8));
                TerrainNode T4terrainNode3 = new TrailTerrainNode(Arrays.asList(6), Arrays.asList(4));
                TerrainNode C1terrainNode3 = new CrossRoads();
                this.terrainNodes = Arrays.asList(C1terrainNode3, J1terrainNode3, J2terrainNode3, J3terrainNode3, J4terrainNode3, T1terrainNode3, T2terrainNode3, T3terrainNode3, T4terrainNode3);
                break;
            case "TJTJ-":
                TerrainNode J1terrainNode4 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(7,3,6,9,1)), new ArrayList<Integer>(Arrays.asList(1,4,7)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode J2terrainNode4 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(9,1,4,7,3)), new ArrayList<Integer>(Arrays.asList(3,6,9)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode T1terrainNode4 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(2,8)), new ArrayList<Integer>(Arrays.asList(2,5,8)));
                this.terrainNodes = Arrays.asList(J1terrainNode4, J2terrainNode4, T1terrainNode4);
                break;
            case "TJJT-":
                TerrainNode J1terrainNode5 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(7, 3)), new ArrayList<Integer>(Arrays.asList(1)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode J2terrainNode5 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(9,1,4,7,3,2,1,9)), new ArrayList<Integer>(Arrays.asList(7,8,9,3,6)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode T1terrainNode5 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(8,6)), new ArrayList<Integer>(Arrays.asList(4,5,2)));
                this.terrainNodes = Arrays.asList(J1terrainNode5, J2terrainNode5, T1terrainNode5);
                break;
            case "TJTT-":
                TerrainNode J1terrainNode6 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(7,3)),new ArrayList<Integer>(Arrays.asList(1)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode J2terrainNode6 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(9,1)),new ArrayList<Integer>(Arrays.asList(7)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode J3terrainNode6 = new JungleTerrainNode(new ArrayList<Integer>(Arrays.asList(9,1,4,7,3)),new ArrayList<Integer>(Arrays.asList(3,6,9)), new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                TerrainNode T1terrainNode6 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(8)),new ArrayList<Integer>(Arrays.asList(2)));
                TerrainNode T2terrainNode6 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(6)),new ArrayList<Integer>(Arrays.asList(4)));
                TerrainNode T3terrainNode6 = new TrailTerrainNode(new ArrayList<Integer>(Arrays.asList(2)),new ArrayList<Integer>(Arrays.asList(8)));
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
