package com.tigerzone.fall2016server;

import com.tigerzone.fall2016.gamesystem.TileStack;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;

import java.util.LinkedList;

/**
 * Created by lenovo on 11/27/2016.
 */
public class TileMain {

    public static void main(String[] args) {


        LinkedList<PlayableTile> tiles = TileStackGenerator.generateTiles(1);
        TileStack tileStack = new TileStack(tiles);

        tileStack.truncateTS(12);
        LinkedList<PlayableTile> trimmedStack = tileStack.getTileList();

//        for (PlayableTile tile: tiles) {
//            System.out.println(tile.getTileString());
//
//        }

        int i = 1;
        for (PlayableTile tile: trimmedStack) {
            System.out.println(i + " " + tile.getTileString());
            i++;
        }

    }

}
