package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by lenovo on 11/18/2016.
 */
public final class TileStackGenerator {


    private TileStackGenerator() { }


    public static LinkedList<PlayableTile> generateTiles(String[] tiles, long seed) {
        LinkedList<PlayableTile> tilesStack = new LinkedList<>();

        for (int i=0; i<tiles.length; i++) {
            PlayableTile tile = new PlayableTile(tiles[i]);
            tilesStack.add(tile);
        }
        shuffle(tilesStack, seed);
        return tilesStack;
    }

    public static void shuffle(LinkedList<PlayableTile> tiles, long seed){
        Collections.shuffle(tiles,new Random(seed));
    }

}
