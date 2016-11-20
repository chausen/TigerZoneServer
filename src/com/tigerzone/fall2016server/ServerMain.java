package com.tigerzone.fall2016server;

import com.tigerzone.fall2016server.server.Client;
import com.tigerzone.fall2016server.server.TournamentProtocol;
import com.tigerzone.fall2016server.server.TournamentServer;

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
        }
    }
}
