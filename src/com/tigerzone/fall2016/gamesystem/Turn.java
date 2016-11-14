package com.tigerzone.fall2016.gamesystem;
import com.tigerzone.fall2016.tileplacement.Direction;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;

public class Turn
{
    private final int playerID;
    private PlayableTile playableTile = null;
    private Direction d = null;
    private int rotationdegree = 0;
    private Point2D p;

    /**
     * Constructor creating the Turn, using the necessary information, such as Tile placement, position, etc..
     * @param playerID Player's ID number, assigned elsewhere.
     * @param playableTile Tile representing the Player's Turn.
     * @param rotationdegree Degree of rotation Counterclockwise that the player wishes to enact on the Tile.
     * @param d Direction of Follower placement (if any at all)
     * @param p Point of grid that the player wishes to place the Tile.
     */

    public Turn(int playerID, PlayableTile playableTile, int rotationdegree, Direction d, Point2D p) {
        this.playerID = playerID;
        this.playableTile = playableTile;
        this.rotationdegree = rotationdegree;
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
     * @return PlayableTile: Tile that is located in this Turn.
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

    public Point2D getPosition(){
        return p;
    }

    public int getOrientation(){
        return rotationdegree;
    }
}