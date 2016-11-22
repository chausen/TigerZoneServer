package com.tigerzone.fall2016server.server;

import java.io.IOException;

/**
 * Created by lenovo on 11/22/2016.
 */
public class TournamentServerMain {

    public static void main(String[] args) throws IOException {

        TournamentServer tournamentServer = new TournamentServer();
        tournamentServer.authenticate();

//        int portNumber = 4444;
//        boolean listening = true;
//
//        long startTime = System.currentTimeMillis(); //fetch starting time
//
//        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
//            Connection connection;
//            boolean running = ((System.currentTimeMillis()-startTime)<20000);
//            while (running) {
//                //while(listening) {
//                connection = new Connection(serverSocket);
//                connection.accept();
//                connection.setupIO();
//                new AuthenticationThread(connection).start();
//                System.out.println("Created a connection with " + connection.getClientSocket());
//                System.out.println("Heres the first tourney player " + tournamentPlayers.get(0));
//                for (TournamentPlayer tp: tournamentPlayers) {
//                    System.out.println("These are the tournament players " + tp.getUsername());
//                }
//
//            }
//            System.out.println("TIME'S UP!!");
//        } catch (IOException e) {
//            System.err.println("Could not listen on port " + portNumber);
//            System.exit(-1);
//        }

    }

}
