package com.tigerzone.fall2016.tileplacement.terrain;

/**
 * Created by Aidan on 11/10/2016.
 */
public interface SegmentVisitor {

    public void visit(LakeTerrain lakeTerrain, int i);
    public void visit(TrailTerrain trailTerrain, int i);
    public void visit(JungleTerrain jungleTerrain, int i);
    public void visit(DenTerrain denTerrain, int i);

}
