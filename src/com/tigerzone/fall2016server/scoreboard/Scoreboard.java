package com.tigerzone.fall2016server.scoreboard;

import com.tigerzone.fall2016server.files.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard extends Application implements Runnable {
    Parent root;
    private PlayerBoxController playerBoxController;
    private HashMap<String,PlayerInfoBox> playerInfoBoxHashMap;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        this.root = fxmlLoader.load(getClass().getResource("Scoreboard.fxml").openStream());
        this.playerBoxController = (PlayerBoxController) fxmlLoader.getController();
        primaryStage.setTitle("TigerZone | Challenge " + 1);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Scoreboard.class.getResource("Scoreboard.css").toExternalForm());
        primaryStage.show();
    }

    @Override
    public void run() {
        launch();
    }

//    private void addPlayerInfoBox(PlayerInfoBox playerInfoBox){
//        if(this.root == null){
//            this.root = new TilePane();
//        }
//        this.root.getChildren().add(playerInfoBox.getvBox());
//    }

    /**
     *
     */
//    private void initializePlayers() {
//        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
//        StringBuilder sb = new StringBuilder();
//        sb.append(currentDirectory);
//        sb.append("/src/com/tigerzone/fall2016server/files/TestCredentials2.txt");
//        String fullFileName = sb.toString();
//        List<String> players = FileReader.getLoginNames(fullFileName);
//        this.playerInfoBoxHashMap = new HashMap<>();
//        players.forEach((player)-> {
//            System.out.println(player);
//            PlayerInfoBox playerInfoBox = new PlayerInfoBox(player);
//            this.playerInfoBoxHashMap.put(player, playerInfoBox);
//            addPlayerInfoBox(playerInfoBox);
//        });
//    }

    public PlayerBoxController getPlayerBoxController() {
        return this.playerBoxController;
    }

    public void setPlayer(String playerId) {

    }

    public HashMap<String, PlayerInfoBox> getPlayerInfoBoxHashMap() {
        return this.playerInfoBoxHashMap;
    }
}
