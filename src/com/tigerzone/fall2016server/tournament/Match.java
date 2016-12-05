package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Connection;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.server.TournamentServer;
import com.tigerzone.fall2016server.server.protocols.GameToClientMessageFormatter;
import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import com.tigerzone.fall2016server.tournament.tournamentplayer.TournamentPlayer;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 * Created by lenovo on 11/17/2016.
 */
public class Match extends Thread {
    private TournamentPlayer player1;
    private TournamentPlayer player2;
    private TournamentPlayer game1player;
    private TournamentPlayer game2player;
    private LinkedList<PlayableTile> tileStack;
    private Round round;
    private int matchID;
    private Game game1;
    private Game game2;
    private final int setUpTime = 10;
    private HashMap<Game, String> forfeitGameMap = new HashMap<>(); //This is used to keep track of which player forfeited for each game

    public Match(TournamentPlayer player1, TournamentPlayer player2, LinkedList<PlayableTile> tileStack) {
        this.tileStack = tileStack;
        this.player1 = player1;
        this.player2 = player2;
        game1 = new Game(1, player1, player2, tileStack, this);
        game2 = new Game(2, player2, player1, tileStack, this);
        this.game1player = player1;
        this.game2player = player2;
    }

    private void swapPlayers() {
        TournamentPlayer placeHolder = this.game1player;
        this.game1player = this.game2player;
        this.game2player = placeHolder;
    }

    public void run() {
        startMatch();
        playMatch();
    }

