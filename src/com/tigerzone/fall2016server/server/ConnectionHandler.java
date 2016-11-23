package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.TournamentPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by lenovo on 11/20/2016.
 */
public class ConnectionHandler extends Thread {

    private static int port = 4444;
    private List<TournamentPlayer> connectedPlayers;
    private int maxConnections;


    public ConnectionHandler(int maxConnections) {
        this.maxConnections = maxConnections;
        connectedPlayers = TournamentServer.getTournamentPlayers();
    }

    public ConnectionHandler(int maxConnections, List<TournamentPlayer> tournamentPlayers) {
        this.maxConnections = maxConnections;
        connectedPlayers = tournamentPlayers;
    }

    @Override
    public void run() {
        authenticate();
    }


    public void authenticate() {

        long startTime = System.currentTimeMillis();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(1000);
            Connection connection;
            while (!tournamentReady(startTime)) { //might need to spin a thread for authentication itself so can interrupt?
                try {
                    connection = new Connection(serverSocket);
                    connection.accept(); //the loop holds here until a new connection attempt is made
                    connection.setupIO();
                    new AuthenticationThread(connection).start();
                } catch (SocketTimeoutException ste) {
                    System.out.println("Waited 1000 millis but no connection made");
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }



    public boolean tournamentReady() {
        boolean ready = false;
        if (connectedPlayers.size()>=maxConnections) {
            ready = true;
        }
        return ready;
    }

    public boolean tournamentReady(long start) {
        boolean ready = false;
        long timePassed = System.currentTimeMillis() - start;
        System.out.println("This is how much time has passed in tournamnet ready " + timePassed);
        if (connectedPlayers.size() >= 24 || (timePassed > 40000)) {
            ready = true;
        }
        return ready;
    }


}
