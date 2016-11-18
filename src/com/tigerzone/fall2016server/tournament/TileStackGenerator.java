package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by lenovo on 11/18/2016.
 */
public class TileStackGenerator {


    public TileStackGenerator() { }


    public static LinkedList<PlayableTile> generateTiles(String[] tiles, long seed) {
        LinkedList<PlayableTile> tileStack = new LinkedList<>();

        for (int i=0; i<tiles.length; i++) {
            PlayableTile tile = new PlayableTile(tiles[i]);
            tileStack.add(tile);
        }
        shuffle(tileStack, seed);
        return tileStack;
    }

    public static void shuffle(LinkedList<PlayableTile> tiles, long seed){
        Collections.shuffle(tiles,new Random(seed));
    }

    public LinkedList<PlayableTile> createTilesFromTextFile(long seed) {
        LinkedList<PlayableTile> stack = new LinkedList<>();
        Scanner sc = null;
        try{
            sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/TileCreation.txt").getFile()));
        }catch(FileNotFoundException exc){System.out.println("FATAL: The AreaTile text file cannot be found.");}
            while(sc.hasNextLine()) {
                int multiple = sc.nextInt();//Number of this Tile to add.
                sc.nextLine();//Move marker to next line.
                String s = sc.nextLine();//Get Tile String rep.
                for(int x = 0; x < multiple; x++)//For each multiple
                {
                    stack.add(new PlayableTile(s));//Add a multiple Number of Tiles to the set.
                }
            }
            Collections.shuffle(stack, new Random(seed));
            return stack;
        }
    }

