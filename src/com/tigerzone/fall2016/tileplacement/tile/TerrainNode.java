package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

import java.util.List;

/**
 * Created by Aidan on 11/14/2016.
 */
public class TerrainNode {

    Terrain terrain;
    BoardTile boardTile;
    List<TerrainNode> terrainNodeList;
    List<Integer> zones;
    Animal animal;


}
