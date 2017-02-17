package com.tigerzone.fall2016adapter.ports;
import com.tigerzone.fall2016adapter.TileReadAdapter;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jeff on 2016/11/07.(*´ω｀*)
 */

//This class is a
public class TextFilePort implements TileReadAdapter
{
    Scanner sc = null;
    public TextFilePort()
    {
        try{
            sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/TileCreation.txt").getFile()));
        }catch(FileNotFoundException exc){System.out.println("FATAL: The AreaTile text file cannot be found.");}
    }

    @Override
    public LinkedList<PlayableTile> createTiles() {
        LinkedList<PlayableTile> set = new LinkedList<>();
        while(sc.hasNextLine()) {
            int multiple = sc.nextInt();//Number of this Tile to add.
           sc.nextLine();//Move marker to next line.
            String s = sc.nextLine();//Get Tile String rep.
            for(int x = 0; x < multiple; x++)//For each multiple
            {
                set.add(new PlayableTile(s));//Add a multiple Number of Tiles to the set.
            }
        }
        return set;
    }

    public String[] createStringTiles(){
        String[] tiles = new String[76];
        while(sc.hasNextLine()) {
            int i = 0;
            int multiple = sc.nextInt();//Number of this Tile to add.
            sc.nextLine();//Move marker to next line.
            String s = sc.nextLine();//Get Tile String rep.
            for(int x = 0; x < multiple; x++)//For each multiple
            {
                tiles[i++] = s;
            }
        }
        return tiles;
    }

}
