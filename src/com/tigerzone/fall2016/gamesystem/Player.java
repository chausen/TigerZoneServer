package com.tigerzone.fall2016.gamesystem;

import java.util.List;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Player {
    private String playerId;
    private int goodSupply; // Tigers
    private int badSupply;  // Crocodiles
    //new Meeple type Goat
    private int goatSupply;

    public Player(String playerId) {
        this.playerId = playerId;
        this.goodSupply = 7;
        this.badSupply = 2;
        this.goatSupply = 3;
    }

    /**
     * Increase supply by 1.
     * PRECONDITION: Supply <= 7
     */
    public void incrementGoodSupply() {
        if(this.goodSupply < 7){
            this.goodSupply++;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementGoatSupply() {
        if(getGoatSupply() >= 1){
            this.goodSupply--;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementGoodSupply() {
        if(getGoodSupply() >= 1){
            this.goodSupply--;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementBadSupply(){
        if(getBadSupply() >= 1){
            this.badSupply--;
        }
    }

    /**
     * Accessor for playerId (unique identifier for player)
     * @return String
     */
    public String getPlayerId() {
        return this.playerId;
    }

    /**
     * Accessor for good supply (number of tigers)
     * @return int
     */
    public int getGoodSupply() {
        return this.goodSupply;
    }

    /**
     * Accessor for bad supply (number of crocodiles)
     * @return
     */
    public int getBadSupply(){ return this.badSupply; }


    /**
     * Accessor for goat supply (number of goat)
     * @return
     */
    public int getGoatSupply(){ return this.goatSupply; }

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
