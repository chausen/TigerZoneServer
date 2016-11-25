package com.tigerzone.fall2016server.server;

import java.io.IOException;

/**
 * Created by lenovo on 11/22/2016.
 */
public class TournamentServerMain {

    public static void main(String[] args) throws IOException {

        TournamentServer tournamentServer = new TournamentServer();
        tournamentServer.runTournament();
        //tournamentServer.authenticationExecutor();
//        tournamentServer.authentication();
        System.out.println("Number of active threads from the given thread: " + Thread.activeCount());


//        ConnectionExecutor connectionExecutor = new ConnectionExecutor(3);
//        new Thread(connectionExecutor).start();

//        tournamentServer.authenticate();
       // tournamentServer.authentication();

       // System.out.println("While loop finally executed after 30 secs in main");

    }

}
