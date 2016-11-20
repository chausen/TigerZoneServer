package com.tigerzone.fall2016server;

import com.tigerzone.fall2016server.server.Client;
import com.tigerzone.fall2016server.server.Connection;
import com.tigerzone.fall2016server.server.TournamentProtocol;
import com.tigerzone.fall2016server.server.TournamentServer;

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
            e.printStackTrace();
//            TournamentServer server = new TournamentServer(4444, 2);
//
//            while (!server.isTournamentReady()) {
//                try {
//                    //create new Connection
//                    Connection connection = server.createConnection(4444);
//
//                    //is login successful (connection)
//                    if (server.isLoginSuccessful(connection)) {
//                    }
//
//                } catch (IOException e) {
//                    System.out.println("I/O error: " + e);
//                }
//                server.startGame();
//            }
        }
    }
}
