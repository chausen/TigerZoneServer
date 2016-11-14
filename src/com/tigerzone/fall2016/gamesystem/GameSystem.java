package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GameSystem implements PlayerInAdapter
{
    private TileStack ts;
    private AreaTile origintile;
    private Scorer scorer;
    private AreaManager areamanager;
    private FreeSpaceBoard fsb;

    public GameSystem(int player1id, int player2id, long seed)
    {
        initializeGame(player1id, player2id, seed);
    }

    //TODO: Make this work with the innards of our system.
    public void acceptTurn(Turn t)
    {

    }

    public void initializeGame(int player1id, int player2id, long seed)
    {
        //TODO: See how Player can be used with the rest of the program.
        Player player1 = new Player(player1id);
        Player player2 = new Player(player2id);

        fsb = new FreeSpaceBoard();

        //TODO: Definitely change the nulls to Lists or figure out how to get those Lists. (´･ω･｀)
        areamanager = new AreaManager(null, null, null, null, fsb);

        ArrayList<Integer> playerids = new ArrayList<>();
        playerids.add(player1.getPlayerId());
        playerids.add(player2.getPlayerId());
        scorer = new Scorer(playerids, areamanager);
        //TODO: Change the hardcoded seed below
        ts = new TileStack(1234567890, new TextFilePort());//1234567890 = set seed. Will need to change in future iterations
        origintile = ts.pop();
        ts.shuffle();//Shuffle
    }

    public void startGame()
    {

    }
}