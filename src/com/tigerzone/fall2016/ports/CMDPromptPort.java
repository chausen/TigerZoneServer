package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.*;

/**
 * Created by Jeff on 2016/11/14.
 */
public class CMDPromptPort extends IOPort {

    Scanner sc;
    String activeMove;

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     *
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed       Seed value for randomization of TileStack inside GameSystem.
     */
    public CMDPromptPort(String loginName1, String loginName2, long seed) {
        super(loginName1, loginName2, seed);
        sc = new Scanner(System.in);
    }

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     *
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed       Seed value for randomization of TileStack inside GameSystem.
     * @param scanner    Scanner reading the input required to play the game
     */
    public CMDPromptPort(String loginName1, String loginName2, long seed, Scanner scanner) {
        super(loginName1, loginName2, seed);
        this.sc = scanner;
    }

    @Override
    public void sendTurn() {
        System.out.println("YOU ARE THE ACTIVE PLAYER IN GAME 1 PLACE "+getActiveTile().getTileString()+" WITHIN 1 SECONDS");
        activeMove = sc.nextLine();
        receiveTurn(activeMove);
    }

    @Override
    public void notifyBeginGame(List<PlayableTile> allTiles) {
        PlayableTile firstTile = allTiles.get(0);
        System.out.println("NEW MATCH YOUR OPPONENT IS " + loginName2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("THE TILES ARE [ ");
        Iterator<PlayableTile> iter = allTiles.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(" ");
            stringBuilder.append(iter.next().getTileString());
        }
        stringBuilder.append(" ] ");
        System.out.println(stringBuilder.toString());
        System.out.println("MATCH BEGINS IN 15 SECONDS");
        System.out.println("YOU ARE THE ACTIVE PLAYER IN GAME 1 PLACE " + firstTile.getTileString() + " WITHIN 1 SECONDS");
        /*activeMove = sc.nextLine();
        receiveTurn(activeMove);*/
    }

    @Override
    public void notifyEndGame(Map<String, Integer> playerScores) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GAME 1 OUTCOME ");
        Set<String> players = playerScores.keySet();
        Iterator<String> iterator = players.iterator();
        String loginName1 = iterator.next();
        String loginName2 = iterator.next();
        stringBuilder.append("PLAYER " + loginName1 + " " + playerScores.get(loginName1) + " ");
        stringBuilder.append("PLAYER " + loginName2 + " " + playerScores.get(loginName2));
        System.out.println(stringBuilder.toString());
        System.exit(0);
    }

    @Override
    public void forfeitIllegalMeeple(String currentPlayerID) {
        System.out.println("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL MEEPLE PLACEMENT "+ activeMove);
    }

    @Override
    public void forfeitInvalidMeeple(String currentPlayerID) {
        System.out.println("GAME 1 PLAYER " + currentPlayerID + " FORFEITED INVALID MEEPLE PLACEMENT "+ activeMove);
    }

    @Override
    public void forfeitIllegalTile(String currentPlayerID) {
        System.out.println("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL TILE PLACEMENT "+ activeMove);
    }

    @Override
    protected void forfeitQuit(String currentPlayerID) {
        System.out.println("GAME 1 PLAYER " + currentPlayerID + " FORFEITED QUIT");
    }
}
