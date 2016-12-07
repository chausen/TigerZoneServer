
package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.ports.IOPort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Created by lenovo on 11/17/2016.
 */
public class Game {
    private int gameID;
    private Match match;
    TournamentPlayer player1;
    TournamentPlayer player2;
    private boolean isOver = false;

    LinkedList<PlayableTile> tileStack;
    private IOPort ioPort;
    Queue<String> forfeitMessageQueue;


    public Game(int gameID, TournamentPlayer player1, TournamentPlayer player2,
                LinkedList<PlayableTile> tileStack, Match match) {

        this.gameID = gameID;
        this.player1 = player1;
        this.player2 = player2;
        this.tileStack = tileStack;
        this.match = match;

        ioPort = new IOPort(this.gameID, player1.getUsername(), player2.getUsername(), tileStack);
        forfeitMessageQueue = new LinkedList<>();
    }

    public void initializeIOport(){
        ioPort.initialize();
    }

    public boolean isOver(){
        return ioPort.isGameOver() == true ? true : isOver;
    }

    public void endGame(){
        isOver = true;
    }

    public String getCurrentTile(){
        return ioPort.getCurrentTile();
    }

    public void receiveTurn(String message){
        ioPort.receiveTurn(message);
        if (ioPort.getInAdapter().getCurrentPlayer().equals(ioPort.getInAdapter().getPlayer(player1.getUsername()))) {
            Logger.messageReceived(getMatch().getRound().getChallenge().getTournamentID(), getMatch().getRound().getChallenge().getChallengeID(), getMatch().getRound().getRoundID(), getMatch().getMatchID(), gameID, player2.getUsername(), message);
        }
        else Logger.messageReceived(getMatch().getRound().getChallenge().getTournamentID(),getMatch().getRound().getChallenge().getChallengeID(),getMatch().getRound().getRoundID(),getMatch().getMatchID(),gameID,player1.getUsername(),message);
    }

    public void receiveScoreGuess(String playerID, String message) {
        ioPort.receiveScoreGuess(playerID, message);
    }

    public String getResponse(){
        String s = ioPort.getResponse();
        //Logger.messageSent(getMatch().getRound().getChallenge().getTournamentID(),getMatch().getRound().getChallenge().getChallengeID(),getMatch().getRound().getRoundID(),getMatch().getMatchID(),gameID,player1.getUsername(),ioPort.getResponse());
        //Logger.playerStatus(this,ioPort.getInAdapter().getCurrentPlayer(),ioPort.getInAdapter().getCurrentPlayer().getGoodSupply(), ioPort.getInAdapter().getCurrentPlayer().getBadSupply());//TODO: Check to make sure that this is the correct player to call.

        if (ioPort.getInAdapter().getCurrentPlayer().equals(ioPort.getInAdapter().getPlayer(player1.getUsername()))) {
            Logger.messageSent(getMatch().getRound().getChallenge().getTournamentID(),getMatch().getRound().getChallenge().getChallengeID(),getMatch().getRound().getRoundID(),getMatch().getMatchID(),gameID,player2.getUsername(),ioPort.getResponse());
            Logger.playerStatus(this,ioPort.getInAdapter().getPlayer(player2.getUsername()));//TODO: Check to make sure that this is the correct player to call.
        } else {
            Logger.messageSent(getMatch().getRound().getChallenge().getTournamentID(),getMatch().getRound().getChallenge().getChallengeID(),getMatch().getRound().getRoundID(),getMatch().getMatchID(),gameID,player1.getUsername(),ioPort.getResponse());
            Logger.playerStatus(this,ioPort.getInAdapter().getPlayer(player1.getUsername()));//TODO: Check to make sure that this is the correct player to call.
        }

        return s;
    }

    public Match getMatch() {
        return match;
    }

    public int getGameID() {
        return gameID;
    }

    public int getPlayerScore(Player player){
        return ioPort.getInAdapter().getPlayerScore(player);
    }

    public TournamentPlayer getPlayer1() {
        return player1;
    }

    public TournamentPlayer getPlayer2() {
        return player2;
    }

    public int getPlayer1FinalScore(){
        return ioPort.getFinalScore(player1.getUsername());
    }

    public int getPlayer2FinalScore(){
        return ioPort.getFinalScore(player2.getUsername());
    }

    public Queue<String> getForfeitMessages() {
        return ioPort.getForfeitMessages();
    }

    public void addForfeitMessage(String message) {
        ioPort.addForfeitMessage(message);
    }

}
