package com.tigerzone.fall2016.area.terrainnode;


import java.util.List;

/**
 * Created by lenovo on 11/15/2016.
 */
interface NodeListAddable {

    void addToAppropriateList(List<JungleTerrainNode> jungleTerrainNodes, List<LakeTerrainNode> lakeTerrainNodes, List<TrailTerrainNode> trailTerrainNodes, List<DenTerrainNode> denTerrainNodes);


}
