package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.animals.Goat;
import com.tigerzone.fall2016.animals.PlaceableAnimal;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;


public class Turn
{
    /**
     * Constructor creating the Turn, using the necessary information, such as Tile placement, position, etc..
     * @param playableTile Tile representing the TournamentPlayer's Turn.
     * @param playerID TournamentPlayer's ID number, assigned elsewhere.
     * @param rotationdegree Degree of rotation Counterclockwise that the player wishes to enact on the Tile.
     * @param predator Either a tiger or a crocodile that the player wishes to place on the tile
     * @param animalPlacementZone A value 1-9 that determines where the predator should be placed
     */
    private PlayableTile playableTile;
    private String playerID;
    private int rotationDegrees = 0;
    private Point position;
    private PlaceableAnimal placeableAnimal;
    private int animalPlacementZone;
    private Goat goat;

    public int getRotationDegrees() {
        return rotationDegrees;
    }
    public PlaceableAnimal getPlaceableAnimal() {
        return placeableAnimal;
    }
    public String getPlayerID(){
        return playerID;
    }
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the Tile that is embedded in a Turn.
     * @return PlayableTile: Tile that is located in this Turn.
     */

    public int getAnimalPlacementZone() {
        return animalPlacementZone;
    }
    public PlayableTile getPlayableTile() {
        return playableTile;
    }

    public boolean placingAnimal(){
        return (placeableAnimal == null) ? false: true;//If we have a Direction, there was a Follower placed.
    }

    // TODO: 11/15/2016 the input adapter coverts the tileString to a playableTile, creates predator, creates Point2D, etc. and makes this turn
    public Turn(String playerID, PlayableTile playableTile, Point tilePlacement, int rotationDegrees, PlaceableAnimal placeableAnimal, int zone) {
        this.playerID = playerID;
        this.playableTile = playableTile;
        this.rotationDegrees = rotationDegrees;
        this.position = tilePlacement;
        this.placeableAnimal = placeableAnimal;
        this.animalPlacementZone = zone;
    }
}