package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

/**
 * Created by Aidan on 11/7/2016.
 */
public class AreaTile {


    //Point2D position;
    Edge northEdge;
    Edge eastEdge;
    Edge southEdge;
    Edge westEdge;
    Terrain center;

    public AreaTile(Edge northEdge, Edge eastEdge, Edge southEdge, Edge westEdge, Terrain center) {
        this.northEdge = northEdge;
        this.eastEdge = eastEdge;
        this.southEdge = southEdge;
        this.westEdge = westEdge;
        this.center = center;
    }

    public void rotateCW(){
        Edge tempEdge = northEdge;
        northEdge = westEdge;
        westEdge = southEdge;
        southEdge = eastEdge;
        eastEdge = tempEdge;
    }

    public void rotateCCW(){
        Edge tempEdge = northEdge;
        northEdge = eastEdge;
        eastEdge = southEdge;
        southEdge = westEdge;
        westEdge = tempEdge;
    }

    public Edge getNorthEdge() {
        return northEdge;
    }

    public void setNorthEdge(Edge northEdge) {
        this.northEdge = northEdge;
    }

    public Edge getEastEdge() {
        return eastEdge;
    }

    public void setEastEdge(Edge eastEdge) {
        this.eastEdge = eastEdge;
    }

    public Edge getSouthEdge() {
        return southEdge;
    }

    public void setSouthEdge(Edge southEdge) {
        this.southEdge = southEdge;
    }

    public Edge getWestEdge() {
        return westEdge;
    }

    public void setWestEdge(Edge westEdge) {
        this.westEdge = westEdge;
    }

    public Terrain getCenter() {
        return center;
    }

    public void setCenter(Terrain center) {
        this.center = center;
    }

    public Terrain getNorthEdgeCenter(){
        return northEdge.getMiddleTerrain();
    }

    public Terrain getEastEdgeCenter(){
        return eastEdge.getMiddleTerrain();
    }

    public Terrain getSouthEdgeCenter(){
        return southEdge.getMiddleTerrain();
    }

    public Terrain getWestEdgeCenter(){
        return westEdge.getMiddleTerrain();
    }

}
