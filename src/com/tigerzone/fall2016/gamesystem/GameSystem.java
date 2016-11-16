package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import javafx.geometry.Point2D;

import java.util.*;

public class GameSystem implements PlayerInAdapter
{
    // Game State
    private TileStack ts;
    private PlayableTile origintile;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private FreeSpaceBoard fsb;
    private Scorer scorer;
    private Turn currentTurn;
    // private AreaManager am;

    // Communication
    private PlayerOutAdapter outAdapter;

    public GameSystem(int player1id, int player2id, long seed) {
        initializeGame(player1id, player2id, seed);
    }

    /**
     * Receives a turn from a PlayerOutAdapter and carries out the turn:
     * 1) Checks if the tile is placeable
     * 2) Updates tile areas (which may trigger scoring)
     * 3) Sends the next tile to the PlayerOutAdapter
     *
     * If there are no tiles remaining, the player with the higher score
     *
     * @param t  Turn object holding the player whose turn it is, their tile placement, and predator placement
     */
    public void receiveTurn(Turn t)
    {
        this.currentTurn = t;

        // check if tile is unplayable
        PlayableTile currentTile = currentTurn.getTile();
        if ( fsb.needToRemove(currentTile) ) {
            // prompt player to:
            //   1. pass
            //   2. pick up one of their previously placed Tigers and return it to the supply
            //   3. put another tiger from their supply on top of a tiger they previously placed
        }

        // place tile
        BoardTile boardTile = new BoardTile(currentTile);
        Point2D position = currentTurn.getPosition();
        fsb.placeTile(position, boardTile);
        //TODO: Add forfeit check

        // update areas

        // notify outAdapter with results

        PlayableTile nextTile = ts.pop();
        // If there are no tiles remaining, end the game
        if (nextTile == null) {
            Set<String> winners = scorer.announceWinners();
            outAdapter.notifyEndGame(winners);
        } else {
            // The other player becomes the current player
            currentPlayer = (currentPlayer.equals(player1) ? player2 : player1);
        }

    }

    public PlayableTile getActiveTile()
    {
        return ts.pop();
    }

    public boolean isTilePlaceable(PlayableTile pt){
        return fsb.needToRemove(pt);
    }

    /**
     * Creates the objects necessary to play a game, shuffles the tile stack,
     * and waits 15 seconds before starting the game
     *
     * @param seed  used to generate a unique Tile order for a game
     */
    public void initializeGame(int player1id, int player2id, long seed)
    {
        player1 = new Player(player1id);
        player2 = new Player(player2id);
        currentPlayer = player1; // Player 1 is always the current player

        fsb = new FreeSpaceBoard();

        ts = new TileStack(seed, new TextFilePort());
        origintile = ts.pop();
        ts.shuffle(); //Shuffle

        // pass the entire contents of the TileStack to the outAdapter
        outAdapter.sendTilesInOrder(ts.getTileList());
        startGame();
    }

    public void startGame() {}

    public void setOutAdapter(PlayerOutAdapter outAdapter){
        this.outAdapter = outAdapter;
    }

    //========== Helper Methods ===========//

    // Notifies the outAdapter that the player whose not currently taking their turn is the winner
    // (The player whose turn it currently is forfeits)

    private int getForfeitWinner() {
        int currentPlayerID = currentTurn.getPlayerID();
        int player1ID = player1.getPlayerId();
        int player2ID = player2.getPlayerId();

        int winningPlayerID = (currentPlayerID == player1ID) ? player2ID : player1ID;
        return winningPlayerID;
    }
}
