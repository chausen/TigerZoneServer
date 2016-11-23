package com.tigerzone.fall2016server.server;

import java.io.IOException;

/**
 * Created by lenovo on 11/22/2016.
 */
public class TournamentServerMain {

    public static void main(String[] args) throws IOException {

        TournamentServer tournamentServer = new TournamentServer();

//        tournamentServer.authenticate();
        tournamentServer.authentication();

        System.out.println("While loop finally executed after 30 secs in main");

    }

}
