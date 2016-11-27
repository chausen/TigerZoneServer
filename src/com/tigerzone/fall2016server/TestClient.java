package com.tigerzone.fall2016server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A test client to be used with the tournament server. Hardcoded to login and
 * place the moves for the 20 tile game using seed 123456789.
 *
 * Usage: java TestClient <host> <port> <loginName> <password>
 *
 * Created by clayhausen on 11/22/16.
 */
public class TestClient {
    public static void main(String args[]) {
        String host = "localhost"; // default value
        int port = 4444; // default value
        String loginName = "PLAYER1"; // default value
        String password = "PASSWORD1"; // default value
        int move = 0;

        // Used for determining which moveset to use
        String player1Login = "PLAYER1";
        String player2Login = "PLAYER2";

        BufferedReader in;
        PrintWriter out;
        Socket clientSocket;
        String request;
        String response;
        String[] player1Moves = {
                "PLACE JJJJX AT -1 0 0 NONE",
                "PLACE LLLL- AT 1 0 0 NONE",
                "PLACE TJTJ- AT -1 1 90 NONE",
                "PLACE LJLJ- AT 2 0 90 NONE",
                "PLACE TLLLC AT -3 0 270 NONE",
                "PLACE TJJT- AT 0 -1 0 NONE",
                "PLACE LJJJ- AT 3 0 90 NONE",
                "PLACE TLTTP AT 4 -1 90 NONE",
                "PLACE TLTJ- AT -2 -1 90 TIGER 2",
                "PLACE JLTTB AT 3 -2 270 NONE"
        };

        String[] player2Moves = {
                "PLACE JLTT- AT -2 0 270 NONE",
                "PLACE TJJT- AT 0 1 90 NONE",
                "PLACE LLJJ- AT 1 -1 0 NONE",
                "PLACE LLJJ- AT 1 1 270 NONE",
                "PLACE TLLTB AT -2 1 180 NONE",
                "PLACE JLLL- AT 2 -1 0 NONE",
                "PLACE TLJT- AT 3 -1 180 NONE",
                "PLACE TJTJ- AT -1 -1 90 TIGER 4",
                "PLACE LJJJ- AT 4 0 180 TIGER 8",
                "PLACE JLLJ- AT 2 1 180 CROCODILE"
        };

        if (args.length == 1) {
            host = args[0];
        } else if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            loginName = args[2];
        } else if (args.length == 4) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            loginName = args[2];
            password = args[3];
        }

        try {
            clientSocket = new Socket(host,port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            while ((response = in.readLine()) != null) {

                System.out.println("From Server: " + response);

                if (response.equals("THIS IS SPARTA!")) {

                    out.println("JOIN TIGERZONE");

                } else if (response.equals("HELLO!")) {

                    out.println("I AM " + loginName);

                } else if (response.startsWith("MAKE YOUR MOVE", 0)) {

                    if (loginName.equals(player1Login)) {
                        // write player1 moves to socket
                        out.println(player1Moves[move++]);
                    }
                    if (loginName.equals(player2Login)) {
                        // write player2 moves to socket
                        out.println(player2Moves[move++]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Some ioexception");
        }

    }

}

