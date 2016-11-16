package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.Set;

public class GameSystem implements PlayerInAdapter {

    // Collaborators
    private GameBoard gameBoard;
    private FreeSpaceBoard fsb;
    private TileStack ts;
    private PlayableTile playableTile;
    private Scorer scorer;
    // private AreaManager am;

    // Game State
    private PlayableTile origintile;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Turn currentTurn;

    // Communication
    private PlayerOutAdapter outAdapter;
    boolean gameInProgress;
    boolean waitingForInput;

    public GameSystem(String player1id, String player2id, long seed) {
        gameInProgress = true;
        waitingForInput = true;
        initializeGame(player1id, player2id, seed);
    }

    /**
     * Called by an adapter at the system's boundary to provide the current player's turn
     *
     * @param t  Turn object holding the player whose turn it is, their tile placement, and predator placement
     */
    public void receiveTurn(Turn t)
    {
        waitingForInput = false;
        this.currentTurn = t;
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
    public void initializeGame(String player1id, String player2id, long seed)
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

    //TODO: Refactor functionality into private methods

    /**
     * The main game loop. One iteration of the game loop encapsulates one turn.
     *
     */
    public void startGame()
    {
        // Game Loop
        while (gameInProgress) {
            // get turn
            while (waitingForInput) {
                // do nothing (。-`ω-)
            }

            // check if tile is unplayable
            PlayableTile currentTile = currentTurn.getPlayableTile();
            if ( fsb.needToRemove(currentTile) ) {
                // prompt player to:
                //   1. pass
                //   2. pick up one of their previously placed Tigers and return it to the supply
                //   3. put another tiger from their supply on top of a tiger they previously placed
            }

            // place tile
            Point position = currentTurn.getPosition();
            fsb.placeTile(position, currentTile);
            //TODO: Add forfeit check

            // update areas

            // notify outAdapter with results

            PlayableTile nextTile = ts.pop();
            // If there are no tiles remaining, end the game
            if (nextTile == null) {
                gameInProgress = false;
            }

            // The other player becomes the current player
            currentPlayer = (currentPlayer.equals(player1) ? player2 : player1);

        }
        Set<String> winners = scorer.announceWinners();
        outAdapter.notifyEndGame(winners);
        gameInProgress = false;
    }

    public void setOutAdapter(PlayerOutAdapter outAdapter){
        this.outAdapter = outAdapter;
    }

    //========== Helper Methods ===========//

    // Notifies the outAdapter that the player whose not currently taking their turn is the winner
    // (The player whose turn it currently is forfeits)

    private String getForfeitWinner() {
        String currentPlayerID = currentTurn.getPlayerID();
        String player1ID = player1.getPlayerId();
        String player2ID = player2.getPlayerId();

        String winningPlayerID = (currentPlayerID == player1ID) ? player2ID : player1ID;
        return winningPlayerID;
    }

    public PlayableTile getActiveTile()
    {
        return ts.pop();
    }


    //========== Erik's Methods ===========//
    public void acceptTurn(Turn t) {
        PlayableTile tile = t.getPlayableTile();
        Point tilePlacement = t.getPosition();
        int rotationDegrees = t.getRotationDegrees();
        if (fsb.needToRemove(tile)) {
            if (checkForPlayerRequest()) {
                handlePlayerRequest();
            } else {
                forfeit(t); //didn't make request: forfeit
            }
        } else if (checkForPlayerRequest()){ //made request at wrong time: forfeit
            forfeit(t);
        } else if (!fsb.isPlaceable(tilePlacement,tile,rotationDegrees)) { //invalid tile placement: forfeit
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

    //========== Erik's Methods ===========//
}




