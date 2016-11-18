package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer implements Runnable {

    ServerSocket serverSocket;
    InputStream inputStream;
    BufferedReader bufferedReader;


    public TournamentServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Caught some exception");
        }
    }

//    public void connect(int portNumber) {
//
//        boolean listening = true;
//        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
//            while (listening) {
//                new KKMultiServerThread(serverSocket.accept()).start();
//            }
//        } catch (IOException e) {
//            System.err.println("Could not listen on port " + portNumber);
//            System.exit(-1);
//        }
//    }

        public void run() {
            Socket clientSocket;
            try {
                while((clientSocket = serverSocket.accept()) != null) {
                    inputStream = clientSocket.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String inputLine = bufferedReader.readLine();
                    if(inputLine!=null) {
                        //do something with the line (like: gui.write(inputLine);)
                    }
                }
            } catch(IOException e) {
                //do something like log the error
            }


    }



}
