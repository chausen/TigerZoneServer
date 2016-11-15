package com.tigerzone.fall2016.gamesystem;
import com.sun.javafx.geom.Point2D;
import com.tigerzone.fall2016.animals.Predator;

public class Turn {


    private String playerID;
    private String tileString;
    private int rotationDegrees = 0;
    private Point2D position;
    private Predator predator;
    private int predatorPlacementZone;

    public String getTileString() {
        return tileString;
    }
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

    public boolean hasFollower(){
        return (predator == null) ? false : true;//If we have a Direction, there was a Follower placed.
    }


    public Turn(String playerID, String tileString, Point2D position, int rotationDegrees, Predator predator, int zone) {
        this.playerID = playerID;
        this.tileString = tileString;
        this.rotationDegrees = rotationDegrees;
        this.position = position;
        this.predator=predator;
        this.predatorPlacementZone = zone;
    }

}