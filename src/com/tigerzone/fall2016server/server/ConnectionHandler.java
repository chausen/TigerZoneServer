package com.tigerzone.fall2016server.server;

/**
 * Created by lenovo on 11/20/2016.
 */
public class ConnectionHandler implements Runnable {

    Connection connection;

    public ConnectionHandler(Connection connection) {
        this.connection=connection;
    }

    @Override
    public void run() {

    }
}
