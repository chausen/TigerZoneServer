package com.tigerzone.fall2016adapter;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.LinkedList;

public interface TileReadAdapter
{
    public LinkedList<PlayableTile> createTiles();
}