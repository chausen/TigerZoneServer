package com.tigerzone.fall2016server.client;

/**
 * Created by lenovo on 11/25/2016.
 */
public class ClientMain2 {

    public static void main(String[] args) {
        Client client = new Client("localhost", 4444);
        try {
            client.defaultLogin2();
            client.waitForGame();
            //client.playGame2();
            client.livePlay();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
