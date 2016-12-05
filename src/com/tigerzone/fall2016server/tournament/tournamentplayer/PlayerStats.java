package com.tigerzone.fall2016server.tournament.tournamentplayer;

import com.tigerzone.fall2016server.server.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by lenovo on 11/19/2016.
 */
public class PlayerStats {
    private int gamesPlayed;
    private int forfeits;
    private int ties;
    private int wins;
    private int losses;
    private int winsByForfeit;
    private int lossesByForfeit;
    private int totalPoints;
    private int opponentTotalPoints;
    private int largestpointdifference;
    private double largestpointdifferencerelative;

    // Properties used by javafx for GUI
    private StringProperty usernameProperty;
    private IntegerProperty winsProperty;
    private IntegerProperty lossesProperty;
    private IntegerProperty forfeitsProperty;
    private IntegerProperty tiesProperty;
    private IntegerProperty winsByForfeitProperty;
    private IntegerProperty lossesByForfeitProperty;

    public PlayerStats(StringProperty username) {
        this.usernameProperty = username;
        lossesByForfeit = gamesPlayed = forfeits = ties = wins = losses = winsByForfeit = totalPoints = opponentTotalPoints = largestpointdifference = 0;
        largestpointdifferencerelative = 0.0;

        winsProperty = new SimpleIntegerProperty(wins);
        lossesProperty = new SimpleIntegerProperty(losses);
        forfeitsProperty = new SimpleIntegerProperty(forfeits);
        tiesProperty = new SimpleIntegerProperty(ties);
        winsByForfeitProperty = new SimpleIntegerProperty(winsByForfeit);
        lossesByForfeitProperty = new SimpleIntegerProperty(lossesByForfeit);
        Logger.addPlayerStats(this);
    }
    public int getLossesByForfeit(){
        return lossesByForfeit;
    }

    public void setLossesByForfeit(int lossesByForfeit){
        this.lossesByForfeit = lossesByForfeit;
        this.lossesProperty.set(lossesByForfeit);
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getForfeits() {
        return forfeits;
    }

    public void setForfeits(int forfeits) {
        this.forfeits = forfeits;
        this.forfeitsProperty.set(forfeits);
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
        this.tiesProperty.set(ties);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
        this.winsProperty.set(wins);
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
        this.lossesProperty.set(losses);
    }

    public int getWinsByForfeit(){
        return winsByForfeit;
    }

    public void setWinsByForfeit(int winsByForfeit){
        this.winsByForfeit = winsByForfeit;
        this.winsByForfeitProperty.set(winsByForfeit);
    }

    public void setLargestpointdifference(int mypoints, int opponentpoints){
        if((mypoints - opponentpoints) < largestpointdifference) {
            largestpointdifference = (mypoints - opponentpoints);
            setLargestpointdifferencerelative(mypoints, opponentpoints);
        }
    }

    public int getLargestpointdifference(){
        return largestpointdifference;
    }

    private void setLargestpointdifferencerelative(int mypoints, int opponentpoints){
        largestpointdifferencerelative = ((double) mypoints / (double)opponentpoints);
    }

    public double getLargestpointdifferencerelative(){
        return largestpointdifferencerelative;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getOpponentTotalPoints(){
        return opponentTotalPoints;
    }
    public void setOpponentTotalPoints(int opponentTotalPoints){
        this.opponentTotalPoints = opponentTotalPoints;
    }

    public double getAvgRelPerf() {
        if ((double) gamesPlayed - (double) getWinsByForfeit() == 0) {
            return 0.0;
        }
        else if ((opponentTotalPoints) == 0) return 0.0;
            else return ((totalPoints / opponentTotalPoints) / ((double) gamesPlayed - (double) getWinsByForfeit()));
        }

    public void setTotalPoints(int totalpoints) {
        totalPoints = totalpoints;
    }

    public StringProperty usernameProperty() {
        return this.usernameProperty;
    }

    public IntegerProperty winsProperty() {
        return winsProperty;
    }

    public IntegerProperty lossesProperty() {
        return lossesProperty;
    }

    public IntegerProperty forfeitsProperty() {
        return forfeitsProperty;
    }

    public IntegerProperty tiesProperty() {
        return tiesProperty;
    }

    public IntegerProperty winsByForfeitProperty() {
        return winsByForfeitProperty;
    }

    public IntegerProperty lossesByForfeitProperty() {
        return lossesByForfeitProperty;
    }
}
