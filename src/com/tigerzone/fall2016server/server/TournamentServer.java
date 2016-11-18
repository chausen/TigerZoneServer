package com.tigerzone.fall2016server.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer implements Runnable {

    ServerSocket serverSocket;
    BufferedReader in;
    PrintWriter out;


    public TournamentServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Caught some exception");
        }
    }

        public void run() {
            Socket clientSocket;
            try {
                TournamentProtocol tournamentProtocol = new TournamentProtocol();
                while((clientSocket = serverSocket.accept()) != null) {
                    InputStream inputStream = clientSocket.getInputStream();
                    in = new BufferedReader(new InputStreamReader(inputStream));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String inputLine, outputLine;
                    outputLine = tournamentProtocol.login(null);
                    out.println(outputLine);
                    while ((inputLine = in.readLine())!=null) {
                        outputLine = tournamentProtocol.login(inputLine);
                        out.println(outputLine);
                        if (outputLine.equals("NOPE GOODBYE")) {
                            break;
                        }
                    }
                    clientSocket.close();
                }
            } catch(IOException e) {
                System.out.println("Some exception in server run()");
            }
    }



}
