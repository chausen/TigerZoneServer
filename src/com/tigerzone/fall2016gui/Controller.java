package com.tigerzone.fall2016gui;

import com.tigerzone.fall2016adapter.ViewInAdapter;
import com.tigerzone.fall2016adapter.ViewOutAdapter;
import com.tigerzone.fall2016server.server.TournamentServer;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Controller implements ViewOutAdapter {
    private boolean tournamentInProgress = false;
    private boolean cancellingTournament = false;
    private ViewInAdapter adapter;

    @FXML
    private TextField port;

    @FXML
    private TextField seed;

    @FXML
    private TextField maxConnections;

    @FXML
    private TextField tournamentID;

    @FXML
    private TextField numChallenges;

    @FXML
    private TextField tournamentOfDeath;

    @FXML
    private TextField serverPassword;

    @FXML
    private TextField ipAddress;

    @FXML
    private Button tournamentButton;

    @FXML
    private TableView<PlayerStats> playerStatsTable;

    @FXML
    private TableColumn<PlayerStats,String> teamColumn;

    @FXML
    private TableColumn<PlayerStats,Number> winsColumn;

    @FXML
    private TableColumn<PlayerStats,Number> lossesColumn;

    @FXML
    private TableColumn<PlayerStats,Number> tiesColumn;

    @FXML
    private TableColumn<PlayerStats,Number> winsByForfeitColumn;

    @FXML
    private TableColumn<PlayerStats,Number> lossesByForfeitColumn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label roundProgress;

    @FXML
    private Label challengeProgress;

    @FXML
    private void initialize() {
        try {
            ipAddress.setText(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            System.out.println(e.toString() + ": " + e.getMessage());
        }
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        winsColumn.setCellValueFactory(cellData -> cellData.getValue().winsProperty());
        lossesColumn.setCellValueFactory(cellData -> cellData.getValue().lossesProperty());
        tiesColumn.setCellValueFactory(cellData -> cellData.getValue().tiesProperty());
        winsByForfeitColumn.setCellValueFactory(cellData -> cellData.getValue().winsByForfeitProperty());
        lossesByForfeitColumn.setCellValueFactory(cellData -> cellData.getValue().lossesByForfeitProperty());
        //progressBar.setProgress(0);
    }

    @FXML
    private void tournamentButtonClick() {
        if (tournamentInProgress) {
            cancelTournament();
        } else {
            startTournament();
        }
    }

    private void startTournament() {
        int portNumber = Integer.parseInt(port.getText());
        int seedNumber = Integer.parseInt(seed.getText());
        int maxConnectionsNumber = Integer.parseInt(maxConnections.getText());
        int tournamentIDNumber = Integer.parseInt(tournamentID.getText());
        int numChallengesNumber = Integer.parseInt(numChallenges.getText());
        boolean isTournamentOfDeath = Boolean.parseBoolean(tournamentOfDeath.getText());

        TournamentServer tournamentServer =
                new TournamentServer(portNumber, seedNumber, maxConnectionsNumber,
                        tournamentIDNumber, numChallengesNumber, isTournamentOfDeath);
        tournamentServer.setViewOutAdapter(this);
        Thread thread = new Thread(tournamentServer);
        thread.start();

        adapter = tournamentServer;
        tournamentInProgress = true;
        tournamentButton.setText("In Progress");
        tournamentButton.getStyleClass().remove("activate-button");
        tournamentButton.getStyleClass().add("cancel-button");
    }

    private void cancelTournament() {
        if (!cancellingTournament) {
            cancellingTournament = true;
            adapter.cancelTournament();
            tournamentButton.setText("Cancelling");
            tournamentButton.getStyleClass().remove("cancel-button");
            tournamentButton.getStyleClass().add("cancelling-button");
        }
    }

    @Override
    public void notifyEndOfTournament() {
        tournamentInProgress = false;
        tournamentButton.setText("Start Tournament");
        if (!cancellingTournament) {

            tournamentButton.getStyleClass().remove("cancel-button");

        } else {

            tournamentButton.getStyleClass().remove("cancelling-button");

        }
        tournamentButton.getStyleClass().add("activate-button");
    }

    @Override
    public void notifyBeginningOfRound(int roundsCompleted, int totalRounds) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(new Double(roundsCompleted) / new Double(totalRounds));
                roundProgress.setText("Rounds\n" + roundsCompleted + " of " + totalRounds);
            }
        });
    }

    @Override
    public void notifyBeginningOfChallenge(int challengesCompleted, int totalChallenges) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(0);
                challengeProgress.setText("Challenges\n" + challengesCompleted + " of " + totalChallenges);
            }
        });
    }

    @Override
    public void giveListOfPlayerStats(ObservableList<PlayerStats> listOfPlayerStats) {
        playerStatsTable.setItems(listOfPlayerStats);
    }
}
