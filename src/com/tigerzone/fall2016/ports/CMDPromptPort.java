package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.*;

/**
 * Created by Jeff on 2016/11/14.
 */
public class CMDPromptPort extends IOPort {

    private String activeMove;


    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     *
     * @param gid Game ID
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param tileStack       Stack of tiles for use in the game.
     */
    public CMDPromptPort(int gid, String loginName1, String loginName2, LinkedList<PlayableTile> tileStack) {
        super(gid, loginName1, loginName2, tileStack);
    }

    //TODO: Do we need to output this every turn, or just before the first move of the game? Delete if not.
//    @Override
//    public void sendTurn() {
//        this.upstreamMessages.add("YOU ARE THE ACTIVE PLAYER IN GAME 1 PLACE "+getActiveTile().getTileString()+" WITHIN 1 SECONDS");
//        activeMove =
//        receiveTurn(activeMove);
//    }

    @Override
    public void notifyBeginGame(List<PlayableTile> allTiles) {
        PlayableTile firstTile = allTiles.get(0);
        this.upstreamMessages.add("NEW MATCH YOUR OPPONENT IS " + loginName2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("THE TILES ARE [ ");
        Iterator<PlayableTile> iter = allTiles.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(" ");
            stringBuilder.append(iter.next().getTileString());
        }
        stringBuilder.append(" ] ");
        this.upstreamMessages.add(stringBuilder.toString());
        this.upstreamMessages.add("MATCH BEGINS IN 15 SECONDS");
        this.upstreamMessages.add("YOU ARE THE ACTIVE PLAYER IN GAME 1 PLACE " + firstTile.getTileString() + " WITHIN 1 SECONDS");
    }

    @Override
    public void forfeitIllegalMeeple(String currentPlayerID) {
        this.upstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL MEEPLE PLACEMENT "+ getCurrentTurnString());
    }

    @Override
    public void forfeitInvalidMeeple(String currentPlayerID) {
        this.upstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED INVALID MEEPLE PLACEMENT "+ getCurrentTurnString());
    }

    @Override
    public void forfeitIllegalTile(String currentPlayerID) {
        this.upstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL TILE PLACEMENT "+ getCurrentTurnString());
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
        this.upstreamMessages.add(stringBuilder.toString());
        //        System.exit(0);
        gameOver = true;
    }
}
