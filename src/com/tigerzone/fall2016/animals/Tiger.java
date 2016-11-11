package com.tigerzone.fall2016.animals;

/**
 * Created by Aidan on 11/9/2016.
 */
public class Tiger extends Predator {
    private int playerId;

    public Tiger(int playerId){
        this.playerId = playerId;
    }

    public int getPlayerId(){
        return this.playerId;
    }
}
