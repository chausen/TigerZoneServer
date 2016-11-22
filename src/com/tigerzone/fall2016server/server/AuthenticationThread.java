package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.TournamentPlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 11/19/2016.
 */
public class AuthenticationThread extends Thread {

    private Connection connection;
    private TournamentPlayer player;

    public Connection getConnection() {
        return connection;
    }
    public TournamentPlayer getPlayer() {
        return player;
    }

    HashMap<TournamentPlayer, AuthenticationThread> playerThreads;
    List<TournamentPlayer> tournamentPlayers;

    public AuthenticationThread(Connection connection) {
        super("AuthenticationThread");
        this.connection=connection;
    }


    public void run() {
        try {
            String input, output;
            LoginProtocol loginProtocol = new LoginProtocol();
            output = loginProtocol.login(null);
            connection.getOut().println(output);
            System.out.println("Connected to " + connection.getClientSocket());

            while ((input = connection.getIn().readLine()) != null) {
                output = loginProtocol.login(input);
                connection.getOut().println(output);
                System.out.println("Message from client " + input);
                System.out.println("Server response is " + output);
                if (output.equals("NOPE GOOD BYE")) {
                    System.out.println("Goodbye from inside multiserverthread");
                    break;
                }
                if (output.startsWith("WELCOME")) {
                    System.out.println("Trying to create a user " + loginProtocol.getUser() + " from within multiserverthread");
                    TournamentPlayer tournamentPlayer = new TournamentPlayer(loginProtocol.getUser(), connection);
                    tournamentPlayers = TournamentServer.getTournamentPlayers();
                    tournamentPlayers.add(tournamentPlayer);
                    playerThreads = TournamentServer.getPlayerThreads(); //might not need this
                    playerThreads.put(tournamentPlayer, this); //might not need this
                }
            }
            System.out.println("Connection in multiserverthread not reading lines anymore?");
            connection.getClientSocket().close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

}
