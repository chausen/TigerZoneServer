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
    BufferedReader in;
    PrintWriter out;
    Socket clientSocket;
    BufferedReader stdIn;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            clientSocket = new Socket(this.host, this.port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.out.println("Some ioexception");
        }

    }

    public void play() throws Exception {
        String response;
        String userInput;
        try {
            response = in.readLine();
            if (response.startsWith("TOURNAMENT PASSWORD?")) {
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
            }
        }
        finally {
            clientSocket.close();
        }
    }

}

