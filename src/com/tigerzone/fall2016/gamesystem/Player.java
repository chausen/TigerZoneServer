package com.tigerzone.fall2016.gamesystem;

import java.util.List;

/**
 * Created by clayhausen on 11/7/16.
 */
public class Player {
    private String playerId;
    private int tigerSupply; // Tigers
    private int crocSupply;  // Crocodiles
    private int goatSupply;

    public Player(String playerId) {
        this.playerId = playerId;
        this.tigerSupply = 7;
        this.crocSupply = 2;
        this.goatSupply = 3;
    }

    /**
     * Increase supply by 1.
     * PRECONDITION: Supply <= 7
     */
    public void incrementTigerSupply() {
        if(this.tigerSupply < 7){
            this.tigerSupply++;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementTigerSupply() {
        if(this.tigerSupply >= 1){
            this.tigerSupply--;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementCrocSupply(){
        if(this.crocSupply >= 1){
            this.crocSupply--;
        }
    }

    /**
     * Decrease supply by 1.
     * POSTCONDITION: If supply is 0, game is forfeited
     */
    public void decrementGoatSupply(){
        if(this.goatSupply >= 1){
            this.goatSupply--;
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
    public int getTigerSupply() {
        return this.tigerSupply;
    }

    /**
     * Accessor for bad supply (number of crocodiles)
     * @return
     */
    public int getCrocSupply(){ return this.crocSupply; }


    /**
     * Accessor for goat supply (number of goats)
     * @return
     */
    public int getGoatSupply() {
        return this.goatSupply;
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
