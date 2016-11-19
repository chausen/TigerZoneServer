package com.tigerzone.fall2016server;

import com.tigerzone.fall2016server.server.Client;
import com.tigerzone.fall2016server.server.TournamentServer;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Main {

    public static void main(String[] args) {
        TournamentServer server = new TournamentServer(9990);
        server.run();

    }
}
