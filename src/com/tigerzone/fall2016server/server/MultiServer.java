package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.server.KnockKnock.KKMultiServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lenovo on 11/19/2016.
 */
public class MultiServer {

    public static void main(String[] args) throws IOException {

        int portNumber = 4444;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            Connection connection;
            while (listening) {
                connection = new Connection(serverSocket);
                connection.accept();
                connection.setupIO();
                new MultiServerThread(connection).start();
                System.out.println("Created a connection with " + connection.getClientSocket());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }



}


