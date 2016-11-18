package com.tigerzone.fall2016;

import com.tigerzone.fall2016.ports.CMDPromptPort;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.CmdPromptInterface;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Aidan on 11/7/2016.
 */
public class Main {
    public static void main(String args[]){
        CmdPromptInterface cmd = new CmdPromptInterface();
<<<<<<< HEAD
        //cmd.startGame();
=======
        TextFilePort textFilePort = new TextFilePort();
        LinkedList<PlayableTile> tileStack = textFilePort.createTiles();
        cmd.startGame(tileStack);
>>>>>>> 5ddc6d03b0c716c126607d3efc88278848ef644f
    }
}
