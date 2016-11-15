package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import javafx.geometry.Point2D;

public class GameSystem implements PlayerInAdapter
{
    private AreaManager areaManager;
    private GameBoard gameBoard;
    private FreeSpaceBoard freeSpaceBoard;

    private TileStack ts;
    private PlayableTile playableTile;
    public GameSystem()
    {
        initializeGame();
    }
    public void acceptTurn(Turn t) {
        PlayableTile tile = t.getPlayableTile();
        Point2D tilePlacement = t.getPosition();
        int rotationDegrees = t.getRotationDegrees();
        if (!freeSpaceBoard.isPlaceable(tilePlacement,tile, rotationDegrees)) {
            forfeit(t);
        } else if (freeSpaceBoard.needToRemove(tile)){
            checkForPlayerRequest();
        } else {
            BoardTile boardtile = new BoardTile(tile, rotationDegrees);
            gameBoard.placeTile(tilePlacement, boardtile);
        }

    }

    public void forfeit(Turn turn) {
        System.out.println("Player " + turn.getPlayerID() + "forfeits");
    }

    public void checkForPlayerRequest() {
        System.out.println("Need to get input from player");
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