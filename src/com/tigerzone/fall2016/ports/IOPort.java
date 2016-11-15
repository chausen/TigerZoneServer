package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.*;

import javafx.geometry.Point2D;

/**
 * Created by Jeff on 2016/11/13.
 */
public abstract class IOPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;
    private Scanner scanner;
    private long seed; // seed corresponding to the tile order
    private Map<Integer, String> players; // key: playerID, value: loginName
    private String loginName1;
    private String loginName2;

    //TODO: Have the Network who creates this IOPort say who the first player is.

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

        // Remove?
//        int playerID1 = 1;
//        int playerID2 = 2;
//        players = new HashMap<>();
//        players.put(playerID1, loginName1);
//        players.put(playerID2, loginName2);

        // Remove?
//        this.inAdapter = new GameSystem(playerID1, playerID2, seed);
//        inAdapter.setOutAdapter(this);
    }

    public void initialize() {
        int playerID1 = 1;
        int playerID2 = 2;
        players = new HashMap<>();
        players.put(playerID1, loginName1);
        players.put(playerID2, loginName2);

        this.inAdapter = new GameSystem(playerID1, playerID2, seed);
        inAdapter.setOutAdapter(this);
    }

    public void initialize(PlayerInAdapter inAdapter) {
        int playerID1 = 1;
        int playerID2 = 2;
        players = new HashMap<>();
        players.put(playerID1, loginName1);
        players.put(playerID2, loginName2);

        this.inAdapter = inAdapter;
        inAdapter.setOutAdapter(this);
    }

    @Override
    public abstract void sendTurn(Turn lastturn, AreaTile areatile);

    //TODO: Put validation of message integrity in here (Ex: They actually said "PLACE ...")
    @Override
    public void receiveTurn(String s) {
            StringBuilder testSb = new StringBuilder();
            Scanner sc = new Scanner(s);

            String place = sc.next();//This gives us PLACE
            testSb.append(place);

            String tiletextrep = sc.next();//This gives us the Text representation of the PlayableTile.
            testSb.append(tiletextrep);

            String at = sc.next();//This gives us AT
            testSb.append(at);

            int x = sc.nextInt();//This gives us the x coord
            int y = sc.nextInt();//This gives us the y coord
            int orientation = sc.nextInt();//This gives us the orientation (rotation degree)
            testSb.append(Integer.toString(x));
            testSb.append(Integer.toString(y));
            testSb.append(Integer.toString(orientation));

            String animal = sc.next();
            testSb.append(animal);

            if (animal.equals("TIGER")) {
                if (sc.hasNext()) {
                    int zone = sc.nextInt();//This gives us zone
                    testSb.append(zone);
                } else {
                    // invalid move
                }
            }

            PlayableTile playableTile = new PlayableTile(tiletextrep, orientation);
            //TODO: Give an actual Direction (or figure out those problems :( )
            Turn t = new Turn(0, playableTile, orientation, null, new Point2D(x,y));

            inAdapter.receiveTurn(t);
            System.out.println(testSb.toString());
    }

    @Override
    public abstract void sendTilesInOrder(List<AreaTile> allAreaTiles);

    @Override
    public abstract void notifyBeginGame(AreaTile areatile);

    @Override
    public abstract void notifyEndGame(Set<Integer> winners);
}
