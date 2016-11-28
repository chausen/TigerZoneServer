package com.tigerzone.fall2016server.scoreboard;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by matthewdiaz on 11/27/16.
 */
public class PlayerInfoBox {
    private static final String LOGIN_NAME = "LOGIN NAME: ";
    private static final String GAMES_PLAYED = "GAMES PLAYED: ";
    private static final String OUTRIGHT_WINS = "OUTRIGHT WINS: ";
    private static final String WINS_BY_FORFEIT = "WINS BY FORFEIT: ";
    private static final String TIES = "TIES: ";
    private static final String LOSSES = "LOSSES: ";
    private static final String TOTAL_POINTS = "TOTAL POINTS: ";
    private static final String AVERAGE_RELATIVE_PERFORMANCE = "AVG. REL. PERFORMANCE: ";
    private static final String LARGEST_LOSS_POINT_DIFF  = "LARGEST LOSS POINT DIFF: ";
    private static final String LARGEST_LOSS_RELATIVE = "LARGEST LOSS RELATIVE: ";

    private VBox vBox;
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
        this.vBox = new VBox();
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
        updateBox();
    }

    public VBox getvBox() {
        return this.vBox;
    }

    public void setOutrightWins(int outrightWins){
        this.outrightWins = new Label(OUTRIGHT_WINS + outrightWins);
        updateBox();
    }

    public void setLargestLossRelative(double largestLossRelative) {
        this.largestLossRelative = new Label(LARGEST_LOSS_RELATIVE +
                roundDoubleToNearestHundredth(largestLossRelative));
        updateBox();
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = new Label(GAMES_PLAYED + gamesPlayed);
        updateBox();
    }

    public void setWinsByForfeit(int winsByForfeit) {
        this.winsByForfeit = new Label(WINS_BY_FORFEIT + winsByForfeit);
        updateBox();
    }

    public void setTies(int ties) {
        this.ties = new Label(TIES + ties);
        updateBox();
    }

    public void setLosses(int losses) {
        this.losses = new Label(LOSSES + losses);
        updateBox();
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = new Label(TOTAL_POINTS + totalPoints);
        updateBox();
    }

    public void setAverageRelativePerformance(double averageRelativePerformance) {
        this.averageRelativePerformance = new Label(AVERAGE_RELATIVE_PERFORMANCE +
                roundDoubleToNearestHundredth(averageRelativePerformance));
        updateBox();
    }

    public void setLargestLossPointDifferential(int largestLossPointDifferential) {
        this.largestLossPointDifferential = new Label(LARGEST_LOSS_POINT_DIFF + largestLossPointDifferential);
        updateBox();
    }

    private double roundDoubleToNearestHundredth(double value){
        value = Math.round(value * 100);
        return value/100;
    }

    private void updateBox() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().clear();
                vBox.getChildren().add(loginName);
                vBox.getChildren().add(gamesPlayed);
                vBox.getChildren().add(outrightWins);
                vBox.getChildren().add(winsByForfeit);
                vBox.getChildren().add(ties);
                vBox.getChildren().add(losses);
                vBox.getChildren().add(totalPoints);
                vBox.getChildren().add(averageRelativePerformance);
                vBox.getChildren().add(largestLossPointDifferential);
                vBox.getChildren().add(largestLossRelative);
            }
        });
    }
}
