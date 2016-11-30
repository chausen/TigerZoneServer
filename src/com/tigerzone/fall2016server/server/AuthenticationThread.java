package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.server.protocols.LoginProtocol;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/19/2016.
 */
public class AuthenticationThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    List<TournamentPlayer> tournamentPlayers;
    List<String> userNames;

    public AuthenticationThread(Socket socket) {
        this.clientSocket = socket;
        userNames = TournamentServer.getUserNames();
        tournamentPlayers = TournamentServer.getTournamentPlayers();
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        authenticate();
    }

    public void authenticate() {
        try {
            String input, output;

            // For 2 clients
//            LoginProtocol loginProtocol = new LoginProtocol();

            // For multiple clients
            String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(currentDirectory);

            sb.append("/src/com/tigerzone/fall2016server/files/TournamentCredentials.txt");

            //sb.append("/src/com/tigerzone/fall2016server/files/TestCredentials0.txt");
            //sb.append("/src/com/tigerzone/fall2016server/files/TestCredentialsFourPlayers.txt");

            LoginProtocol loginProtocol = new LoginProtocol(sb.toString());

            output = loginProtocol.login(null);

            out.println(output);

            clientSocket.setSoTimeout(15000);

            
            while ((input = in.readLine()) != null) { //so this will not sotp
                output = loginProtocol.login(input);
                out.println(output);
                if (output.equals("NOPE GOOD BYE")) {
                    clientSocket.close();
                    break;
                }
                if (output.startsWith("WELCOME")) {
                    String user = loginProtocol.getUser();
                    if (!multipleLoginAttempts(user)) {
                        userNames.add(user);
                        TournamentPlayer tournamentPlayer = new TournamentPlayer(user, new Connection(clientSocket));
                        tournamentPlayer.setCommunicationTimeout(1100);
                        tournamentPlayers.add(tournamentPlayer);
                    } else {
                        System.out.println("Multiple login attempts received");
                        break;
                    }
                    break;
                }
            }
        } catch (IOException e) {
            try {
                System.out.println("Caught exception in authentication thread; closing client connection");
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e2) {
                System.out.println("Exception when trying to close client connection in Authentication Thread");
            }
        }
    }

    public boolean multipleLoginAttempts(String user) {
        boolean addable = true;
        if (!userNames.isEmpty()) { //check if the list of usernames logged in is empty
            for (String username : userNames) {
                if (username.equals(user)) {
                    addable = false;
                    break;
                }
            }
        } //if the list of usernames was empty, can just add the player
        return !addable;
    }

}
