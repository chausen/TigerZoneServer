package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;

public class GameSystem implements PlayerInAdapter {

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
        Point tilePlacement = t.getPosition();
        int rotationDegrees = t.getRotationDegrees();
        if (freeSpaceBoard.needToRemove(tile)) {
            if (checkForPlayerRequest()) {
                handlePlayerRequest();
            } else {
                forfeit(t); //didn't make request: forfeit
            }
        } else if (checkForPlayerRequest()){ //made request at wrong time: forfeit
            forfeit(t);
        } else if (!freeSpaceBoard.isPlaceable(tilePlacement,tile,rotationDegrees)) { //invalid tile placement: forfeit
            forfeit(t);
        } else { //valid move, play the tile
            playTile(tile, tilePlacement, rotationDegrees);
        }
    }

    public void playTile(PlayableTile playableTile, Point tilePlacement, int rotationDegrees) {
        BoardTile boardTile = new BoardTile(playableTile, rotationDegrees);
        gameBoard.placeTile(tilePlacement, boardTile);
    }

    public void forfeit(Turn turn) {
        System.out.println("Player " + turn.getPlayerID() + "forfeits");
        //updateObservers();
    }

    public boolean checkForPlayerRequest() {
        System.out.println("Need to get input from player");
        return false;
    }

    public void handlePlayerRequest() {
        System.out.println("Handling player request");
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