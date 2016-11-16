package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.*;

/**
 * Created by Jeff on 2016/11/14.
 */
public class CMDPromptPort extends IOPort {

    Scanner sc = new Scanner(System.in);
    String activeMove;
    private boolean isGameOver = false;

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     *
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed       Seed value for randomization of TileStack inside GameSystem.
     */
    public CMDPromptPort(String loginName1, String loginName2, long seed) {
        super(loginName1, loginName2, seed);
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
    public void notifyEndGame(Set<String> winners) {
        StringBuilder stringBuilder = new StringBuilder();
        /*stringBuilder.append("GAME 1 OUTCOME PLAYER ");
        stringBuilder.append(winners.)
        System.out.println(" <pid> <score> PLAYER <pid> <score>");
        */
        System.out.println("SOMEONE WON");
        setGameOverTrue();
    }

    private void setGameOverTrue() {
        isGameOver = true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public void forfeitIllegalMeeple(String winner) {
        System.out.println("GAME 1 PLAYER "+getActivePlayer()+" FORFEITED ILLEGAL MEEPLE PLACEMENT "+ activeMove);
        setGameOverTrue();
    }

    @Override
    public void forfeitInvalidMeeple(String winner) {
        System.out.println("GAME 1 PLAYER "+getActivePlayer()+" FORFEITED INVALID MEEPLE PLACEMENT "+ activeMove);
        setGameOverTrue();
    }

    @Override
    public void forfeitIllegalTile(String winner) {
        System.out.println("GAME 1 PLAYER "+getActivePlayer()+" FORFEITED ILLEGAL TILE PLACEMENT "+ activeMove);
        setGameOverTrue();
    }
}
