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

    public PlayerStats() {
    }

    public PlayerStats(int gamesCompleted, int gamesPlayed, int forfeits, int ties, int wins, int losses) {
        this.gamesCompleted = gamesCompleted;
        this.gamesPlayed = gamesPlayed;
        this.forfeits = forfeits;
        this.ties = ties;
        this.wins = wins;
        this.losses = losses;
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
}
