package com.tigerzone.fall2016server.server.KnockKnock;

/**
 * Created by lenovo on 11/20/2016.
 */

import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 4444;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new KKMultiServerThread(serverSocket.accept()).start();
                System.out.println("Created a connection with someone new");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}