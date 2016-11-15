package com.tigerzone.fall2016.tileplacement.tile;


import com.tigerzone.fall2016.area.terrainnode.TerrainNode;

import java.util.List;

/**
 * Created by lenovo on 11/13/2016.
 */
public class BoardTile {

    List<TerrainNode> terrainNodes;

    public BoardTile(PlayableTile playableTile){
        createZones(playableTile.getTileString());
    }

    public BoardTile(PlayableTile playableTile, int rotationDegrees) {

    }

    private void createZones(String tileString) {
        switch (tileString) {
            case "JJJJ-":


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
