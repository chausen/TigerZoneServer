package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBuilder;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentAdder;
import com.tigerzone.fall2016.tileplacement.terrain.SegmentVisitor;
import com.tigerzone.fall2016.tileplacement.terrain.TrailTerrain;
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
    private HashMap<Point2D, FreeSpace> freeSpaceHashMap;

    public AreaManager(List<DenArea> denAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas, List<TrailArea> trailAreas) {
        this.denAreas = denAreas;
        this.jungleAreas = jungleAreas;
        this.lakeAreas = lakeAreas;
        this.trailAreas = trailAreas;
        //You know what these need to be built at the beginning of the game
        areaTileHashMap = new HashMap<>();
        freeSpaceHashMap = new HashMap<>();
    }

    public void updateAreas(Point2D position, AreaTile areaTile){
        areaTileHashMap.put(position, areaTile);
        FreeSpace freeSpace = freeSpaceHashMap.get(position);
        updateTrail(position, areaTile, freeSpace);
        updateJungle(position, areaTile, freeSpace);
    }

    private void updateTrail(Point2D position, AreaTile areaTile, FreeSpace freeSpace){
        for(TrailArea trailArea: trailAreas){
            HashMap<Point2D, FreeSpace> taFreeSpaceMap = trailArea.getFreeSpaceMap();
            FreeSpaceBuilder freeSpaceBuilder = new FreeSpaceBuilder(freeSpace, areaTile, trailArea.getFreeSpaceMap());
            if(taFreeSpaceMap.get(position) != null){
                trailArea.updateArea(areaTile);
                freeSpaceBuilder.makeFreeSpace(position, true);
            }
            else {
                freeSpaceBuilder.makeFreeSpace(position, false);
            }
        }

        if(freeSpace.getNorthTerrain().isFree() && areaTile.getNorthEdge().getMiddleTerrain().accept(new TrailTerrain())){
            HashMap<Point2D, FreeSpace> newFSHashMap = new HashMap<>();
            TrailArea trailArea = new TrailArea(position,areaTile,newFSHashMap);
        }
        if(freeSpace.getEastTerrain().isFree() && areaTile.getEastEdge().getMiddleTerrain().accept(new TrailTerrain())){

        }
        if(freeSpace.getSouthTerrain().isFree() && areaTile.getSouthEdge().getMiddleTerrain().accept(new TrailTerrain())){

        }
        if(freeSpace.getWestTerrain().isFree() && areaTile.getWestEdge().getMiddleTerrain().accept(new TrailTerrain())){

        }
    }

    private void updateJungle(Point2D position, AreaTile areaTile, FreeSpace freeSpace){
        SegmentAdder segmentAdder = new SegmentAdder();
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

    private void merge(Mergeable m1, Mergeable m2) {

    }

    private void visitEdge(Edge edge, SegmentVisitor segmentVisitor, Direction direction){
        edge.getLeftTerrain().accept(segmentVisitor, direction);
        edge.getMiddleTerrain().accept(segmentVisitor, direction);
        edge.getRightTerrain().accept(segmentVisitor, direction);
    }


}