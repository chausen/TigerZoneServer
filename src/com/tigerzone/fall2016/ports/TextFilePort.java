package com.tigerzone.fall2016.ports;
import com.tigerzone.fall2016.adapters.TileReadAdapter;
import com.tigerzone.fall2016.tileplacement.terrain.*;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

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
            String s = sc.nextLine();//Get Top of Tile.
            char[] terrainarray = new char[9];
             for(int q = 0; q < 3; q++) {
                 for (int x = 0; x < s.length(); x++) {
                     terrainarray[x+(q*3)] = s.charAt(x);//Get all nine characters.
                 }
                 s = sc.nextLine();
             }

            //TODO: String s now has any "prey" or "predator" on the Tile. Add check for this.

            //Going clockwise with a non-rotated Tile.
            Edge N = new Edge(charToTerrain(terrainarray[0]),charToTerrain(terrainarray[1]),charToTerrain(terrainarray[2]));
            Edge E = new Edge(charToTerrain(terrainarray[2]),charToTerrain(terrainarray[5]),charToTerrain(terrainarray[8]));
            Edge S = new Edge(charToTerrain(terrainarray[8]),charToTerrain(terrainarray[7]),charToTerrain(terrainarray[6]));
            Edge W = new Edge(charToTerrain(terrainarray[6]),charToTerrain(terrainarray[3]),charToTerrain(terrainarray[0]));
            Terrain center = charToTerrain(terrainarray[4]);
            for(int x = 0; x < multiple; x++)//For each multiple
            {
                //TODO: We no longer have AreaTile we need to talk about this!!!
                //set.add(new AreaTile(N, E, S, W, center));//Add a multiple Number of Tiles to the set.
            }
        }
        return set;
    }

    /**
     * Method which gives a Terrain corresponding to whatever Character we give it.
     * @param c Char which represents which 'Terrain' we have.
     * @return
     */
    private Terrain charToTerrain(char c) {
        Terrain t = new FreeTerrain();
        switch(c)
        {
            case 'L':
                t = new LakeTerrain();
                break;

            case 'J':
                t = new JungleTerrain();
                break;

            case 'D':
                t = new DenTerrain();
                break;

            case 'T':
                t = new TrailTerrain();
                break;

            default:
                break;
        }
        return t;
    }
}
