package com.tigerzone.fall2016gui;

import com.tigerzone.fall2016adapter.ViewOutAdapter;
import com.tigerzone.fall2016server.server.TournamentServer;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Controller implements ViewOutAdapter {
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
    }

    @FXML
    private void startTournament() {
        int portNumber = Integer.parseInt(port.getText());
        int seedNumber = Integer.parseInt(seed.getText());
        int maxConnectionsNumber = Integer.parseInt(maxConnections.getText());
        int tournamentIDNumber = Integer.parseInt(tournamentID.getText());
        int numChallengesNumber = Integer.parseInt(numChallenges.getText());
        TournamentServer tournamentServer =
                new TournamentServer(portNumber, seedNumber, maxConnectionsNumber, tournamentIDNumber, numChallengesNumber);

        tournamentServer.setViewOutAdapter(this);
        Thread t = new Thread(tournamentServer);
        t.start();
        tournamentButton.setText("Tournament in progress...");
    }


    @Override
    public void notifyEndOfTournament() {
        tournamentButton.setText("Start Tournament");
    }

    @Override
    public void giveListOfPlayerStats(ObservableList<PlayerStats> listOfPlayerStats) {
        playerStatsTable.setItems(listOfPlayerStats);
    }
}
