package com.tigerzone.fall2016server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Client {

    String host;
    int port;
    BufferedReader in;
    PrintWriter out;
    Socket clientSocket;
    BufferedReader stdIn;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            clientSocket = new Socket(this.host, this.port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.out.println("Some ioexception");
        }

    }


    public static void main(String[] args) throws Exception {
        String response;
        String userInput;
        BufferedReader in;
        PrintWriter out;
        Socket clientSocket;
        BufferedReader stdIn;
        String host = "localhost";

        clientSocket = new Socket("localhost", 4444);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;

        while ((fromServer = in.readLine()) != null) {
            if(fromServer.equals("NOPE GOODBYE")) {
                System.out.println("Server says goodbye");
                break;
            }

//            try {
                //response = in.readLine();
                if (fromServer.startsWith("TOURNAMENT PASSWORD?")) {
                    //while ((userInput = stdIn.readLine()) != null) {
                    //out.println(userInput);
                    out.println("TIGERZONE");
                    System.out.println("echo: password is TIGERZONE");
                    //}
                } else if (fromServer.startsWith("USERNAME?")) {
                    //while ((userInput = stdIn.readLine()) != null) {
                    //out.println(userInput);
                    out.println("PLAYER1");
                    System.out.println("echo: username is PLAYER1");
                    //}
                } else if (fromServer.startsWith("PASSWORD?")) {
                    //while ((userInput = stdIn.readLine()) != null) {
                    //out.println(userInput);
                    out.println("PASSWORD1");
                    System.out.println("echo: password is PASSWORD1");
                    //}
                } else if (fromServer.startsWith("WELCOME")) {
                    System.out.println("ACCEPTED TO THE TOURNEY");
                }
//            } catch (UnknownHostException e) {
//                System.err.println("Don't know about host " + host);
//                System.exit(1);
//            } catch (IOException e) {
//                System.err.println("Couldn't get I/O for the connection to " + host);
//                System.exit(1);
//            } finally {
//                clientSocket.close();
//            }
            }
        }



    public void play() throws Exception {
        String response;
        String userInput;
        try {
            response = in.readLine();
            if (response.startsWith("TOURNAMENT PASSWORD?")) {
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
            } else if (response.startsWith("USERNAME?")) {
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
            } else if (response.startsWith("PASSWORD?")) {
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            System.exit(1);
        } finally {
            clientSocket.close();
        }
    }

}

