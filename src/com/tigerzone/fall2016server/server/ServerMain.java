package com.tigerzone.fall2016server.server;

/**
 * Created by lenovo on 11/17/2016.
 */
public class ServerMain {

    public static void main(String[] args) {

        TourneyServerSandbox server = new TourneyServerSandbox(4444);
        try {
            server.login();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("GOT AN ERROR IN MAIN");
        }


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
