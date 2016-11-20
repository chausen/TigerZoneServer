package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer {

    private int port;

    Challenge challenge;
    Set<Player> players;
    Set<Connection> connections;

    public TournamentServer(int port) {
        this.port = port;
    }

    public TournamentServer() {
    }


    public void login() throws IOException {
        Connection connection = new Connection(port);
        connection.accept();
        connection.setupIO();

        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);
        connection.getOut().println(outputLine);

        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            connection.getOut().println(outputLine);
            if (outputLine.equals("NOPE GOOD BYE")) {
                System.out.println("Server says goodbye inside server");
                break;
            }
        }
        connection.getOut().close();
        connection.getIn().close();
        connection.getClientSocket().close();
        connection.getServerSocket().close();
    }


}
