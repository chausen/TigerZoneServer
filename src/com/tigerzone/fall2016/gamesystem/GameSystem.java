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
import java.util.List;

public class GameSystem implements PlayerInAdapter {

    // Collaborators
    private GameBoard gameBoard;
    private FreeSpaceBoard fsb;
    private TileStack ts;
    private Scorer scorer;
    // private AreaManager am;

    // Game State
    private PlayableTile origintile;
    private PlayableTile currentTile;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Turn currentTurn;

    // Communication
    private PlayerOutAdapter outAdapter;

    public GameSystem() {}

    /**
     * Receives a turn from a PlayerOutAdapter and carries out the turn:
     * 1) Checks if the tile is placeable
     * 2) Updates tile areas (which may trigger scoring)
     * 3) Sends the next tile to the PlayerOutAdapter
     *
     * If there are no tiles remaining, the player with the higher score is passed to the adapter as the winner
     * If a forfeit event occurs, it is passed to the adapter
     *
     * @param t  Turn object holding the player whose turn it is, their tile placement, and predator placement
     */
    public void receiveTurn(Turn t)
    {
        this.currentTurn = t;

        // place tile
        PlayableTile currentTile = currentTurn.getPlayableTile();
        if (!currentTile.equals(currentTile)) {
            outAdapter.forfeitIllegalTile(getForfeitWinner());
        }

        if (!fsb.isPlaceable(t.getPosition(), t.getPlayableTile(), t.getRotationDegrees())) {
            outAdapter.forfeitIllegalTile(getForfeitWinner());
        } else {
            fsb.placeTile(t.getPosition(), t.getPlayableTile());
        }

        // update areas

        // notify outAdapter with results

        this.currentTile = ts.pop();
        // If there are no tiles remaining, end the game
        if (this.currentTile == null) {
            Set<String> winners = scorer.announceWinners();
            outAdapter.notifyEndGame(winners);
        } else {
            // Check if the next tile is playable
            if ( fsb.needToRemove(currentTile) ) {
                outAdapter.unplaceableTile();
            }
            // The other player becomes the current player
            currentPlayer = (currentPlayer.equals(player1) ? player2 : player1);
        }
    }

    public boolean isTilePlaceable(PlayableTile pt){
        return fsb.needToRemove(pt);
    }

    @Override
    public void triggerSendTurn() {
        outAdapter.sendTurnInitial(currentPlayer.getPlayerId(), currentTile);
    }

    /**
     * Creates the objects necessary to play a game, shuffles the tile stack,
     * and passes the entire tile stack, and first tile, to the adapter
     *
     * @param player1id  the playerID of the player who will go first
     * @param player2id  the playerID of the player who will go second
     * @param seed  used to generate a unique Tile order for a game
     */
    public void initializeGame(String player1id, String player2id, long seed)
    {
        player1 = new Player(player1id);
        player2 = new Player(player2id);
        currentPlayer = player1; // Player 1 is always the current player

        fsb = new FreeSpaceBoard();
        // am = new AreaManager();

        ts = new TileStack(seed, new TextFilePort());
        origintile = ts.pop();
        ts.shuffle(); //Shuffle
        List<PlayableTile> allTiles = ts.getTileList();

        outAdapter.notifyBeginGame(allTiles);
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




