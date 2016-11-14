package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentAdder;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentVisitor;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private List<DenArea> denAreas;
    private List<JungleArea> jungleAreas;
    private List<LakeArea> lakeAreas;
    private List<TrailArea> trailAreas;
    private HashMap<Point2D, AreaTile> areaTileHashMap;
    private FreeSpaceBoard freeSpaceBoard;

    public AreaManager(List<DenArea> denAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas, List<TrailArea> trailAreas,
    FreeSpaceBoard freeSpaceBoard) {
        this.denAreas = denAreas;
        this.jungleAreas = jungleAreas;
        this.lakeAreas = lakeAreas;
        this.trailAreas = trailAreas;
        this.freeSpaceBoard = freeSpaceBoard;
        //You know what these need to be built at the beginning of the game
        this.areaTileHashMap = areaTileHashMap;
    }

    public void updateAreas(Point2D position, AreaTile areaTile){

        if(freeSpaceBoard.isPlaceable(position, areaTile)){
            SegmentVisitor segmentVisitor = new SegmentAdder();
            FreeSpace freeSpace = freeSpaceBoard.getFreeSpace(position);
            if(freeSpace.getNorthTerrain().isFree()){
                Edge edge = areaTile.getNorthEdge();
                edge.getLeftTerrain().accept(segmentVisitor);
                edge.getMiddleTerrain().accept(segmentVisitor);
                edge.getRightTerrain().accept(segmentVisitor);
            }
            if(freeSpace.getEastTerrain().isFree()){
                Edge edge = areaTile.getEastEdge();
                edge.getLeftTerrain().accept(segmentVisitor);
                edge.getMiddleTerrain().accept(segmentVisitor);
                edge.getRightTerrain().accept(segmentVisitor);
            }
            if(freeSpace.getSouthTerrain().isFree()){
                Edge edge = areaTile.getSouthEdge();
                edge.getLeftTerrain().accept(segmentVisitor);
                edge.getMiddleTerrain().accept(segmentVisitor);
                edge.getRightTerrain().accept(segmentVisitor);
            }
            if(freeSpace.getWestTerrain().isFree()){
                Edge edge = areaTile.getWestEdge();
                edge.getLeftTerrain().accept(segmentVisitor);
                edge.getMiddleTerrain().accept(segmentVisitor);
                edge.getRightTerrain().accept(segmentVisitor);
            }
        }
    }

    private void merge(Mergeable m1, Mergeable m2) {

    }


}
