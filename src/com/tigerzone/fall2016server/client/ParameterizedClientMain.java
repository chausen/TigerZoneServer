package com.tigerzone.fall2016server.client;

/**
 * Takes 3 arguments: loginName, file name for player1 moves, file name for player2 moves
 * Takes 2 option arguments: <hostname> <port>
 *
 */
public class ParameterizedClientMain {

    public static void main(String[] args) {
        String loginName = "";
        String password = "";
        String player1MovesFilename = "";
        String player2MovesFilename = "";
        String hostname = "localhost"; // default
        int port = 4444; // default
        boolean useTimeOutClient = false;

        if (args.length != 4 && args.length != 6 && args.length != 7) {
            System.out.println(args.length);
            System.out.println("Proper usages: ParameterizedClientMain <loginName> <password> <filename1> <filename2>. Exiting...");
            return;
        } else if (args.length == 6) {
            hostname = args[4];
            port = Integer.parseInt(args[5]);
        } else if (args.length == 7) {
            // use the ParameterizedTimeOutClient
            if (args[6].equals("true") || args[6].equals("t"))
                useTimeOutClient = true;
        }

        loginName = args[0];
        password = args[1];
        player1MovesFilename = args[2];
        player2MovesFilename = args[3];

        ParameterizedClient client;

        if (useTimeOutClient) {
            client = new ParameterizedTimeOutClient(hostname, port, player1MovesFilename, player2MovesFilename);
        } else {
            client = new ParameterizedClient(hostname, port, player1MovesFilename, player2MovesFilename);
        }
        try {
            client.login(loginName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
