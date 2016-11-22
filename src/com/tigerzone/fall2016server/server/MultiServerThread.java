package com.tigerzone.fall2016server.server;


import com.tigerzone.fall2016server.tournament.Game;

import java.io.IOException;

/**
 * Created by lenovo on 11/19/2016.
 */
public class MultiServerThread extends Thread {

    private Connection connection;


    public MultiServerThread(Connection connection) {
        super("MultiServerThread");
        this.connection=connection;
    }


    public void run() {
        try {
            String input, output;
            TournamentProtocol tp = new TournamentProtocol();
            output = tp.login(null);
            connection.getOut().println(output);
            System.out.println("Connected to " + connection.getClientSocket());

            while ((input = connection.getIn().readLine()) != null) {
                output = tp.login(input);
                connection.getOut().println(output);
                System.out.println("Message from client " + input);
                System.out.println("Server response is " + output);
                if (output.equals("NOPE GOOD BYE")) {
                    System.out.println("Goodbye from inside multiserverthread");
                    break;
                }
            }
            System.out.println("Connection in multiserverthread not reading lines anymore?");
            connection.getClientSocket().close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

}
