package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016server.server.Connection;

/**
 * Created by lenovo on 11/19/2016.
 */
public class Player {

    private String username;
    private PlayerStats stats;
    Connection connection;

    public Player(String username) {
        this.username = username;
    }

    public Player(String username, Connection connection) {
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

}
