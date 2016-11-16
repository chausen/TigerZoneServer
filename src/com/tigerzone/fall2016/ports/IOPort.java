package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.*;


/**
 * Created by Jeff on 2016/11/13.
 */
public abstract class IOPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;
    private long seed; // seed corresponding to the tile order
    private String loginName1;
    private String loginName2;
    private PlayableTile activeTile;
    private String activeplayer;

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed Seed value for randomization of TileStack inside GameSystem.
     */
    public IOPort(String loginName1, String loginName2, long seed) {
        this.loginName1 = loginName1;
        this.loginName2 = loginName2;
        this.seed = seed;
    }

    public void initialize() {
        this.inAdapter = new GameSystem(loginName1, loginName2, seed);
        inAdapter.setOutAdapter(this);
    }

    public void initialize(PlayerInAdapter inAdapter) {
        this.inAdapter = inAdapter;
        inAdapter.setOutAdapter(this);
    }

    public void sendTurnInitial(String playerid, PlayableTile activeTile){
        this.activeTile = activeTile;
        activeplayer = playerid;
        sendTurn();
    }

    @Override
    public abstract void sendTurn();

    @Override
    public void receiveTurn(String s) {
        Scanner sc = new Scanner(s);

        String determiner = sc.next();//This gives us one of three things as guaranteed by the Server: PLACE, TILE, or QUIT
        switch(determiner)
        {
            case "PLACE":
                receiveTurnPlace(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                break;

            case "TILE":
                receiveTurnTile(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                break;

            case "QUIT":
                receiveTurnQuit();
                break;
        }
    }

    private void receiveTurnPlace(String s){
        Scanner sc = new Scanner(s);
        String tiletextrep = sc.next();//This gives us the Text representation of the PlayableTile.
        sc.next();//Gives us AT
        int x = sc.nextInt();//This gives us the x coord
        int y = sc.nextInt();//This gives us the y coord
        int orientation = sc.nextInt();//This gives us the orientation (rotation degree)
        String animal = sc.next();
        if (animal.equals("TIGER")) {
            if (sc.hasNext()) {
                int zone = sc.nextInt();//This gives us zone
            } else {
                // invalid move
            }
        }

        PlayableTile playableTile = new PlayableTile(tiletextrep, orientation);
        //TODO: Give an actual Direction (or figure out those problems :( )
        Turn t = new Turn(activeplayer, playableTile, new Point(x,y), orientation, null, 0);
        System.out.println("We are now at PLACE : "+s);
        inAdapter.receiveTurn(t);
    }

    private void receiveTurnTile(String s){
        System.out.println("We are now at Tile : "+s);
    }

    private void receiveTurnQuit(){
        System.out.println("We are now at Quit");
    }


    @Override
    public abstract void sendTilesInOrder(LinkedList<PlayableTile> allAreaTiles);

    @Override
    public abstract void notifyBeginGame(PlayableTile playableTile);

    @Override
    public abstract void notifyEndGame(Set<String> winners);

    public PlayableTile getActiveTile(){
        return activeTile;
    }

    public String getActivePlayer(){
        return activeplayer;
    }

    @Override
    public abstract void forfeitIllegalMeeple(int winner);

    @Override
    public abstract void forfeitInvalidMeeple(int winner);

    @Override
    public abstract void forfeitIllegalTile(int winner);
}
