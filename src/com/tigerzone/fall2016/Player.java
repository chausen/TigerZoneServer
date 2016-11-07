package com.tigerzone.fall2016;

import java.util.List;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Player {
    private int playerId;
    private int supply;

    /**
     * Increase supply by 1.
     * PRECONDITION: Supply <= 7
     */
    public void incrementSupply() {

    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementSupply() {

    }

    /**
     * Accessor for playerId (unique identifier for player)
     * @return int
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Accessor for supply (number of followers)
     * @return int
     */
    public int getSupply() {
        return supply;
    }
}
