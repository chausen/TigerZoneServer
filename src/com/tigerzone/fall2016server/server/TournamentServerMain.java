package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.scoreboard.Scoreboard;

import java.io.IOException;

/**
 * Created by lenovo on 11/22/2016.
 */
public class TournamentServerMain {

    public static void main(String[] args) throws IOException {
        TournamentServer tournamentServer;
        if (args.length == 3) {
            int port = Integer.parseInt(args[0]);
            int seed = Integer.parseInt(args[1]);
            int maxConnections = Integer.parseInt(args[2]);
            tournamentServer = new TournamentServer(port, seed, maxConnections);
        } else if (args.length == 4) {
            int port = Integer.parseInt(args[0]);
            int seed = Integer.parseInt(args[1]);
            int maxConnections = Integer.parseInt(args[2]);
            int tournamentID = Integer.parseInt(args[3]);
            tournamentServer = new TournamentServer(port, seed, maxConnections, tournamentID);
        } else {
            tournamentServer = new TournamentServer();
        }

        tournamentServer.runTournament();

        System.out.println("Number of active threads from the given thread: " + Thread.activeCount());

    }

}

// CODE THAT WAS PREVIOUSLY IN MAIN BUT MOVED HERE TO CLEAN IT UP
//        System.out.println("While loop finally executed after 30 secs in main");
//        ConnectionExecutor connectionExecutor = new ConnectionExecutor(3);
//        new Thread(connectionExecutor).start();

//        tournamentServer.authenticate();
//        tournamentServer.authentication();
//        tournamentServer.authenticationExecutor();
//        tournamentServer.authentication();