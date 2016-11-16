package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import jdk.internal.org.objectweb.asm.commons.InstructionAdapter;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Jeff on 2016/11/13.
 */
public abstract class IOPort implements PlayerOutAdapter {
    protected PlayerInAdapter inAdapter;
    protected long seed; // seed corresponding to the tile order
    protected String loginName1;
    protected String loginName2;
    protected PlayableTile activeTile;
    protected String activeplayer;

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed Seed value for randomization of TileStack inside GameSystem.
     */
    public IOPort(String loginName1, String loginName2, long seed) {
        this.loginName1 = loginName1;
        this.activeplayer = loginName1;
        this.loginName2 = loginName2;
        this.seed = seed;
    }

    public void initialize() {
        this.inAdapter = new GameSystem();
        inAdapter.setOutAdapter(this);
        inAdapter.initializeGame(loginName1, loginName2, seed);
    }

    public void initialize(PlayerInAdapter inAdapter) {
        this.inAdapter = inAdapter;
        inAdapter.setOutAdapter(this);
    }

    @Override
    public void sendTurnInitial(String playerid, PlayableTile activeTile){
        this.activeTile = activeTile;
        activeplayer = playerid;
        sendTurn();
    }

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

    //========== Helper Methods for Receive Turn ==========//

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
       // System.out.println("We are now at PLACE : "+s);
        inAdapter.receiveTurn(t);
    }

    private void receiveTurnTile(String s){
       // System.out.println("We are now at Tile : "+s);
    }

    private void receiveTurnQuit(){
        System.out.println("We are now at Quit");
    }

    //========== End of Helper Methods for Receive Turn ==========//

    @Override
    public abstract void notifyBeginGame(List<PlayableTile> allAreaTiles);

    @Override
    public abstract void notifyEndGame(Set<String> winners);

    @Override
    public abstract void forfeitIllegalMeeple(String winner);

    @Override
    public abstract void forfeitInvalidMeeple(String winner);

    @Override
    public abstract void forfeitIllegalTile(String winner);

    //========== Accessors ==========//

    protected PlayableTile getActiveTile(){
        return activeTile;
    }

    protected String getActivePlayer(){
        return activeplayer;
    }
}
