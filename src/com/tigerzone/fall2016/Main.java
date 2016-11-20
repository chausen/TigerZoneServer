package com.tigerzone.fall2016;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.CmdPromptInterface;
import com.tigerzone.fall2016server.tournament.Match;
import com.tigerzone.fall2016server.tournament.RoundRobinTest;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;
import com.tigerzone.fall2016server.tournament.TournamentPlayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
