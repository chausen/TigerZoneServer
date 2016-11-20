package com.tigerzone.fall2016server.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer {

    ServerSocket serverSocket;
    BufferedReader in;
    PrintWriter out;


    public TournamentServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void login() throws IOException {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            out.println(outputLine);
            if (outputLine.equals("NOPE GOODBYE")) {
                System.out.println("Server says goodbye inside server");
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }

}
