package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javafx.geometry.Point2D;

/**
 * Created by Jeff on 2016/11/13.
 */
public abstract class NetworkPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;
    private Scanner scanner;
    private long seed; // seed corresponding to the tile order
    private Map<Integer, String> players; // key: playerID, value: loginName
    private String loginName1;
    private String loginName2;

    //TODO: Have the Network who creates this NetworkPort say who the first player is.

    /**
     * Constructor: Create a new NetworkPort which then creates GameSystem/new match for two players.
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param seed Seed value for randomization of TileStack inside GameSystem.
     */
    public NetworkPort(String loginName1, String loginName2, long seed) {
        this.loginName1 = loginName1;
        this.loginName2 = loginName2;
        this.seed = seed;
        //TODO: Find way to convert Strings to ints for PlayerIDs
        this.inAdapter = new GameSystem(1, 2, seed);
        inAdapter.setOutAdapter(this);
    }

    @Override
    public abstract void sendTurn(Turn lastturn, AreaTile areatile);

    //TODO: Put validation of message integrity in here (Ex: They actually said "PLACE ...")
    @Override
    public void receiveTurn(String s) {
            String printstring = "begin";
            Scanner sc = new Scanner(s);
            printstring.concat(sc.next());//This gives us PLACE
            String tiletextrep = sc.next();//This gives us the Text representation of the PlayableTile.
            printstring.concat(tiletextrep);
            printstring.concat(sc.next());//This gives us AT
            int x = sc.nextInt();//This gives us the x coord
            int y = sc.nextInt();//This gives us the y coord
            int orientation = sc.nextInt();//This gives us the orientation (rotation degree)
            printstring.concat(Integer.toString(x));
            printstring.concat(Integer.toString(y));
            printstring.concat(Integer.toString(orientation));
            PlayableTile playableTile = new PlayableTile(tiletextrep, orientation);
        //TODO: Give an actual playerID, and Direction (or figure out those problems :( )
            Turn t = new Turn(0, playableTile, orientation, null, new Point2D(x,y));
        inAdapter.receiveTurn(t);
        System.out.println(printstring);
    }

    @Override
    public abstract void sendTilesInOrder(List<AreaTile> allAreaTiles);

    @Override
    public abstract void notifyBeginGame(AreaTile areatile);

    @Override
    public abstract void notifyEndGame(Set<Integer> winners);
}
