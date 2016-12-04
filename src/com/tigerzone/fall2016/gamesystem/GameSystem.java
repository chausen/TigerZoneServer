package com.tigerzone.fall2016.gamesystem;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.GameBoard;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameSystem implements PlayerInAdapter {

    // Collaborators
    private GameBoard gameBoard;
    private FreeSpaceBoard fsb;
    private TileStack ts;
    private Scorer scorer;
    private AreaManager am;

    // Game State
    private PlayableTile currentTile;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private PlayableTile originTile;

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
     * @param tileStack  is the the stack of playable tiles for a game
     */
    public void initializeGame(String player1id, String player2id, LinkedList<PlayableTile> tileStack)
    {
        player1 = new Player(player1id);
        player2 = new Player(player2id);
        currentPlayer = player1; // TournamentPlayer 1 is always the current player

        fsb = new FreeSpaceBoard();
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        scorer = new Scorer(players, outAdapter);
        am = new AreaManager(scorer);
        gameBoard = am.getGameBoard();

        ts = new TileStack(tileStack);

        // Tile Unplaceable Test
        //ts = new TileStack(getUnplaceableTestStack());

        // Tile Unplaceable Pass Test
        // ts = new TileStack(getUnplaceablePassTestStack());

        currentTile = ts.peek();
    }

    public String getTileRepresentationString(Set<BoardTile> boardtiles){
        return gameBoard.getPointRepresentation(boardtiles);
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
            endOfGame();
        }
        // place tile, if Tile isn't the same one we gave, boot them.
        else if (!currentTile.equals(turn.getPlayableTile())) {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            endOfGame();
        }
        // Check if they tried to place the tile in an invalid position
        //areaManager.place() //needs to be boolean
        else if (!fsb.isPlaceable(turn.getPosition(), turn.getPlayableTile(), turn.getRotationDegrees())) {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            endOfGame();
        } else {
            fsb.placeTile(turn.getPosition(), turn.getPlayableTile());
            // update areas
            if ( turn.placingAnimal() ) {
                if (am.addTile(turn.getPosition(), turn.getPlayableTile(), turn.getPlaceableAnimal(),
                        turn.getAnimalPlacementZone(), turn.getRotationDegrees())) {
                    outAdapter.successfulTurn();
                    // notify outAdapter with results
                    prepareNextTurn();
                }
                else if( turn.getAnimalPlacementZone()!=0 && am.invalidMeeplePlacement(turn.getPlayableTile(), turn.getAnimalPlacementZone(), turn.getRotationDegrees())){
                    outAdapter.forfeitInvalidMeeple(getCurrentPlayerID());
                    endOfGame();
                }
                else {
                    outAdapter.forfeitIllegalMeeple(getCurrentPlayerID());
                    endOfGame();
                }
            } else if ( !turn.placingAnimal() ) {
                am.addTile(turn.getPosition(), turn.getPlayableTile(), turn.getRotationDegrees());
                outAdapter.successfulTurn();
                // notify outAdapter with results
                prepareNextTurn();
            }
        }
    }

    @Override
    public void receivePass(){
        if (passCheck()) {
            outAdapter.successfulTurn();
            prepareNextTurn();
        } else {
            outAdapter.forfeitIllegalTile(getCurrentPlayerID());
            endOfGame();
        }
    }

    private boolean passCheck(){
        boolean passed = true;
        if(!currentTileCannotBePlaced) {
            passed = false;
        }
        return passed;
    }


    /**
     * This method retrieves a Tiger from an area and adds it back to the current player's supply
     * NOTE: should be used during special case when tile is not placable
     * @param x
     * @param y
     */
    public void tigerRetrieve(int x, int y){
        Point position = new Point(x,y);
        BoardTile boardTile = gameBoard.getTile(position);
        if(passCheck() && boardTile.removeTiger(this.currentPlayer.getPlayerId())){
            this.currentPlayer.incrementGoodSupply();
            outAdapter.successfulTurn();
            prepareNextTurn();
        } else {
            outAdapter.forfeitInvalidMeeple(currentPlayer.getPlayerId());
            endOfGame();
        }
    }

    /**
     * This method places a Tiger to an area and decrements the current player's supply
     * @param x
     * @param y
     */
    public void tigerPlace(int x, int y){
        int currentPlayerSupply = currentPlayer.getGoodSupply();
        if (passCheck() && currentPlayerSupply > 0) {
            Point position = new Point(x,y);
            BoardTile boardTile = gameBoard.getTile(position);
            currentPlayer.decrementGoodSupply();
            Tiger tiger = new Tiger(currentPlayer);
            if (boardTile.placeTiger(tiger)) {
                outAdapter.successfulTurn();
                prepareNextTurn();
            } else {
                outAdapter.forfeitInvalidMeeple(currentPlayer.getPlayerId());
                endOfGame();
            }
        } else {
            outAdapter.forfeitInvalidMeeple(currentPlayer.getPlayerId());
            endOfGame();
        }
    } 

    public void truncateTS(int x) {
        ts.truncateTS(x);
    }

    /**
     * Used to get a TournamentPlayer from their ID
     *
     * @param playerID
     * @return the TournamentPlayer object corresponding to a playerID, or null if their is no player with that ID
     */
    public Player getPlayer(String playerID) {
        Player player;
        if (player1.getPlayerId() == playerID) {
            player = player1;
        } else if (player2.getPlayerId() == playerID) {
            player = player2;
        } else {
            player = null;
        }
        return player;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public int getPlayerScore(Player p){
        return scorer.getScore(p);
    }

    // Gets the next tile and checks if any remain / if the tile can be placed
    // This method sets the currentTileCannotBePlaced attribute used in other methods
    private void prepareNextTurn() {
        ts.pop();
        this.currentTile = ts.peek();
        // If there are no tiles remaining, end the game
        if (this.currentTile == null) {
            //scores incomplete areas
            scorer.endGameScoring(am);
            int player1Score = scorer.getScore(player1);
            int player2Score = scorer.getScore(player2);
            outAdapter.notifyEndGame(player1Score, player2Score);
        } else {
            // Check if the next tile is playable
            currentTileCannotBePlaced = (fsb.needToRemove(currentTile));//needtoRemove USED TO return TRUE if PLACEABLE
            // The other player becomes the current player
            currentPlayer = (currentPlayer.equals(player1) ? player2 : player1);
        }
    }

    /**
     * Only called in the event an illegal message is received so that the GameSystem is always the
     * object to trigger the end of a game. Notifies the outAdapter of end of game.
     */
    @Override
    public void forfeit() {
        endOfGame();
    }

    @Override
    public String getCurrentTile() {
        return ts.peek().getTileString();
    }

    //========== Helper Methods ===========//
    private String getCurrentPlayerID() {
        return currentPlayer.getPlayerId();
    }

    // This method is used a lot so it makes the code a little clearer
    private void endOfGame() {
        int player1Score = scorer.getScore(player1);
        int player2Score = scorer.getScore(player2);
        outAdapter.notifyEndGame(player1Score, player2Score);
    }

    private LinkedList<PlayableTile> getUnplaceableTestStack() {
        LinkedList<PlayableTile> tiles = new LinkedList<>();
        tiles.add(new PlayableTile("LJJJ-"));
        tiles.add(new PlayableTile("LLLL-"));
        tiles.add(new PlayableTile("LLLL-"));
        tiles.add(new PlayableTile("LLLL-"));
        tiles.add(new PlayableTile("LLLL-"));
        return tiles;
    }

    private LinkedList<PlayableTile> getUnplaceablePassTestStack() {
        LinkedList<PlayableTile> tiles = new LinkedList<>();
        tiles.add(new PlayableTile("LJJJ-"));
        tiles.add(new PlayableTile("TJTJ-"));
        return tiles;
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}




