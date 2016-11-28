package com.tigerzone.fall2016server.scoreboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scoreboard extends Application {

    private int challengeId;

    public Scoreboard(int challengeId) {
        this.challengeId = challengeId;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
        primaryStage.setTitle("TigerZone | Challenge " + challengeId);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Scoreboard.class.getResource("Scoreboard.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
