package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Client {

    String host;
    int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            Socket clientSocket = new Socket(this.host, this.port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



        } catch(IOException e) {
            System.out.println("Some ioexception");
        }

    }

}
