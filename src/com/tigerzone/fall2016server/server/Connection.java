package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lenovo on 11/19/2016.
 */
public class Connection {

    Socket clientSocket;
    ServerSocket serverSocket;
    BufferedReader in;
    PrintWriter out;
    Queue<String> messageQueue;
    int port;
    boolean timedOut;


    public Connection(int port) throws IOException {
        try {
            this.serverSocket = new ServerSocket(port);
            this.messageQueue = new LinkedList<>();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception in connection");
        }
    }

    public Connection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.messageQueue = new LinkedList<>();
    }

    public Connection(ServerSocket serverSocket, Socket clientSocket) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.messageQueue = new LinkedList<>();
    }


    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.messageQueue = new LinkedList<>();
        setupIO();
    }

    public void accept() throws IOException {
        clientSocket = serverSocket.accept();
    }


    public void setupIO() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCommunicationTimeout(int millis) throws SocketException {
        this.clientSocket.setSoTimeout(millis);
    }

    public int getPort() {
        return port;
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    //would like to remove this method
    public BufferedReader getIn() {
        return in;
    }

    public void writeMessageToPlayer(String message){
        this.out.println(message);
    }

    public String receiveMessageFromPlayer() throws IOException{
        try {
            Long startTime = System.nanoTime();
            String input = this.in.readLine();
            Long endTime = System.nanoTime();
            System.out.println(endTime - startTime);
//            System.out.println("===== Delay: " + (endTime - startTime) + "ns =====");

            return input;
//            return this.in.readLine();
        }
        catch (SocketException e){
            return null;
        }
    }

    public void playerOutput(String message) {
        this.out.println(message);
    }

    public void close() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    /**
     * Query whether or not this connection has timed out
     * @return boolean that is true if this connection timed out
     */
    public boolean isTimedOut() {
        return timedOut;
    }

    /**
     * Indicate that this connection timed out for future queries
     */
    public void timedOut() {
        this.timedOut = true;
    }

    /**
     * Indicate that this connection has not timed out for future queries
     */
    public void resetTimedOut() {
        this.timedOut = false;
    }
}
