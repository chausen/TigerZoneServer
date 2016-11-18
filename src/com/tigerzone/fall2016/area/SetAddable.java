package com.tigerzone.fall2016.area;

import java.util.List;
import java.util.Set;

/**
 * Created by Aidan on 11/15/2016.
 */
public interface SetAddable {

    void addToAppropriateSet(Set<TrailArea> trailAreas, Set<JungleArea> jungleAreas, Set<LakeArea> lakeAreas);

}
