package com.tigerzone.fall2016server.server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.tigerzone.fall2016server.tournament.Player;

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


    public Connection(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            this.messageQueue = new LinkedList<>();
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
