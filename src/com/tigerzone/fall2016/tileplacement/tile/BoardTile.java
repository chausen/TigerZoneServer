package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.animals.Buffalo;
import com.tigerzone.fall2016.animals.Deer;
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

                List<Integer> jungleAZoneList = new ArrayList<>(Arrays.asList(1));
                List<Integer> jungleBZoneList = new ArrayList<>(Arrays.asList(3,7,8,9));
                List<Integer> trailAZoneList = new ArrayList<>(Arrays.asList(2,4,5));
                List<Integer> lakeAZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleACanConnect = new ArrayList<>(Arrays.asList(7,3));
                List<Integer> jungleBCanConnect = new ArrayList<>(Arrays.asList(9,9,1,2,3));
                List<Integer> trailACanConnect = new ArrayList<>(Arrays.asList(6,8));
                List<Integer> lakeACanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeA = new LakeTerrainNode(lakeACanConnect, lakeAZoneList);
                TrailTerrainNode trailA = new TrailTerrainNode(trailACanConnect, trailAZoneList);
                JungleTerrainNode jungleA = new JungleTerrainNode(jungleACanConnect, jungleAZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleB = new JungleTerrainNode(jungleBCanConnect, jungleBZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeA)), new HashSet<DenTerrainNode>());
                List<TerrainNode> terrainNodesA = new ArrayList<>(Arrays.asList(jungleA, jungleB, trailA, lakeA));
                this.terrainNodes = terrainNodesA;

                break;
            case "TLJTP": //checked
                List<Integer> jungleCZoneList = new ArrayList<>(Arrays.asList(1));
                List<Integer> jungleDZoneList = new ArrayList<>(Arrays.asList(3,7,8,9));
                List<Integer> trailCZoneList = new ArrayList<>(Arrays.asList(2,4,5));
                List<Integer> lakeCZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleCCanConnect = new ArrayList<>(Arrays.asList(7,3));
                List<Integer> jungleDCanConnect = new ArrayList<>(Arrays.asList(9,9,1,2,3));
                List<Integer> trailCCanConnect = new ArrayList<>(Arrays.asList(6,8));
                List<Integer> lakeCCanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeC = new LakeTerrainNode(lakeCCanConnect, lakeCZoneList);
                TrailTerrainNode trailC = new TrailTerrainNode(trailCCanConnect, trailCZoneList);
                JungleTerrainNode jungleC = new JungleTerrainNode(jungleCCanConnect, jungleCZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleD = new JungleTerrainNode(jungleDCanConnect, jungleDZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeC)), new HashSet<DenTerrainNode>());

                lakeC.getArea().addAnimal(new Boar());
                trailC.getArea().addAnimal(new Boar());

                this.terrainNodes = Arrays.asList(jungleC, jungleD, trailC, lakeC);

                break;
            case "JLTT-": //checked
                List<Integer> jungleEZoneList = new ArrayList<>(Arrays.asList(7));
                List<Integer> jungleFZoneList = new ArrayList<>(Arrays.asList(1,2,3,9));
                List<Integer> trailDZoneList = new ArrayList<>(Arrays.asList(4,5,8));
                List<Integer> lakeDZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleECanConnect = new ArrayList<>(Arrays.asList(9,1));
                List<Integer> jungleFCanConnect = new ArrayList<>(Arrays.asList(3,7,8,9,3)); //checked
                List<Integer> trailDCanConnect = new ArrayList<>(Arrays.asList(6,2));
                List<Integer> lakeDCanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeD = new LakeTerrainNode(lakeDCanConnect, lakeDZoneList);
                TrailTerrainNode trailD = new TrailTerrainNode(trailDCanConnect, trailDZoneList);
                JungleTerrainNode jungleE = new JungleTerrainNode(jungleECanConnect, jungleEZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleF = new JungleTerrainNode(jungleFCanConnect, jungleFZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeD)), new HashSet<DenTerrainNode>());

                this.terrainNodes = Arrays.asList(jungleE, jungleF, trailD, lakeD);

                break;
            case "JLTTB": //checked

                List<Integer> jungleGZoneList = new ArrayList<>(Arrays.asList(7));
                List<Integer> jungleHZoneList = new ArrayList<>(Arrays.asList(1,2,3,9));
                List<Integer> trailEZoneList = new ArrayList<>(Arrays.asList(4,5,8));
                List<Integer> lakeEZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleGCanConnect = new ArrayList<>(Arrays.asList(9,1));
                List<Integer> jungleHCanConnect = new ArrayList<>(Arrays.asList(3,7,8,9,3)); //checked
                List<Integer> trailECanConnect = new ArrayList<>(Arrays.asList(6,2));
                List<Integer> lakeECanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeE = new LakeTerrainNode(lakeECanConnect, lakeEZoneList);
                TrailTerrainNode trailE = new TrailTerrainNode(trailECanConnect, trailEZoneList);
                JungleTerrainNode jungleG = new JungleTerrainNode(jungleGCanConnect, jungleGZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleH = new JungleTerrainNode(jungleHCanConnect, jungleHZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeE)), new HashSet<DenTerrainNode>());

                lakeE.getArea().addAnimal(new Buffalo());
                trailE.getArea().addAnimal(new Buffalo());

                this.terrainNodes = Arrays.asList(jungleG, jungleH, trailE, lakeE);

                break;
            case "TLTJ-": //checked

                List<Integer> jungleIZoneList = new ArrayList<>(Arrays.asList(1,4,7));
                List<Integer> jungleJZoneList = new ArrayList<>(Arrays.asList(3,9));
                List<Integer> trailFZoneList = new ArrayList<>(Arrays.asList(2,5,8));
                List<Integer> lakeFZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleICanConnect = new ArrayList<>(Arrays.asList(7,3,6,9,1));
                List<Integer> jungleJCanConnect = new ArrayList<>(Arrays.asList(9,3)); //checked
                List<Integer> trailFCanConnect = new ArrayList<>(Arrays.asList(8,2));
                List<Integer> lakeFCanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeF = new LakeTerrainNode(lakeFCanConnect, lakeFZoneList);
                TrailTerrainNode trailF = new TrailTerrainNode(trailFCanConnect, trailFZoneList);
                JungleTerrainNode jungleI = new JungleTerrainNode(jungleICanConnect, jungleIZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleJ = new JungleTerrainNode(jungleJCanConnect, jungleJZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeF)), new HashSet<DenTerrainNode>());

                this.terrainNodes = Arrays.asList(jungleI, jungleJ, trailF, lakeF);

                break;
            case "TLTJD": //checked

                List<Integer> jungleKZoneList = new ArrayList<>(Arrays.asList(1,4,7));
                List<Integer> jungleLZoneList = new ArrayList<>(Arrays.asList(3,9));
                List<Integer> trailGZoneList = new ArrayList<>(Arrays.asList(2,5,8));
                List<Integer> lakeGZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleKCanConnect = new ArrayList<>(Arrays.asList(7,3,6,9,1));
                List<Integer> jungleLCanConnect = new ArrayList<>(Arrays.asList(9,3)); //checked
                List<Integer> trailGCanConnect = new ArrayList<>(Arrays.asList(8,2));
                List<Integer> lakeGCanConnect = new ArrayList<>(Arrays.asList(4));

                LakeTerrainNode lakeG = new LakeTerrainNode(lakeGCanConnect, lakeGZoneList);
                TrailTerrainNode trailG = new TrailTerrainNode(trailGCanConnect, trailGZoneList);
                JungleTerrainNode jungleK = new JungleTerrainNode(jungleKCanConnect, jungleKZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleL = new JungleTerrainNode(jungleLCanConnect, jungleLZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeG)), new HashSet<DenTerrainNode>());

                lakeG.getArea().addAnimal(new Deer());
                trailG.getArea().addAnimal(new Deer());

                this.terrainNodes = Arrays.asList(jungleK, jungleL, trailG, lakeG);

                break;
            case "TLLL-": //checked

                List<Integer> jungleMZoneList = new ArrayList<>(Arrays.asList(1));
                List<Integer> jungleNZoneList = new ArrayList<>(Arrays.asList(3));
                List<Integer> trailHZoneList = new ArrayList<>(Arrays.asList(2));
                List<Integer> lakeHZoneList = new ArrayList<>(Arrays.asList(4,5,6,7,8,9));

                List<Integer> jungleMCanConnect = new ArrayList<>(Arrays.asList(7));
                List<Integer> jungleNCanConnect = new ArrayList<>(Arrays.asList(9)); //checked
                List<Integer> trailHCanConnect = new ArrayList<>(Arrays.asList(8));
                List<Integer> lakeHCanConnect = new ArrayList<>(Arrays.asList(4,7,3,2,1,9,6));

                LakeTerrainNode lakeH = new LakeTerrainNode(lakeHCanConnect, lakeHZoneList);
                TrailTerrainNode trailH = new TrailTerrainNode(trailHCanConnect, trailHZoneList);
                JungleTerrainNode jungleM = new JungleTerrainNode(jungleMCanConnect, jungleMZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeH)), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleN = new JungleTerrainNode(jungleNCanConnect, jungleNZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeH)), new HashSet<DenTerrainNode>());

                this.terrainNodes = Arrays.asList(jungleM, jungleN, trailH, lakeH);

                break;
            case "TLTT-": //checked

                List<Integer> jungleOZoneList = new ArrayList<>(Arrays.asList(1));
                List<Integer> junglePZoneList = new ArrayList<>(Arrays.asList(7));
                List<Integer> jungleQZoneList = new ArrayList<>(Arrays.asList(3,9));
                List<Integer> trailIZoneList = new ArrayList<>(Arrays.asList(2));
                List<Integer> trailJZoneList = new ArrayList<>(Arrays.asList(4));
                List<Integer> trailKZoneList = new ArrayList<>(Arrays.asList(8));

                List<Integer> lakeIZoneList = new ArrayList<>(Arrays.asList(6));

                List<Integer> jungleOCanConnect = new ArrayList<>(Arrays.asList(3,7));
                List<Integer> junglePCanConnect = new ArrayList<>(Arrays.asList(9,1)); //checked
                List<Integer> jungleQCanConnect = new ArrayList<>(Arrays.asList(9,3)); //checked
                List<Integer> trailICanConnect = new ArrayList<>(Arrays.asList(8));
                List<Integer> trailJCanConnect = new ArrayList<>(Arrays.asList(6));
                List<Integer> trailKCanConnect = new ArrayList<>(Arrays.asList(2));

                List<Integer> lakeICanConnect = new ArrayList<>(Arrays.asList(4));


                TrailTerrainNode trailI = new TrailTerrainNode(trailICanConnect, trailIZoneList);
                TrailTerrainNode trailJ = new TrailTerrainNode(trailJCanConnect, trailJZoneList);
                TrailTerrainNode trailK = new TrailTerrainNode(trailKCanConnect, trailKZoneList);

                LakeTerrainNode lakeI = new LakeTerrainNode(lakeICanConnect, lakeIZoneList);
                JungleTerrainNode jungleO = new JungleTerrainNode(jungleOCanConnect, jungleOZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleP = new JungleTerrainNode(junglePCanConnect, junglePZoneList, new HashSet<LakeTerrainNode>(), new HashSet<DenTerrainNode>());
                JungleTerrainNode jungleQ = new JungleTerrainNode(jungleQCanConnect, jungleQZoneList, new HashSet<LakeTerrainNode>(Arrays.asList(lakeI)), new HashSet<DenTerrainNode>());
                

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
