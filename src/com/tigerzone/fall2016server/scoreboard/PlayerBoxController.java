package com.tigerzone.fall2016server.scoreboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by clayhausen on 11/27/16.
 */
public class PlayerBoxController {
    @FXML
    private Label loginName;

    @FXML
    private Label gamesPlayed;

    @FXML
    private Label outrightWins;

    @FXML
    private Label winsByForfeit;

    @FXML
    private Label ties;

    @FXML
    private Label losses;

    @FXML
    private Label totalPoints;

    @FXML
    private Label averageRelativePerformance;

    @FXML
    private Label largestLossPointDifferential;

    @FXML
    private Label largestLossRelative;

    public void setLoginName(String loginName) {
        this.loginName = new Label(loginName);
    }

}
