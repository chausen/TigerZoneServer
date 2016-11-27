package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by matthewdiaz on 11/25/16.
 */
public class GamePlayerCommunication {
    Deque<String> player1ReadQueue;
    Deque<String> player1WriteQueue;
    Deque<String> player2ReadQueue;
    Deque<String> player2WriteQueue;


    TournamentPlayer player1;
    TournamentPlayer player2;

    public GamePlayerCommunication(TournamentPlayer player1, TournamentPlayer player2){
        this.player1 = player1;
        this.player2 = player2;
        this.player1ReadQueue = new ArrayDeque<>();
        this.player1WriteQueue = new ArrayDeque<>();
        this.player2ReadQueue = new ArrayDeque<>();
        this.player2WriteQueue = new ArrayDeque<>();

        player1.setUpGameConnection(this.player1WriteQueue, this.player1ReadQueue);
        player2.setUpGameConnection(this.player2WriteQueue, this.player2ReadQueue);
    }

    public void sendMessageToPlayer1(String gameMessage){
        this.player1.sendMessageToPlayer(gameMessage);
    }

    public void sendGameMessageToPlayer2(String gameMessage){
        this.player2.sendMessageToPlayer(gameMessage);
    }

    public String receivePlayer1Message(){
        return this.player1.readPlayerMessage();
    }

    public String receivePlayer2Message(){
        return this.player2.readPlayerMessage();
    }
}
