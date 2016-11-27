package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 11/24/2016.
 */
public class ConnectionExecutor implements Runnable {

    protected int serverPort = 4444;
    protected Thread runningThread = null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(12);

    private List<TournamentPlayer> connectedPlayers;
    private int maxConnections;

    public ConnectionExecutor(int maxConnections) {
        this.maxConnections = maxConnections;
        connectedPlayers = TournamentServer.getTournamentPlayers();
    }

    public ConnectionExecutor(int maxConnections, List<TournamentPlayer> tournamentPlayers) {
        this.maxConnections = maxConnections;
        connectedPlayers = tournamentPlayers;
    }


    public void run() {
        authenticate();
    }

    public void authenticate() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        long startTime = System.currentTimeMillis();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            serverSocket.setSoTimeout(1000);
            Socket clientSocket = null;
            while (!tournamentReady(startTime)) {
                try {
                    clientSocket = serverSocket.accept();
                    this.threadPool.execute(
                            new AuthenticationThread(clientSocket));
                } catch (IOException e) {
                    System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
                }
            }
        } catch (IOException e) {
            System.out.println("Some IOException in ConnectionExecutor");
        }
        //this.threadPool.shutdown();
        //System.out.println("Server Stopped.");
    }


    public boolean tournamentReady() {
        boolean ready = false;
        if (connectedPlayers.size() >= maxConnections) {
            ready = true;
        }
        return ready;
    }

    public boolean tournamentReady(long start) {
        boolean ready = false;
        long timePassed = System.currentTimeMillis() - start;
        if (connectedPlayers.size() >= maxConnections || (timePassed > 80000)) {
            ready = true;
        }
        return ready;
    }


}
