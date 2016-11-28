package com.tigerzone.fall2016server.scoreboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.HashMap;
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
}
