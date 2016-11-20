package com.tigerzone.fall2016server.server;

import java.io.IOException;

/**
 * Created by lenovo on 11/19/2016.
 */
public class MultiServerThread extends Thread {

    private Connection connection;

    public MultiServerThread(Connection connection) {
        this.connection=connection;
    }


    public MultiServerThread() {
    }

    public void run() {
        try {
//            Connection connection = new Connection(4444);
            String input, output;
            TournamentProtocol tp = new TournamentProtocol();
            output = tp.login(null);
            connection.getOut().println(output);

            while ((input = connection.getIn().readLine()) != null) {
                output = tp.login(input);
                connection.getOut().println(output);
                System.out.println("Connected to " + connection.getClientSocket());
                System.out.println("Message from client " + input);
                if (output.equals("NOPE GOOD BYE"))
                    break;
            }
            connection.getClientSocket().close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

}
