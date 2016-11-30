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
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            userNames = TournamentServer.getUserNames();
            tournamentPlayers = TournamentServer.getTournamentPlayers();
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

            LoginProtocol loginProtocol = new LoginProtocol(sb.toString());

            output = loginProtocol.login(null);

            out.println(output);
            clientSocket.setSoTimeout(180000);

            while ((input = in.readLine()) != null) { //so this will not sotp
                output = loginProtocol.login(input);
                out.println(output);
                if (output.equals("NOPE GOOD BYE")) {
                    clientSocket.close();
                    break;
                }
                if (output.startsWith("WELCOME")) {
                    String user = loginProtocol.getUser();
                    if (!multipleLogins(user)) {
                        userNames.add(user);
                        TournamentPlayer tournamentPlayer = new TournamentPlayer(user, new Connection(clientSocket));
                        tournamentPlayer.setCommunicationTimeout(1100);
                        //tournamentPlayers = TournamentServer.getTournamentPlayers();
                        tournamentPlayers.add(tournamentPlayer);
                    } else {
                        in.close();
                        out.close();
                        clientSocket.close();
                        System.out.println("Multiple login attempts received; client socket closed");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            try {
                System.out.println("Caught exception in authentication thread");
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e2) {
                e2.printStackTrace();

            }
        }
    }

    public boolean multipleLogins(String user) {
        boolean addable = true;
        if (!userNames.isEmpty()) {
            for (String username : userNames) {
                if (username.equals(user)) {
                    addable = false;
                    break;
                }
            }
        }
        return addable;
    }

}
