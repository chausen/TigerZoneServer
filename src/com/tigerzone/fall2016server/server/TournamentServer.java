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
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }


        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
