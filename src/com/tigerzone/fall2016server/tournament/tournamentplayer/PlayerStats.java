package com.tigerzone.fall2016server.tournament.tournamentplayer;

/**
 * Created by lenovo on 11/19/2016.
 */
public class PlayerStats {
    private int gamesCompleted;
    private int gamesPlayed;
    private int forfeits;
    private int ties;
    private int wins;
    private int losses;
    private int winsByForfeit;
    private int totalPoints;
    private int opponentTotalPoints;
    private int largestpointdifference;
    private double largestpointdifferencerelative;

    public PlayerStats() {
        gamesCompleted = gamesPlayed = forfeits = ties = wins = losses = winsByForfeit = totalPoints = opponentTotalPoints = largestpointdifference = 0;
        largestpointdifferencerelative = 0.0;
    }

    public int getGamesCompleted() {
        return gamesCompleted;
    }

    public void setGamesCompleted(int gamesCompleted) {
        this.gamesCompleted = gamesCompleted;
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
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getWinsByForfeit(){
        return winsByForfeit;
    }

    public void setWinsByForfeit(int winsByForfeit){
        this.winsByForfeit = winsByForfeit;
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
}
