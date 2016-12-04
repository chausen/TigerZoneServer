package com.tigerzone.fall2016server.tournament.tournamentplayer;

import com.tigerzone.fall2016server.server.Connection;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
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
        this.stats = new PlayerStats();
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

    public String readPlayerMessage() throws IOException {
        if (this.timedOutLastMove()) {
            try {
                // If the socket timed out last move, read then throw away the message that timed out
                // and mark the connection as not having timed out
                String lastResponse = this.connection.receiveMessageFromPlayer();
                System.out.println("This is the last move from player " + this.getUsername() + " after timeout: " + lastResponse);
                this.connection.resetTimedOut();
            } catch (IOException e) {
                System.out.println("Couldn't read the player's last response after timeout within TournamentPlayer readplayermessage");
            }
        }
        return this.connection.receiveMessageFromPlayer();
    }

    public void timedOut() {
        this.connection.timedOut();
    }

    // Split up into a private and a public method just so the name makes more sense when called publicly;
    // within this.readPlayerMessage() it makes sense to refer to the last move being timed out; but it does not
    // when used externally
    public boolean isTimedOut() {
        return this.connection.isTimedOut();
    }

    private boolean timedOutLastMove() {
        return this.connection.isTimedOut();
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

    public void closeConnection() throws IOException {
        connection.close();
    }

}
