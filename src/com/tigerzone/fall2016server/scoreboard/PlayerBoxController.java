package com.tigerzone.fall2016server.scoreboard;

import com.tigerzone.fall2016server.files.FileReader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clayhausen on 11/27/16.
 */
public class PlayerBoxController {
    private Map<String, PlayerInfoBox> playerInfoBoxMap;

    public PlayerBoxController(){
        playerInfoBoxMap = new HashMap<>();
    }

    /**
     * Method creates a new HBox for Tournament Player added
     * @param userID
     */
    public void generatePlayerInfoBox(String userID){
        PlayerInfoBox playerInfoBox = new PlayerInfoBox(userID);
        playerInfoBoxMap.put(userID, playerInfoBox);
    }

    public void initializePlayers() {
        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/TestCredentials2.txt");
        String fullFileName = sb.toString();
        List<String> players = FileReader.getLoginNames(fullFileName);
    }

    public void initializePlayers(String fileName) {
        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/" + fileName);
        String fullFileName = sb.toString();
        List<String> players = FileReader.getLoginNames(fullFileName);
    }
}
