package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;

import java.util.LinkedList;

public interface TileReadAdapter
{
    public LinkedList<AreaTile> createTiles();
}