package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/11/2016.
 */
public interface SegmentVisitor {

    public void visitDen(DenTerrain denTerrain);
    public void visitFree(FreeTerrain freeTerrain);
    public void visitLake(LakeTerrain lakeTerrain);
    public void visitTrail(TrailTerrain trailTerrain);
    public void visitJungle(JungleTerrain jungleTerrain);
}
