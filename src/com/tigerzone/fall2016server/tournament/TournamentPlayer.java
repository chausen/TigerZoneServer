package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016server.server.Connection;

import java.io.IOException;
import java.net.SocketException;
import java.util.Deque;

/**
 * Created by lenovo on 11/19/2016.
 */
public class TournamentPlayer {
    private String username;
    private PlayerStats stats;
    private Connection connection;
    private PlayerGameCommunication tournamentPlayerGameConnection;

    public TournamentPlayer(String username) {
        this.username = username;
    }

    public TournamentPlayer(String username, Connection connection) {
        this.username = username;
        this.connection = connection;
        this.tournamentPlayerGameConnection = new PlayerGameCommunication(this.connection);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public void setUpGameConnection (Deque<String> playerReadQueue, Deque<String> playerWriteQueue){
        this.tournamentPlayerGameConnection.setPlayerReadQueue(playerReadQueue);
        this.tournamentPlayerGameConnection.setPlayerWriteQueue(playerWriteQueue);
    }

    //public void closeConnection()

    public void sendMessageToPlayer(String message){
        this.connection.writeMessageToPlayer(message);
    }

    public String readPlayerMessage(){
        return this.connection.receiveMessageFromPlayer();
    }

    public String playerInput() throws IOException {
        String input = connection.playerInput();
        return input;
    }

    public void playerOutput(String message) {
        connection.playerOutput(message);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setCommunicationTimeout(int millis) throws SocketException {
        connection.setCommunicationTimeout(millis);
    }

}
