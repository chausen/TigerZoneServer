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
import java.util.concurrent.CountDownLatch;

public class Scoreboard extends Application {
    TilePane root = new TilePane();
    public static final CountDownLatch latch = new CountDownLatch(1);
    public static Scoreboard scoreboard = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root.setAlignment(Pos.CENTER);
        root.setPrefColumns(6);
        root.setVgap(10);
        root.setHgap(10);
        primaryStage.setTitle("TigerZone | Challenge " + 1);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Scoreboard.class.getResource("Scoreboard.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void addPlayerInfoBox(PlayerInfoBox playerInfoBox){
        this.root.getChildren().add(playerInfoBox.getvBox());
    }

    /**
     *
     */
    public void initializePlayers(HashMap<String,PlayerInfoBox> playerInfoBoxHashMap) {
        String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/TestCredentials0.txt");
        String fullFileName = sb.toString();

        List<String> players = FileReader.getLoginNames(fullFileName);

        players.forEach((player)-> {
            PlayerInfoBox playerInfoBox = new PlayerInfoBox(player);
            playerInfoBoxHashMap.put(player, playerInfoBox);
            addPlayerInfoBox(playerInfoBox);
        });
    }


    public static Scoreboard waitForScoreboard() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return scoreboard;
    }

    public static void setScoreboard(Scoreboard sb) {
        scoreboard = sb;
        latch.countDown();
    }

    public Scoreboard() {
        setScoreboard(this);
    }
}
