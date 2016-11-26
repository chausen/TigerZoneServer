package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016server.server.Connection;

import java.util.Deque;

/**
 * Created by matthewdiaz on 11/25/16.
 */
public class PlayerGameCommunication {
    private Deque<String> playerWriteQueue;
    private Deque<String> playerReadQueue;
    private Connection connection;

    public PlayerGameCommunication(Connection connection){
        this.connection = connection;
    }

    public void setPlayerWriteQueue(Deque<String> gameReadQueue){
        this.playerWriteQueue = gameReadQueue;
    }

    public void setPlayerReadQueue(Deque<String> gameWriteQueue){
        this.playerReadQueue = gameWriteQueue;
    }

    public void sendGameMessageToPlayer(String gameMessage){}

    public String sendPlayerMessageToGame(){
        return "";
    }
}
