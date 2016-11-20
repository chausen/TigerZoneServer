package com.tigerzone.fall2016server.server;


import com.tigerzone.fall2016.gamesystem.TileStack;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.tournament.Challenge;
import com.tigerzone.fall2016server.tournament.Game;
import com.tigerzone.fall2016server.tournament.Player;
import com.tigerzone.fall2016server.tournament.TileStackGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TournamentServer {
    BufferedReader serverInput;
    PrintWriter serverOutput;

    BufferedReader in;
    PrintWriter out;
    int numOfPlayers;
    int portNum;

    Challenge challenge;
    List<Player> players;

    public TournamentServer(int portNum, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.portNum = portNum;
        this.players = new ArrayList<>();
    }

    public boolean isTournamentReady(){
        return (this.players.size() == this.numOfPlayers);

    }

    public Connection createConnection(int portNum) throws IOException {
        return new Connection(portNum);
    }


    public boolean isLoginSuccessful(Connection connection) throws IOException {
        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();
        this.serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        this.serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);

        serverOutput.println(outputLine);

        while ((inputLine = serverInput.readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            serverOutput.println(outputLine);
            if(outputLine.startsWith("WELCOME")){
                addPLayerToPlayerToList(connection, tp.getUser());
                return true;
            }

            if (outputLine.equals("NOPE GOODBYE")) {
                System.out.println("Server says goodbye inside server");
                out.close();
                in.close();
                clientSocket.close();
                serverSocket.close();
                return false;
            }
        }
<<<<<<< HEAD

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
=======
        return true;
    }
>>>>>>> 096f853efe54cd3ff481cd7c0901ca463543b276

    public void addPLayerToPlayerToList(Connection connection, String userName){
        Player player = new Player(userName, connection);
        this.players.add(player);
    }

    //This class is for testing purposes only
    public void startGame(){
        TileStackGenerator stackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = stackGenerator.createTilesFromTextFile(123456789);
        Game game = new Game(1,players.get(0).getUsername(), players.get(1).getUsername(), tileStack);
        game.start();
    }
}
