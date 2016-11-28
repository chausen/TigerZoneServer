package com.tigerzone.fall2016server.scoreboard;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by matthewdiaz on 11/27/16.
 */
public class PlayerInfoBox{
    private HBox hBox;
    private static final String LOGIN_NAME = "LOGIN NAME: ";
    private static final String GAMES_PLAYED = "GAMES PLAYED: ";
    private static final String OUTRIGHT_WINS = "OUTRIGHT WINS: ";
    private static final String WINS_BY_FORFEIT = "WINS BY FORFEIT: ";
    private static final String TIES = "TIES: ";
    private static final String LOSSES = "LOSSES: ";
    private static final String TOTAL_POINTS = "TOTAL POINTS: ";
    private static final String AVERAGE_RELATIVE_PERFORMANCE = "AVERAGE RELATIVE PERFORMANCE: ";
    private static final String LARGEST_LOSS_POINT_DIFF  = "LARGEST LOSS POINT DIFF: ";
    private static final String LARGEST_LOSS_RELATIVE = "LARGEST LOSS RELATIVE: ";

    private Label loginName;
    private Label gamesPlayed;
    private Label outrightWins;
    private Label winsByForfeit;
    private Label ties;
    private Label losses;
    private Label totalPoints;
    private Label averageRelativePerformance;
    private Label largestLossPointDifferential;
    private Label largestLossRelative;

    PlayerInfoBox(String userID){
        hBox = new HBox();
        this.loginName = new Label(LOGIN_NAME + userID);
        this.gamesPlayed = new Label(GAMES_PLAYED + 0);
        this.outrightWins = new Label(OUTRIGHT_WINS + 0);
        this.winsByForfeit = new Label(WINS_BY_FORFEIT + 0);
        this.ties = new Label(TIES + 0);
        this.losses = new Label(LOSSES + 0);
        this.totalPoints = new Label(TOTAL_POINTS + 0);
        this.averageRelativePerformance = new Label(AVERAGE_RELATIVE_PERFORMANCE + 0);
        this.largestLossPointDifferential = new Label(LARGEST_LOSS_POINT_DIFF + 0);
        this.largestLossRelative = new Label(LARGEST_LOSS_RELATIVE + 0);
    }

    public void setLargestLossRelative(int largestLossRelative) {
        this.largestLossRelative = new Label(LARGEST_LOSS_RELATIVE + largestLossRelative);
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = new Label(GAMES_PLAYED + gamesPlayed);
    }

    public void setWinsByForfeit(int winsByForfeit) {
        this.winsByForfeit = new Label(WINS_BY_FORFEIT + winsByForfeit);
    }

    public void setTies(int ties) {
        this.ties = new Label(TIES + ties);
    }

    public void setLosses(int losses) {
        this.losses = new Label(LOSSES + losses);
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = new Label(TOTAL_POINTS + totalPoints);
    }

    public void setAverageRelativePerformance(int averageRelativePerformance) {
        this.averageRelativePerformance = new Label(AVERAGE_RELATIVE_PERFORMANCE + averageRelativePerformance);
    }

    public void setLargestLossPointDifferential(int largestLossPointDifferential) {
        this.largestLossPointDifferential = new Label(LARGEST_LOSS_POINT_DIFF + largestLossPointDifferential);
    }
}
