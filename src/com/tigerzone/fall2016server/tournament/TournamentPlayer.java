package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016server.server.Connection;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by lenovo on 11/19/2016.
 */
public class TournamentPlayer {
    private String username;
    private PlayerStats stats;
    Connection connection;

    public TournamentPlayer(String username) {
        this.username = username;
    }

    public TournamentPlayer(String username, Connection connection) {
        this.username = username;
        this.connection = connection;
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

    public Connection getConnection(){
        return this.connection;
    }
}
