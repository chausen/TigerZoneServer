package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;
import java.util.List;

/**
 * Created by lenovo on 11/7/2016.
 */
public class AreaManager {

    private List<DenArea> denAreas;
    private List<JungleArea> jungleAreas;
    private List<LakeArea> lakeAreas;
    private List<TrailArea> trailAreas;

    public AreaManager(List<DenArea> denAreas, List<JungleArea> jungleAreas, List<LakeArea> lakeAreas, List<TrailArea> trailAreas) {
        this.denAreas = denAreas;
        this.jungleAreas = jungleAreas;
        this.lakeAreas = lakeAreas;
        this.trailAreas = trailAreas;
    }



}