    private void startMatch() {
        sendMessageToPlayers();
        try {
            Thread.sleep(setUpTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the response from a GamePlayer.
     * If the a tournamentPlayer times out the returned response is a timed out message
     * plus the current tournamentPlayer timedOut attribute is set to true.
     *
     * NOTE: A GamePlayer contains a TournamentPlayer & a timeOut boolean for that TournamentPlayer
     * @param game
     * @param moveNumber
     * @param gamePlayer
     */
    private String acquireGamePlayerResponse(Game game, int moveNumber, TournamentPlayer gamePlayer) {
        String gamePlayerResponse = null;
        if (!game.isOver()) {
            String gamePlayerPrompt = GameToClientMessageFormatter.generateMessageToActivePlayer(game.getGameID(), 1, moveNumber, game.getCurrentTile());
            gamePlayer.sendMessageToPlayer(gamePlayerPrompt);
            try {
                gamePlayerResponse = gamePlayer.readPlayerMessage();
            } catch (SocketTimeoutException e) {
                gamePlayerResponse = GameToClientMessageFormatter.generateForfeitMessageToBothPlayers(game.getGameID(),
                        moveNumber, gamePlayer.getUsername(), "FORFEITED: TIMEOUT");
                gamePlayer.timedOut();
                System.out.println("FORFEIT: Timeout in game " + game.getGameID() + ": " + gamePlayer.getUsername());
                forfeitGameMap.put(game, gamePlayer.getUsername());
            } catch (IOException e) {
                System.out.println("Caught IOException in match besides timeout (Player 2)");
                System.out.println("This is their input " + gamePlayerResponse);
                e.printStackTrace();
            }
        }
        return gamePlayerResponse;
    }

    /**
     * This method first checks if the GamePlayer has timed out for the current Game, if it has it sends the response
     * to the player and ends the current Game.
     *
     * If the GamePlayer has not timed out this method receives the message from the game and sends the
     * game's message to the GamePlayer.
     *
     * verifies a GamePlayer's response
     * @param game
     * @param gamePlayer
     */
    private void verifyingGamePlayerResponse(Game game, TournamentPlayer gamePlayer, String gamePlayerResponse) {
        if (!game.isOver()) {
            if (gamePlayer.isTimedOut()) {
                sendGameMessage(gamePlayerResponse);
                game.endGame();
            } else {
                game.receiveTurn(gamePlayerResponse);
                String gameResponse = game.getResponse();
                if (gameResponse.contains("FORFEITED")) {
                    System.out.println("FORFEIT: Invalid move in game " + game.getGameID() + ": " +
                            gamePlayer.getUsername() + " player response " + gamePlayerResponse);
                    forfeitGameMap.put(game, gamePlayer.getUsername());
                }
                sendGameMessage(gameResponse);
            }
        }
    }

    private void playMatch() {
        forfeitGameMap = new HashMap<>();
        int moveNumber = 1;
        game1.initializeIOport();
        game2.initializeIOport();

        while ((!game1.isOver() || !game2.isOver()) && moveNumber < tileStack.size() + 1) {
            String gamePlayer1Response = acquireGamePlayerResponse(game1, moveNumber, game1player);
            String gamePlayer2Response = acquireGamePlayerResponse(game2, moveNumber, game2player);

            verifyingGamePlayerResponse(game1, game1player, gamePlayer1Response);
            verifyingGamePlayerResponse(game2, game2player, gamePlayer2Response);

            //swap who is the active player in each game
            swapPlayers();
            //Increment move count
            moveNumber++;
        }
        notifyEndGameToPlayers();
        round.notifyComplete();
    }


    private String tileToSTring(LinkedList<PlayableTile> tileStack) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Iterator<PlayableTile> iter = tileStack.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(" ");
            stringBuilder.append(iter.next().getTileString());
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    private void sendStartMessage(TournamentPlayer player, String opponentUserName) {
        player.sendMessageToPlayer("YOUR OPPONENT IS PLAYER " + opponentUserName);
        player.sendMessageToPlayer("STARTING TILE IS TLTJ- AT 0 0 0");
        player.sendMessageToPlayer("THE REMAINING 76 TILES ARE " + tileToSTring(tileStack));
        player.sendMessageToPlayer("MATCH BEGINS IN " + setUpTime + " SECONDS");


        System.out.println("YOUR OPPONENT IS PLAYER " + opponentUserName);
        System.out.println("STARTING TILE IS TLTJ- AT 0 0 0");
        System.out.println("THE REMAINING 76 TILES ARE " + tileToSTring(tileStack));
        System.out.println("MATCH BEGINS IN " + setUpTime + " SECONDS");
    }

    private void sendMessageToPlayers() {
        sendStartMessage(player1, player2.getUsername());
        sendStartMessage(player2, player1.getUsername());
    }

    private void notifyEndGameToPlayers() {
        if (forfeitGameMap.get(game1) == null) {
            sendEndMessage(game1);
        } else {
            sendForfeitMessage(game1);
        }
        if (forfeitGameMap.get(game2) == null) {
            sendEndMessage(game2);
        } else {
            sendForfeitMessage(game2);
        }
    }

    private void sendEndMessage(Game game) {
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();

        sendGameMessage(GameToClientMessageFormatter.generateGameOverMessage(game.getGameID(),
                p1.getUsername(), p2.getUsername(), "" + game.getPlayer1FinalScore(),"" + game.getPlayer2FinalScore()));
        updatePlayerStatistics(game, p1, p2);
    }

    private void sendForfeitMessage(Game game) {
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();
        String player1score = (forfeitGameMap.get(game) != p1.getUsername() ? "WIN" : "FORFEITED");
        String player2score = (forfeitGameMap.get(game) != p2.getUsername() ? "WIN" : "FORFEITED");

        sendGameMessage(GameToClientMessageFormatter.generateGameOverMessage(game.getGameID(),
                p1.getUsername(), p2.getUsername(), player1score, player2score));
        updatePlayerStatistics(game, p1, p2);
    }

    private void updatePlayerStatistics(Game game, TournamentPlayer p1, TournamentPlayer p2) {
       /* HashMap<String, TournamentPlayer> playerLookup = new HashMap<>();
        playerLookup.put(p1.getUsername(), p1);
        playerLookup.put(p2.getUsername(), p2);
        */
        Match m = game.getMatch();
        Round r = m.getRound();
        Challenge c = r.getChallenge();

        PlayerStats p1stats = p1.getStats();
        PlayerStats p2stats = p2.getStats();
        p1stats.setGamesPlayed(p1stats.getGamesPlayed() + 1);
        p2stats.setGamesPlayed(p2stats.getGamesPlayed() + 1);
        //p1 forfeited
        if (forfeitGameMap.get(game) != null && forfeitGameMap.get(game) == p1.getUsername()) {
            PlayerStats ps = p1.getStats();
            ps.setForfeits(ps.getForfeits() + 1);
            p2stats.setWinsByForfeit(p2stats.getWinsByForfeit() + 1);//Else, ps is player 2, P2 forfeited.
            p1stats.setLossesByForfeit(p1stats.getLossesByForfeit() + 1);
        }//p2 forfeited
        else if (forfeitGameMap.get(game) != null && forfeitGameMap.get(game) == p2.getUsername()) {
            PlayerStats ps = p2.getStats();
            ps.setForfeits(ps.getForfeits() + 1);
            p1stats.setWinsByForfeit(p1stats.getWinsByForfeit() + 1);//If our ps is the same as p1stats, it means P1 forfeited.
            p2stats.setLossesByForfeit(p2stats.getLossesByForfeit() + 1);
        } else {
            if (game.getPlayer1FinalScore() > game.getPlayer2FinalScore()) {
                p1stats.setWins(p1stats.getWins() + 1);
                p2stats.setLosses(p2stats.getLosses() + 1);
                p2stats.setLargestpointdifference(game.getPlayer2FinalScore(), game.getPlayer1FinalScore());
            } else if (game.getPlayer1FinalScore() == game.getPlayer2FinalScore()) {
                p1stats.setTies(p1stats.getTies() + 1);
                p2stats.setTies(p2stats.getTies() + 1);
            } else {
                p2stats.setWins(p2stats.getWins() + 1);
                p1stats.setLosses(p1stats.getLosses() + 1);
                p1stats.setLargestpointdifference(game.getPlayer1FinalScore(), game.getPlayer2FinalScore());
            }
        }

        p1stats.setTotalPoints(p1stats.getTotalPoints() + game.getPlayer1FinalScore());
        p2stats.setTotalPoints(p2stats.getTotalPoints() + game.getPlayer2FinalScore());

        p1stats.setOpponentTotalPoints(p1stats.getOpponentTotalPoints() + game.getPlayer2FinalScore());
        p2stats.setOpponentTotalPoints(p2stats.getOpponentTotalPoints() + game.getPlayer1FinalScore());

        Logger.endGame(c.getTournamentID(), c.getChallengeID(), r.getRoundID(), m.getMatchID(), game.getGameID(), p1, p2);
    }

    public TournamentPlayer getPlayer1() {
        return player1;
    }

    public TournamentPlayer getPlayer2() {
        return player2;
    }

    private void sendGameMessage(String playerMessage) {
        player1.sendMessageToPlayer(playerMessage);
        player2.sendMessageToPlayer(playerMessage);
    }

    public Round getRound() {
        return round;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Game getGame1() {
        return game1;
    }

    public Game getGame2() {
        return game2;
    }

}
