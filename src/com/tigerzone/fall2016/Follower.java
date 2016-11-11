package com.tigerzone.fall2016;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Follower {
    private int playerId;

    public Follower(int playerId){
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
