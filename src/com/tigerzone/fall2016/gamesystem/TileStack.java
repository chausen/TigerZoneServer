package com.tigerzone.fall2016.gamesystem;
import com.tigerzone.fall2016.adapters.TileReadAdapter;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

class TileStack
{
    private LinkedList<PlayableTile> ll;
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

    public PlayableTile pop() {
        return ll.removeFirst();
    }

    public LinkedList<PlayableTile> getTileList(){
        return ll;
    }

    public boolean equals(TileStack ts)
    {
        LinkedList<PlayableTile> ll = ts.getTileList();
        for(int x = 0; x < ll.size(); x++)
        {
            if(!this.ll.get(x).getTileString().equals(ll.get(x).getTileString())){
                return false;
            }
        }
        return true;//They matched the entire way, so we're good.
    }
}