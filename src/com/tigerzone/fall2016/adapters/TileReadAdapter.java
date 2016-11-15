package com.tigerzone.fall2016.adapters;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;

public interface TileReadAdapter
{
    public LinkedList<PlayableTile> createTiles();
}