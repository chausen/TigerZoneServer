package com.tigerzone.fall2016server.server;


import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.protocols.GameToClientMessageFormatter;
import com.tigerzone.fall2016server.server.protocols.LoginProtocol;
import com.tigerzone.fall2016server.server.protocols.TournamentProtocol;
import com.tigerzone.fall2016server.tournament.*;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class TourneyServerSandbox {

    int numOfPlayers;
    int portNum;

    Challenge challenge;
    List<TournamentPlayer> tournamentPlayers;

    Game game;

    public TourneyServerSandbox(int portNum, int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        this.portNum = portNum;
        this.tournamentPlayers = new ArrayList<>();
    }

    public TourneyServerSandbox(int portNum) {
        this.portNum = portNum;
        this.tournamentPlayers = new ArrayList<>();
    }

    public boolean isTournamentReady(){
        return (this.tournamentPlayers.size() == this.numOfPlayers);

    }

    public void gamePlay() throws IOException
    {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();

        String inputLine, outputLine;
        GameToClientMessageFormatter tp = new GameToClientMessageFormatter();
        //outputLine = tp.game(null);
        //connection.getOut().println(outputLine);



    }

    public void login() throws IOException {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        String inputLine, outputLine;
        LoginProtocol lp = new LoginProtocol();
        outputLine = lp.login(null);
        connection.writeMessageToPlayer(outputLine);

        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = lp.login(inputLine);
            connection.writeMessageToPlayer(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    System.out.println("Player has been welcomed to the system");
                    break;
                }
                if (outputLine.equals("NOPE GOOD BYE")) {
                    connection.writeMessageToPlayer(outputLine);
                    System.out.println("Server says goodbye inside server");
                    connection.close();
                    connection.getClientSocket().close();
                    connection.getServerSocket().close();
                    break;
                }
            }
        }

    public Connection createConnection(int portNum) throws IOException {
        return new Connection(portNum);
    }


    public void isLoginSuccessful() throws IOException {
        Connection connection = new Connection(portNum);
        connection.accept();
        connection.setupIO();

        Socket clientSocket = connection.getClientSocket();
        ServerSocket serverSocket = connection.getServerSocket();


        String inputLine, outputLine;
        TournamentProtocol tp = new TournamentProtocol();

        outputLine = tp.login(null);

        //serverOutput.println(outputLine);
        connection.writeMessageToPlayer(outputLine);

        //while ((inputLine = serverInput.readLine()) != null) {
        while ((inputLine = connection.getIn().readLine()) != null) {
            System.out.println("Entering server with message" + inputLine);
            outputLine = tp.login(inputLine);
            //serverOutput.println(outputLine);
            connection.writeMessageToPlayer(outputLine);
                if (outputLine.startsWith("WELCOME")) {
                    addPLayerToPlayerToList(connection, tp.getUser());
                    startGame();
                }

                if (outputLine.equals("NOPE GOODBYE")) {
                    System.out.println("Server says goodbye inside server");
                    connection.close();
                    clientSocket.close();
                    serverSocket.close();
                }
            }
        connection.close();
        }

    public void addPLayerToPlayerToList(Connection connection, String userName){
        TournamentPlayer tournamentPlayer = new TournamentPlayer(userName, connection);
        this.tournamentPlayers.add(tournamentPlayer);
    }

    //This class is for testing purposes only
    public void startGame(){
        System.out.println("in game method");
        LinkedList<PlayableTile> tileStack = TileStackGenerator.generateTiles(123456789);
        TournamentPlayer player1 = tournamentPlayers.get(0);
        Socket clientSocket = null;
        Socket clientSocket2 = null;
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            clientSocket = serverSocket.accept();
            clientSocket2 = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection1 = new Connection(clientSocket);
        Connection connection2 = new Connection(clientSocket2);
        Game game = new Game(1, player1,  player1, tileStack, null);
        game.start();
//        Connection player1Connection = player1.getConnection();
//
//        Deque<String> writeQueue = game.getPlayer1ReadQueue();
//
//        String player1Message = "";
//        try{
//
//            while ((player1Message = player1Connection.getIn().readLine()) != null) {
//                System.out.println("Reading input" + player1Message);
//
//                writeQueue.push(player1Message);
//            }
//        }catch(IOException e){
//
//        }
    }

}
