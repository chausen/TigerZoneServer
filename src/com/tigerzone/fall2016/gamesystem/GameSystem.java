package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

public class GameSystem implements PlayerInAdapter
{
    private TileStack ts;
    private PlayableTile playableTile;
    public GameSystem()
    {
        initializeGame();
    }
    public void acceptTurn(Turn t)
    {

    }

    public void startGame(int player1id, int player2id)
    {
        //TODO: See how Player can be used with the rest of the program.
        Player player1 = new Player(1);
        Player player2 = new Player(2);
    }

    public void initializeGame()
    {
        //TODO: Change the hardcoded seed below
        ts = new TileStack(1234567890, new TextFilePort());//1234567890 = set seed. Will need to change in future iterations
        playableTile = ts.pop();
        ts.shuffle();//Shuffle
    }
}