package com.tigerzone.fall2016server.scoreboard;


import com.tigerzone.fall2016server.files.FileReader;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by clayhausen on 11/27/16.
 */
public class PlayerBoxController{
    //private Map<String, PlayerInfoBox> playerInfoBoxMap;
    private Scoreboard scoreBoard;

    public PlayerBoxController(){
        //this.playerInfoBoxMap = new HashMap<>();
        scoreBoard = new Scoreboard();
        new Thread(scoreBoard).start();
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
        Map<String, PlayerInfoBox> playerInfoBoxMap = scoreBoard.getPlayerInfoBoxHashMap();
        System.out.println("Lenght of map: " + playerInfoBoxMap.size());
        if(playerInfoBoxMap.containsKey(userID)){
            System.out.println("IN HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            PlayerInfoBox userInfoBox = playerInfoBoxMap.get(userID);

            userInfoBox.setAverageRelativePerformance(playerStats.getAvgRelPerf());
            userInfoBox.setGamesPlayed(playerStats.getGamesPlayed());
            userInfoBox.setLargestLossPointDifferential(playerStats.getLosses());
            userInfoBox.setLargestLossRelative(playerStats.getLargestpointdifferencerelative());
            userInfoBox.setLosses(playerStats.getLosses());
            userInfoBox.setWinsByForfeit(playerStats.getWinsByForfeit());
            userInfoBox.setTies(playerStats.getTies());
            userInfoBox.setTotalPoints(playerStats.getTotalPoints());
            userInfoBox.setOutrightWins(playerStats.getWins());
        }
    }
}
