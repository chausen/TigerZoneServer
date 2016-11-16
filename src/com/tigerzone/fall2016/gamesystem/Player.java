package com.tigerzone.fall2016.gamesystem;

import java.util.List;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Player {
    private String playerId;
    private int goodSupply; // Tigers
    private int badSupply;  // Crocodiles

    public Player(String playerId) {
        this.playerId = playerId;
        this.goodSupply = 7;
        this.badSupply = 2;
    }

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
     * @return String
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Accessor for supply (number of followers)
     * @return int
     */
    public int getSupply() {
        return goodSupply;
    }

    /**
     * Determines whether this player is equal to another based on playerID
     *
     * @param  player The player you're comparing this one too
     * @return  true if player has the same playerID as this
     */
    public boolean equals(Player player) {

        String otherPlayerID = player.getPlayerId();

        if (this.playerId.equals(otherPlayerID)) {
            return true;
        }

        return false;

    }
}
