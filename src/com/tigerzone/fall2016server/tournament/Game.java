
package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.ports.IOPort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;
import java.io.IOException;
import java.util.LinkedList;


/**
 * Created by lenovo on 11/17/2016.
 */
public class Game {
    private int gameID;
    private Match match;
    TournamentPlayer player1;
    TournamentPlayer player2;
    //private GamePlayerCommunication gamePlayerCommunication;
    private TournamentPlayer activePlayer;
    private TournamentPlayer restingPlayer;

    LinkedList<PlayableTile> tileStack;
    private IOPort ioPort;
    private static long MAX_PLAYER_DECISION_TIME = 1000;

    public Game(int gameID, TournamentPlayer player1, TournamentPlayer player2,
                LinkedList<PlayableTile> tileStack, Match match) {

        this.gameID = gameID;

        this.player1 = player1;
        this.activePlayer = this.player1;

        this.player2 = player2;
        this.restingPlayer = this.player2;

        this.tileStack = tileStack;
        this.match = match;
        //this.gamePlayerCommunication = new GamePlayerCommunication(player1, player2);

        ioPort = new IOPort(this.gameID, player1.getUsername(), player2.getUsername(), tileStack);

    }

    /**
     * Method that puts current game thread to sleep
     * for specified amount of milliseconds
     * @param milliseconds
     */
//    private void putGameThreadToSleep(int milliseconds){
//        try {
//            sleep(milliseconds);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * This method swaps active player between
     * player1 and player2 and vice-versa.
     *
     */
    private void swapActivePlayer(){
        TournamentPlayer placeHolder = this.activePlayer;
        this.activePlayer = this.restingPlayer;
        this.restingPlayer = placeHolder;
    }

    private boolean didActivePlayerTimeOut(TournamentPlayer previousActivePlayer){
        return this.activePlayer == previousActivePlayer;
    }

//    void playGame() {
//        this.ioPort.initialize();
//        while (!this.ioPort.isGameOver()) {
//            //not sure if this logic is correct
//            while (this.ioPort.isCurrentMessageQueueEmpty()) {
//                putGameThreadToSleep(20);
//            }
//            //send active player message from Game System
//            String gameMessage = this.ioPort.getMessageFromCurrentMessageQueue();
//            this.activePlayer.sendMessageToPlayer(gameMessage);
//
//            long startTime = System.nanoTime();
//            long activePlayerDecisionTime = startTime;
//
//            TournamentPlayer previousActivePlayer = this.activePlayer;
//            while (activePlayerDecisionTime - startTime > MAX_PLAYER_DECISION_TIME) {
//                putGameThreadToSleep(200);
//
//                String activePlayerMessage = this.activePlayer.readPlayerMessage();
//                //send active player's move to game
//                if (activePlayerMessage != null) {
//                    this.ioPort.receiveTurn(activePlayerMessage);
//                    //swap resting player to be active player
//                    swapActivePlayer();
//                } else {
//                    activePlayerDecisionTime = System.nanoTime() - startTime;
//                }
//            }
//
//            //decide if active player should forfeit due to (TimeOut)
//            if (didActivePlayerTimeOut(previousActivePlayer)) {
//                //active player should forfeit
//            }
//        }
//        notifyComplete();
//    }

    public void initializeIOport(){
        ioPort.initialize();
    }

    public boolean isOver(){
        return ioPort.isGameOver();
    }

    public String getCurrentTile(){
        return ioPort.getCurrentTile();
    }

    public void receiveTurn(String message){
        Logger.messageReceived(getMatch().getRound().getChallenge().getTournamentID(),getMatch().getRound().getChallenge().getChallengeID(),getMatch().getRound().getRoundID(),getMatch().getMatchID(),gameID,activePlayer.getUsername(),message);
        ioPort.receiveTurn(message);
    }

    public String getResponse(){
        return ioPort.getResponse();
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
}
