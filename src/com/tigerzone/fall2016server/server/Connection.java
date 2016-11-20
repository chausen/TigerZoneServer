package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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

    public void accept() {
       try {
           clientSocket = serverSocket.accept();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void setupIO() {
        try {
            in = new BufferedReader(new InputStreamReader(getClientSocket().getInputStream()));
            out = new PrintWriter(getClientSocket().getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Queue<String> getMessageQueue() {
        return messageQueue;
    }
}
