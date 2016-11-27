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

        if (args.length != 4 && args.length != 6) {
            System.out.println(args.length);
            System.out.println("Proper usages: ParameterizedClientMain <loginName> <password> <filename1> <filename2>. Exiting...");
            return;
        } else if (args.length == 6) {
            hostname = args[4];
            port = Integer.parseInt(args[5]);
        }

        loginName = args[0];
        password = args[1];
        player1MovesFilename = args[2];
        player2MovesFilename = args[3];

        ParameterizedClient client = new ParameterizedClient(hostname, port, player1MovesFilename, player2MovesFilename);
        try {
            client.login(loginName, password);
            client.waitForGame();
            client.determineMoveSet();
            client.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
