package com.tigerzone.fall2016server.client;

/**
 * Created by lenovo on 11/18/2016.
 */
public class ClientMain {

    public static void main(String[] args) {
        Client client = new Client("localhost", 4444);
        try {
            //client.login();
            client.defaultLogin();
            client.waitForGame();
            client.playGame();
            //client.livePlay();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
