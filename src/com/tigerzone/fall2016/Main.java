package com.tigerzone.fall2016;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;

import java.util.LinkedList;

/**
 * Created by Aidan on 11/7/2016.
 */
public class Main {
    public static void main(String args[]){

        CmdPromptInterface cmd = new CmdPromptInterface();
//        TextFilePort textFilePort = new TextFilePort();
//        LinkedList<PlayableTile> tileStack = textFilePort.createTiles();
        TileStackGenerator tileStackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = tileStackGenerator.createTilesFromTextFile(123456789);
        cmd.startGame(tileStack);

    }
}
