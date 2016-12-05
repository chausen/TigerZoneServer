package com.tigerzone.fall2016adapter.ports;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import com.tigerzone.fall2016server.tournament.TileStackGenerator;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Jeff on 2016/11/16.
 */
public class CMDPromptPortTest {

    @Test
    public void testSendTurn() throws Exception {
        Scanner sc = null;
        {
            try {
                sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/Game.txt").getFile()));
            } catch(FileNotFoundException exc){System.out.println("FATAL: The Tile text file cannot be found.");}
        }
//        TextFilePort textFilePort = new TextFilePort();
//        LinkedList<PlayableTile> tileStack = textFilePort.createTiles();

        TileStackGenerator tileStackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = tileStackGenerator.createTilesFromTextFile(123456789);

//        CMDPromptPort cmdp = new CMDPromptPort(1, "Taco", "Bell", tileStack);
//        cmdp.initialize();
//        cmdp.inAdapter.truncateTS(20);//Make the tile set only 20
//        while(!cmdp.getMessageQueue().isEmpty()){
//            System.out.println(cmdp.getMessageQueue().remove());
//        }
//        while (!cmdp.isGameOver() && sc.hasNext()) {
//            String line = sc.nextLine();
//            cmdp.receiveTurn(line);
//            System.out.println(cmdp.getMessageQueue().remove());
//        }
    }
}