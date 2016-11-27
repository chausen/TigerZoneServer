package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.server.protocols.LoginProtocol;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/19/2016.
 */
public class AuthenticationThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    HashMap<TournamentPlayer, AuthenticationThread> playerThreads;
    List<TournamentPlayer> tournamentPlayers;

    public AuthenticationThread(Socket socket) {
        this.clientSocket = socket;
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
            LoginProtocol loginProtocol = new LoginProtocol();
            output = loginProtocol.login(null);

            out.println(output);
            clientSocket.setSoTimeout(20000);

            while ((input = in.readLine()) != null) { //so this will not sotp
                output = loginProtocol.login(input);
                out.println(output);
                if (output.equals("NOPE GOOD BYE")) {
                    clientSocket.close();
                    break;
                }
                if (output.startsWith("WELCOME")) {
                    TournamentPlayer tournamentPlayer = new TournamentPlayer(loginProtocol.getUser(), new Connection(clientSocket));
                    tournamentPlayers = TournamentServer.getTournamentPlayers();
                    tournamentPlayers.add(tournamentPlayer);
                    playerThreads = TournamentServer.getPlayerThreads(); //might not need this
                    playerThreads.put(tournamentPlayer, this); //might not need this
                    break;
                }
            }
        } catch (IOException e) {
            try {
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

}
