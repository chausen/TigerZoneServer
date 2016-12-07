package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
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

    private void playMatch() {
        forfeitGameMap = new HashMap<>();
        int moveNumber = 1;
        game1.initializeIOport();
        game2.initializeIOport();
        boolean game1EndNotified = false;
        boolean game2EndNotified = false;

        while ((!game1.isOver() || !game2.isOver()) && moveNumber < 77) {

//            if (game1.isOver() && !game1EndNotified) {
//                sendEndMessage(game1);
//            } else if (game2.isOver() && !game2EndNotified) {
//                sendEndMessage(game2);
//            } else {

            //A single game will be doing the following in each line of the if statement...
            //Create prompt message for both players
            //Send each player their own prompt message
            //If a player didn't respond in time put them in the forfeitMap for the game they should have sent in a move for

            boolean game1Timeout = false;
            String gamePlayer1Response = null;

            if (!game1.isOver()) {
                String game1playerPrompt = GameToClientMessageFormatter.generateMessageToActivePlayer(game1.getGameID(), 1, moveNumber, game1.getCurrentTile());
                game1player.sendMessageToPlayer(game1playerPrompt);
                //timeout to start
                try { //here, we attempt to read from the client socket and throw a timeout exception if it isn't done fast enough
                    gamePlayer1Response = game1player.readPlayerMessage();
                } catch (SocketTimeoutException e) {
                    game1Timeout = true; //somewhat redundant to have this boolean when have the line below
                    game1player.timeOut();
                    gamePlayer1Response = "GAME " + game1.getGameID() + " MOVE " + moveNumber + " PLAYER " + game1player.getUsername() + " FORFEITED: TIMEOUT";
                    System.out.println("Timeout in game 1: " + game1player.getUsername());
                    forfeitGameMap.put(game1, game1player.getUsername());
                } catch (IOException e) {
                    System.out.println("Caught IOException in match besides timeout (Player 1)");
                    System.out.println("This is their input " + gamePlayer1Response);
                    e.printStackTrace();
                }
            }

            boolean game2Timeout = false;
            String gamePlayer2Response = null;
            if (!game2.isOver()) {
                String game2playerPrompt = GameToClientMessageFormatter.generateMessageToActivePlayer(game2.getGameID(), 1, moveNumber, game2.getCurrentTile());
                game2player.sendMessageToPlayer(game2playerPrompt);
                //timeout to start
                try {
                    gamePlayer2Response = game2player.readPlayerMessage();
                } catch (SocketTimeoutException e) {
                    game2Timeout = true; //somewhat redundant to have this boolean when have the line below
                    game2player.timeOut();
                    gamePlayer2Response = "GAME " + game2.getGameID() + " MOVE " + moveNumber + " PLAYER " + game2player.getUsername() + " FORFEITED: TIMEOUT";
                    System.out.println("Timeout in game 2: " + game2player.getUsername());
                    forfeitGameMap.put(game2, game2player.getUsername());
                } catch (IOException e) {
                    System.out.println("Caught IOException in match besides timeout (Player 2)");
                    System.out.println("This is their input " + gamePlayer2Response);
                    e.printStackTrace();
                }
            }
            //A single game will be doing the following in each line of the if statement...
            //Get each player's response after 1 second
            //Send each player's response to the respective gamePort
            //Get the ioPort's response
            //Send the ioPort's response to both players. Note that each player gets the same message
            //If there move is not legal put the player in the forfeit map for the game which they were the active player

            if (!game1.isOver()) {
                if (game1Timeout) {
                    sendGameMessage(gamePlayer1Response);
                    game1.endGame();
                } else {
                    game1.receiveTurn(gamePlayer1Response);
                    String gameResponse = game1.getResponse();
                    if (gameResponse.contains("FORFEITED")) {
                        forfeitGameMap.put(game1, game1player.getUsername());
                    }
                    sendGameMessage(gameResponse);
                }
            }
            if (!game2.isOver()) {
                if (game2Timeout) {
                    sendGameMessage(gamePlayer2Response);
                    game2.endGame();
                } else {
                    game2.receiveTurn(gamePlayer2Response);
                    String gameResponse = game2.getResponse();
                    if (gameResponse.contains("FORFEITED")) {
                        forfeitGameMap.put(game2, game2player.getUsername());
                    }
                    sendGameMessage(gameResponse);
                }
            }

            //swap who is the active player in each game
            swapPlayers();
            //Increment move count
            moveNumber++;
        }
        notifyEndGameToPlayers(); //sends players the "send outcome" message

        checkPlayerScoreResponse(game1, game1.player1);
        checkPlayerScoreResponse(game1, game1.player2);
        displayForfeitMessages(game1);
        displayFinalScore(game1);

        checkPlayerScoreResponse(game2, game2.player1);
        checkPlayerScoreResponse(game2, game2.player2);
        displayForfeitMessages(game2);
        displayFinalScore(game2);

        round.notifyComplete();
    }

    private void checkPlayerScoreResponse(Game game, TournamentPlayer tournamentPlayer) {
        String response = null;
        try {
            response = tournamentPlayer.readPlayerMessage();
        } catch (SocketTimeoutException e) {
            tournamentPlayer.timeOut();
            game.addForfeitMessage(GameToClientMessageFormatter.generateForfeitMessageToBothPlayers(game.getGameID(), tournamentPlayer.getUsername(), "FORFEITED: TIMEOUT"));
        } catch (IOException e) {
            System.out.println("Some IOException in getScoreResponse from player: " + tournamentPlayer.getUsername());
        }
        game.receiveScoreGuess(tournamentPlayer.getUsername(), response);
    }

    private void checkScoringGuessForfeits() {

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
        player.sendMessageToPlayer("THE REMAINING 78 TILES ARE " + tileToSTring(tileStack));
        player.sendMessageToPlayer("MATCH BEGINS IN " + setUpTime + " SECONDS");


        System.out.println("YOUR OPPONENT IS PLAYER " + opponentUserName);
        System.out.println("STARTING TILE IS TLTJ- AT 0 0 0");
        System.out.println("THE REMAINING 78 TILES ARE " + tileToSTring(tileStack));
        System.out.println("MATCH BEGINS IN " + setUpTime + " SECONDS");
    }

    private void sendMessageToPlayers() {
        sendStartMessage(player1, player2.getUsername());
        sendStartMessage(player2, player1.getUsername());
    }

    private void notifyEndGameToPlayers() {
            sendEndMessage(game1);
            sendEndMessage(game2);
    }

    private void sendEndMessage(Game game) {
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();

        player1.sendMessageToPlayer("GAME " + game.getGameID() + " OVER SEND OUTCOME");
        player2.sendMessageToPlayer("GAME " + game.getGameID() + " OVER SEND OUTCOME");
        updatePlayerStatistics(game, p1, p2);
    }

    private void displayForfeitMessages(Game game) {
        for (String forfeitMessage: game.getForfeitMessages()) {
            player1.sendMessageToPlayer(forfeitMessage);
            player2.sendMessageToPlayer(forfeitMessage);
        }
    }


    private void displayFinalScore(Game game) {
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();
        int player1Score = game.getPlayer1FinalScore();
        int player2Score = game.getPlayer2FinalScore();
        player1.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                player1Score + " PLAYER " + p2.getUsername() + " " + player2Score);
        player2.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                player1Score + " PLAYER " + p2.getUsername() + " " + player2Score);
        updatePlayerStatistics(game, p1, p2);
    }

    private void sendForfeitMessage(Game game) {
        TournamentPlayer p1 = game.getPlayer1();
        TournamentPlayer p2 = game.getPlayer2();
        String player1score = (forfeitGameMap.get(game) != p1.getUsername() ? "WIN" : "FORFEITED");
        String player2score = (forfeitGameMap.get(game) != p2.getUsername() ? "WIN" : "FORFEITED");
        player1.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                player1score + " PLAYER " + p2.getUsername() + " " + player2score);
        player2.sendMessageToPlayer("GAME " + game.getGameID() + " OVER PLAYER " + p1.getUsername() + " " +
                player1score + " PLAYER " + p2.getUsername() + " " + player2score);
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
            p1stats.setLosses(p1stats.getLosses() + 1);
        }//p2 forfeited
        else if (forfeitGameMap.get(game) != null && forfeitGameMap.get(game) == p2.getUsername()) {
            PlayerStats ps = p2.getStats();
            ps.setForfeits(ps.getForfeits() + 1);
            p1stats.setWinsByForfeit(p1stats.getWinsByForfeit() + 1);//If our ps is the same as p1stats, it means P1 forfeited.
            p2stats.setLosses(p2stats.getLosses() + 1);
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
