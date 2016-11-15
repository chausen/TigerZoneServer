package com.tigerzone.fall2016.gamesystem;
import javafx.geometry.Point2D;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

public class Turn {

    private PlayableTile playableTile;
    private String playerID;
    private int rotationDegrees = 0;
    private Point2D position;
    private Predator predator;
    private int predatorPlacementZone;

    public int getRotationDegrees() {
        return rotationDegrees;
    }
    public Predator getPredator() {
        return predator;
    }
    public String getPlayerID(){
        return playerID;
    }
    public Point2D getPosition() {
        return position;
    }
    public int getPredatorPlacementZone() {
        return predatorPlacementZone;
    }
    public PlayableTile getPlayableTile() {
        return playableTile;
    }

    public boolean placingPredator(){
        return (predator == null) ? false : true;//If we have a Direction, there was a Follower placed.
    }

    // TODO: 11/15/2016 the input adapter coverts the tileString to a playableTile, creates predator, creates Point2D, etc. and makes this turn
    public Turn(String playerID, PlayableTile playableTile, Point2D tilePlacement, int rotationDegrees, Predator predator, int zone) {
        this.playerID = playerID;
        this.playableTile = playableTile;
        this.rotationDegrees = rotationDegrees;
        this.position = tilePlacement;
        this.predator=predator;
        this.predatorPlacementZone = zone;
    }

}