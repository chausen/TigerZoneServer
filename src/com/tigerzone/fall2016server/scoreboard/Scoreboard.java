package com.tigerzone.fall2016server.scoreboard;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Scoreboard extends Application implements Runnable {
    TilePane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new TilePane();
        primaryStage.setTitle("TigerZone | Challenge " + 1);
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Scoreboard.class.getResource("Scoreboard.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String args) {
        launch();
    }

    @Override
    public void run() {
        launch();
    }

    public void addPlayerInfoBox(PlayerInfoBox playerInfoBox){
        root.getChildren().add(playerInfoBox);
    }
}
