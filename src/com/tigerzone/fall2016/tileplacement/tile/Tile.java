package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.tileplacement.terrain.Terrain;
import javafx.geometry.Point2D;

import java.util.HashMap;

/**
 * Created by Aidan on 11/7/2016.
 */
public abstract class Tile {

    private Point2D point2D;
    private HashMap<Direction, Terrain> segments = new HashMap<Direction, Terrain>();

}
