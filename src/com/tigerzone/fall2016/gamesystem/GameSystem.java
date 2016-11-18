package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class GameSystem implements PlayerInAdapter {

    // Collaborators
    private GameBoard gameBoard;
    private FreeSpaceBoard fsb;
    private TileStack ts;
    private Scorer scorer;
    private AreaManager am;

    // Game State
    private PlayableTile origintile;
    private PlayableTile currentTile;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Turn currentTurn;

    // Communication
    private PlayerOutAdapter outAdapter;
    // Set in prepareNextTurn()
    // NOTE: First tile always placeable based on Origin Tile.
    private boolean currentTileCannotBePlaced = false;

    public GameSystem() {}

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
        am = new AreaManager();
        List<String> players = new ArrayList<>();
        players.add(player1id);
        players.add(player2id);
        scorer = new Scorer(players, am);

        ts = new TileStack(seed, new TextFilePort());
        origintile = ts.pop();
        ts.shuffle(); //Shuffle
        currentTile = ts.peek();
        List<PlayableTile> allTiles = ts.getTileList();

        outAdapter.notifyBeginGame(allTiles);
    }

    public void setOutAdapter(PlayerOutAdapter outAdapter){
        this.outAdapter = outAdapter;
    }

    /**
     * Receives a turn from a PlayerOutAdapter and carries out the turn:
     * 1) Checks if the tile is placeable
     * 2) Updates tile areas (which may trigger scoring)
     * 3) Sends the next tile to the PlayerOutAdapter
     *
     * If there are no tiles remaining, the player with the higher score is passed to the adapter as the winner
     * If a forfeit event occurs, it is passed to the adapter
     *
     * @param turn  Turn object holding the player whose turn it is, their tile placement, and predator placement
     */
    //Only for "PLACE"
    public void receiveTurn(Turn turn)
    {
        //TODO: Refactor duplicate code
        //They called the wrong thing if this goes through.
        if(currentTileCannotBePlaced) {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            outAdapter.notifyEndGame(scorer.getPlayerScores());
        }
        // place tile, if Tile isn't the same one we gave, boot them.
        else if (!currentTile.equals(turn.getPlayableTile())) {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            outAdapter.notifyEndGame(scorer.getPlayerScores());
        }
        // Check if they tried to place the tile in an invalid position
        //areaManager.place() //needs to be boolean
        else if (!fsb.isPlaceable(turn.getPosition(), turn.getPlayableTile(), turn.getRotationDegrees())) {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            outAdapter.notifyEndGame(scorer.getPlayerScores());
        } else {
            fsb.placeTile(turn.getPosition(), turn.getPlayableTile());
            // update areas
            if ( turn.placingPredator() ) {
                if (am.addTile(turn.getPosition(), turn.getPlayableTile(), turn.getPredator(),
                        turn.getPredatorPlacementZone(), turn.getRotationDegrees())) {
                } else {
                    outAdapter.forfeitIllegalMeeple(getCurrentPlayerID());
                    outAdapter.notifyEndGame(scorer.getPlayerScores());
                }
            } else if ( !turn.placingPredator() ) {
                am.addTile(turn.getPosition(), turn.getPlayableTile(), turn.getRotationDegrees());
            }
            outAdapter.successfulTurn();
            // notify outAdapter with results
            prepareNextTurn();
        }
    }

    @Override
    public void receivePass(){
        tileUnplaceableCheck();
        //TODO: Ask Dave if we need to broadcast a person's Unplaceable Turn.
        //Add logic for dealing with unplaceable Tile PASS turns.
        // outAdapter.successfulTurn();
        prepareNextTurn();
    }

    /**
     * This method retrieves a Tiger from an area and adds it back to the current player's supply
     * NOTE: should be used during special case when tile is not placable
     * @param x
     * @param y
     */
    public void tigerRetrieve(int x, int y){
        tileUnplaceableCheck();
        Point position = new Point(x,y);
        BoardTile boardTile = gameBoard.getTile(position);

        if(boardTile.removeTiger(this.currentPlayer.getPlayerId())){
            this.currentPlayer.incrementSupply();
        }else{
            outAdapter.forfeitInvalidMeeple(currentPlayer.getPlayerId());
            outAdapter.notifyEndGame(scorer.getPlayerScores());
        }

        //Add logic for dealing with retrieving Tigers from a location with one placed.
        // outAdapter.successfulTurn();
        prepareNextTurn();
    }

    /**
     * This method places a Tiger to an area and decrements the current player's supply
     * @param x
     * @param y
     */
    public void tigerPlace(int x, int y){
        tileUnplaceableCheck();
        Point position = new Point(x,y);
        BoardTile boardTile = gameBoard.getTile(position);

        int currentPlayerSupply = currentPlayer.getSupply();
        if(!(currentPlayerSupply == 0)){
            currentPlayer.decrementSupply();
            Tiger tiger = new Tiger(currentPlayer.getPlayerId());
            boardTile.placeTiger(tiger);
        }else{
            //forfeit
            outAdapter.forfeitInvalidMeeple(currentPlayer.getPlayerId());
            outAdapter.notifyEndGame(scorer.getPlayerScores());

        }
        //Add logic for dealing with placing an additional Tiger.
        // outAdapter.successfulTurn();
        prepareNextTurn();
    }

    // If the current tile can be placed but they are taking one of the actions
    // for when the tile is unplaceable: invalid move; forfeit
    private void tileUnplaceableCheck(){
        if(!currentTileCannotBePlaced)
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            outAdapter.notifyEndGame(scorer.getPlayerScores());
    }

    // Gets the next tile and checks if any remain / if the tile can be placed
    // This method sets the currentTileCannotBePlaced attribute used in other methods
    private void prepareNextTurn() {
        ts.pop();
        this.currentTile = ts.peek();
        // If there are no tiles remaining, end the game
        if (this.currentTile == null) {
            Map<String, Integer> playerScores = scorer.getPlayerScores();
            outAdapter.notifyEndGame(playerScores);
        } else {
            // Check if the next tile is playable
            currentTileCannotBePlaced = (!fsb.needToRemove(currentTile));//needtoRemove returns TRUE if PLACEABLE
            // The other player becomes the current player
            currentPlayer = (currentPlayer.equals(player1) ? player2 : player1);
        }
    }

    //TODO: Do we need this?
//    @Override
//    public void triggerSendTurn() {
//        outAdapter.sendTurnInitial(currentPlayer.getPlayerId(), currentTile);
//    }

    //========== Helper Methods ===========//
    private String getCurrentPlayerID() {
        return currentPlayer.getPlayerId();
    }

}




