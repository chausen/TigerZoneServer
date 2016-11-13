package com.tigerzone.fall2016.tileplacement.terrain;

import com.tigerzone.fall2016.tileplacement.Direction;

/**
 * Created by Aidan on 11/11/2016.
 */
public interface SegmentVisitor {

    public void visitJungle(JungleTerrain jungleTerrain, Direction direction);
    public void visitDen(DenTerrain denTerrain, Direction direction);
    public void visitFree(FreeTerrain freeTerrain, Direction direction);
    public void visitLake(LakeTerrain lakeTerrain, Direction direction);
    public void visitTrail(TrailTerrain trailTerrain, Direction direction);

}
