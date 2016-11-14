package com.tigerzone.fall2016.gamesystem;
import com.tigerzone.fall2016.adapters.TileReadAdapter;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

class TileStack
{
    private LinkedList<AreaTile> ll;
    long seed;

    public TileStack(long l, TileReadAdapter tra){
        seed = l;
        ll = new LinkedList<>(tra.createTiles());
}
    /**
     * Shuffle the TileStack providing we have a seed.
     */
    public void shuffle(){
        Collections.shuffle(ll,new Random(seed));
    }

    //TODO: Decide if reset() is even necessary?
    /*
    //Resets the current TileStack to be as it was when the game first started.
    public void reset(){

    }*/

    public AreaTile pop() {
        return ll.removeFirst();
    }

    public LinkedList<AreaTile> getTileList(){
        return ll;
    }
}