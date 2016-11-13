package com.tigerzone.fall2016;

/**
 * Created by clayhausen on 11/7/16.
 */
public abstract class Animal {
    private int playerId;

    public Animal(int playerId){
        this.playerId = playerId;
    }

    /**
     * Accessor for playerId (unique identifier for player)
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }
}
