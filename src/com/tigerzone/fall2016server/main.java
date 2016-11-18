package com.tigerzone.fall2016server;

import com.tigerzone.fall2016server.server.Client;
import com.tigerzone.fall2016server.server.TournamentServer;

/**
 * Created by lenovo on 11/17/2016.
 */
public class main {

    public static void main(String[] args) {
        Client client = new Client("localhost", 9990);
        TournamentServer server = new TournamentServer(9990);
        server.run();

    }
}
