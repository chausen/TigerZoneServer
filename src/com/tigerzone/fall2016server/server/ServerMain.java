package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.server.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lenovo on 11/17/2016.
 */
public class ServerMain {

    public static void main(String[] args) {

        TournamentServer server = new TournamentServer(4444);
        try {
            server.login();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("GOT AN ERROR IN MAIN");
        }

    }
}
