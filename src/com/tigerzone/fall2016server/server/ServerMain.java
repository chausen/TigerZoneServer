package com.tigerzone.fall2016server.server;

import java.io.IOException;

/**
 * Created by lenovo on 11/17/2016.
 */
public class ServerMain {

    public static void main(String[] args) {

//        TourneyServerSandbox server = new TourneyServerSandbox(4444);
//        try {
//            server.login();
//        } catch (Exception e) {
////            e.printStackTrace();
//            System.out.println("GOT AN ERROR IN MAIN");
//        }

        long start = System.currentTimeMillis();
        System.out.println("This is the start time: " + start);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("This is the end time: " + end);
        System.out.println("This is the time difference: " + (end-start));

//        TournamentProtocol tp = new TournamentProtocol();
//        Socket clientSocket;
//        try {
            //ServerSocket serverSocket = new ServerSocket(4444);
//            Connection connection = new Connection(4444);
//            while (true) {
                //clientSocket = serverSocket.accept();
//                connection.accept();
//                connection.setupIO();
//                new AuthenticationThread(connection).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
