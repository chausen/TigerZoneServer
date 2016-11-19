package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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


    public void login() {
        try {
            String fromServer;
            String userInput;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.equals("NOPE GOODBYE")) {
                    System.out.println("Server says goodbye");
                    break;
                }
                userInput = stdIn.readLine();
                if (userInput != null) {
                    out.println(userInput);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        }
    }


    public void defaultLogin() throws Exception {
        String fromServer;
        while ((fromServer = in.readLine()) != null) {
            if (fromServer.equals("NOPE GOODBYE")) {
                System.out.println("Server says goodbye");
                break;
            }
            if (fromServer.startsWith("TOURNAMENT PASSWORD?")) {
                out.println("TIGERZONE");
                System.out.println("echo: password is TIGERZONE");
            } else if (fromServer.startsWith("USERNAME?")) {
                out.println("PLAYER1");
                System.out.println("echo: username is PLAYER1");
            } else if (fromServer.startsWith("PASSWORD?")) {
                out.println("PASSWORD1");
                System.out.println("echo: password is PASSWORD1");
            } else if (fromServer.startsWith("WELCOME")) {
                System.out.println("ACCEPTED TO THE TOURNEY");
            }
        }
    }

}

