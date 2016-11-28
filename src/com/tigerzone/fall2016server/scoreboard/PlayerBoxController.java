package com.tigerzone.fall2016server.scoreboard;


import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016server.files.FileReader;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by clayhausen on 11/27/16.
 */
public class PlayerBoxController{
    private Scoreboard scoreBoard;

    @FXML
    private Label player1Id = new Label();

    public PlayerBoxController(){
        //this.playerInfoBoxMap = new HashMap<>();
//        scoreBoard = new Scoreboard();
//        new Thread(scoreBoard).start();
//        scoreBoard.initializePlayers(this.playerInfoBoxMap);
//        while (playerInfoBoxMap != null) {
//            playerInfoBoxMap = scoreBoard.getPlayerInfoBoxHashMap();
//        }
    }

    /**
     * Updates a users
     * @param userID
     * @param playerStats
     */
    public void updatePlayerInfoBox(String userID, PlayerStats playerStats){
        player1Id.setText("PLAYER1");
//        scoreBoard.setPlayer(userID);
//        if(playerInfoBoxHashMap.containsKey(userID)){
//            System.out.println("IN HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            PlayerInfoBox userInfoBox = playerInfoBoxHashMap.get(userID);
//
//            userInfoBox.setAverageRelativePerformance(playerStats.getAvgRelPerf());
//            userInfoBox.setGamesPlayed(playerStats.getGamesPlayed());
//            userInfoBox.setLargestLossPointDifferential(playerStats.getLosses());
//            userInfoBox.setLargestLossRelative(playerStats.getLargestpointdifferencerelative());
//            userInfoBox.setLosses(playerStats.getLosses());
//            userInfoBox.setWinsByForfeit(playerStats.getWinsByForfeit());
//            userInfoBox.setTies(playerStats.getTies());
//            userInfoBox.setTotalPoints(playerStats.getTotalPoints());
//            userInfoBox.setOutrightWins(playerStats.getWins());
//        }
    }
}
