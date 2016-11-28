package com.tigerzone.fall2016server.scoreboard;


import com.tigerzone.fall2016server.files.FileReader;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clayhausen on 11/27/16.
 */
public class PlayerBoxController{
    private Map<String, PlayerInfoBox> playerInfoBoxMap;
    private Scoreboard scoreBoard;

    public PlayerBoxController(){
        playerInfoBoxMap = new HashMap<>();
        scoreBoard = new Scoreboard();
        new Thread(scoreBoard).start();
        initializePlayers();
    }

    /**
     * Method creates a new HBox for Tournament Player added
     * @param userID
     */
    private void createPlayerInfoBox(String userID){
        PlayerInfoBox playerInfoBox = new PlayerInfoBox(userID);
        playerInfoBoxMap.put(userID, playerInfoBox);
        scoreBoard.addPlayerInfoBox(playerInfoBox);
    }

    /**
     * Updates a users
     * @param userID
     * @param playerStats
     */
    public void updatePlayerInfoBox(String userID, PlayerStats playerStats){
        if(this.playerInfoBoxMap.containsKey(userID)){
            PlayerInfoBox userInfoBox = this.playerInfoBoxMap.get(userID);

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

    private void initializePlayers() {
        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/TestCredentials2.txt");
        String fullFileName = sb.toString();
        List<String> players = FileReader.getLoginNames(fullFileName);
        players.forEach((player)-> createPlayerInfoBox(player));
    }
}
