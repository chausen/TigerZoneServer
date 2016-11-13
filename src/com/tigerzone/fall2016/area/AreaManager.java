package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentAdder;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentVisitor;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.FreeSpace;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
        updateTrail(position, areaTile);
        SegmentAdder segmentAdder = new SegmentAdder();
        FreeSpace freeSpace = freeSpaceBoard.getFreeSpace(position);
        if(freeSpace.getNorthTerrain().isFree()){
            Edge edge = areaTile.getNorthEdge();
            visitEdge(edge, segmentAdder, Direction.NORTH);
        }
        if(freeSpace.getEastTerrain().isFree()){
            Edge edge = areaTile.getEastEdge();
            visitEdge(edge, segmentAdder, Direction.EAST);
        }
        if(freeSpace.getSouthTerrain().isFree()){
            Edge edge = areaTile.getSouthEdge();
            visitEdge(edge, segmentAdder, Direction.SOUTH);
        }
        if(freeSpace.getWestTerrain().isFree()){
            Edge edge = areaTile.getWestEdge();
            visitEdge(edge, segmentAdder, Direction.WEST);
        }
        //segmentAdder.createAreas();
    }

    private void updateTrail(Point2D position, AreaTile areaTile){
        List<TrailArea> newTrailAreas = new ArrayList<>();
        for(TrailArea ta: trailAreas){
            Set<Point2D> keySet = ta.getFreeSpaceMap().keySet();
            for(Point2D point2D: keySet){
                if(point2D == position){
                    ta.updateArea(areaTile);
                }
            }
        }
    }

    private void merge(Mergeable m1, Mergeable m2) {

    }

    private void visitEdge(Edge edge, SegmentVisitor segmentVisitor, Direction direction){
        edge.getLeftTerrain().accept(segmentVisitor, direction);
        edge.getMiddleTerrain().accept(segmentVisitor, direction);
        edge.getRightTerrain().accept(segmentVisitor, direction);
    }


}
