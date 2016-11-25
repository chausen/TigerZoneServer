package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.server.Client;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by lenovo on 11/18/2016.
 */
public class ClientMain {

    public static void main(String[] args) {
        Client client = new Client("localhost", 4444);
        System.out.println("Number of active threads from the given thread: " + Thread.activeCount());

        try {
            client.login();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
