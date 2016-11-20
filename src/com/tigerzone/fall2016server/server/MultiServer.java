package com.tigerzone.fall2016server.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by lenovo on 11/19/2016.
 */
public class MultiServer {

    public static void main(String[] args) throws IOException {

        int port = 4444;
        boolean listening = true;
        Connection connection = new Connection(port);
        while (listening) {
            connection.accept();
            connection.setupIO();
            new MultiServerThread(connection).start();
        }
    }
}


