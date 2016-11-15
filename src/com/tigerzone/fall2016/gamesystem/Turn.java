package com.tigerzone.fall2016.gamesystem;
import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.Point;

public class Turn
{
    private final int playerID;
    private PlayableTile playableTile;
    private Direction d = null;
    private int rotations = 0;
    private Point p;

    /**
     * Constructor creating the Turn, using the necessary information, such as Tile placement, position, etc..
     * @param playerID Player's ID number, assigned elsewhere.
     * @param playableTile Tile representing the Player's Turn.
     * @param rotations Number of rotations that the player wishes to enact on the Tile.
     * @param d Direction of Follower placement (if any at all)
     * @param p Point of grid that the player wishes to place the Tile.
     */
    public Turn(int playerID, PlayableTile playableTile, int rotations, Direction d, Point p) {
        this.playerID = playerID;
        this.playableTile = playableTile;
        this.rotations = rotations;
        this.d = d;
        this.p = p;
    }

    /**
     * Gets the Player ID of the player who "owns" this Turn.
     * @return int: Player's ID.
     */
    public int getPlayerID(){
        return playerID;
    }

    /**
     * Returns the Tile that is embedded in a Turn.
     * @return AreaTile: Tile that is located in this Turn.
     */
    public PlayableTile getTile() {
        return playableTile;
    }

    /**
     * Returns whether or not this Turn has had a Follower placed on the corresponding AreaTile.
     * @return boolean: If a follower exists in a Turn.
     */
    public boolean hasFollower(){
        return (d == null) ? false : true;//If we have a Direction, there was a Follower placed.
    }

    public Direction getFollowerDirection(){
        return d;
    }

    public void placeFollower(Direction d){
        this.d = d;
    }

    public Point getPosition(){
        return p;
    }

    public int getOrientation(){
        return rotations;
    }
}