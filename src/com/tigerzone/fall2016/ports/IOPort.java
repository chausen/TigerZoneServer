package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.DenArea;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.area.LakeArea;
import com.tigerzone.fall2016.area.TrailArea;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.parsing.Context;
import com.tigerzone.fall2016.parsing.GameContext;
import com.tigerzone.fall2016.parsing.ProtocolStateMachine;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import com.tigerzone.fall2016server.server.Logger;
import com.tigerzone.fall2016server.server.protocols.GameToClientMessageFormatter;

import java.awt.*;
import java.util.*;

/**
 * Created by Jeff on 2016/11/13.
 */
public class IOPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;
    private LinkedList<PlayableTile> tileStack;
    
    private int gid;
    private int turnTime; // time allotted to place a turn
    private int turnCount; // used to track the current turn #
    private String loginName1;
    private String loginName2;
    private int player1FinalScore;
    private int player2FinalScore;
    private String activeplayer;
    private String activeMove;
    private String currentTurnString;
    private boolean gameOver = false;
    private String response;
    private boolean didForfeit = false;
    private String forfeitedPlayer = "";
    private GameContext gameContext;
    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     * @param gid Game ID
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param tileStack Stack of pre-made Tiles.
     */
    public IOPort(int gid, String loginName1, String loginName2, LinkedList<PlayableTile> tileStack) {
        this.gid = gid;
        this.turnTime = 1;
        this.turnCount = 1;
        this.loginName1 = loginName1;
        this.activeplayer = loginName1;
        this.loginName2 = loginName2;
        this.tileStack = tileStack;
    }

    public void initialize() {
        this.inAdapter = new GameSystem();
        inAdapter.setOutAdapter(this);
        inAdapter.initializeGame(loginName1, loginName2, tileStack);
        this.gameContext = new GameContext(this.gid);
    }

    public void initialize(PlayerInAdapter inAdapter) {
        this.inAdapter = inAdapter;
        inAdapter.setOutAdapter(this);
    }

//    @Override
//    public void promptForTurn(PlayableTile currentTile) {
//        String messageToActivePlayer =
//                GameToClientMessageFormatter.generateMessageToActivePlayer(this.gid, this.turnTime,
//                        this.turnCount, currentTile.getTileString());
//        currentUpstreamMessages.add(messageToActivePlayer);
//    }

    @Override
    public void receiveTurn(String s) {
        // Needed to output move is inAdapter finds it successful
        //boolean received = false;
        currentTurnString = s;

        Scanner parserScanner = new Scanner(s);
        gameContext.setScanner(parserScanner);
        ProtocolStateMachine psm = new ProtocolStateMachine();
        psm.parse(gameContext);
        if (!gameContext.wasMoveValid()) {
            receiveIllegalMessage();
        } else {

            Scanner sc = new Scanner(s);

            sc.next();
            sc.next();
            sc.next();
            sc.next();

            //Moving past the GAME and gid
            String determiner = sc.next();//This gives us one of three things as guaranteed by the Server: PLACE, TILE, or QUIT
            System.out.println("THIS IS THE DETERMINER STRING IN IOPORT " + determiner);
            switch (determiner) {
                case "PLACE":
                    System.out.println("GOT PLACE WITHIN IOPORT");
                    receiveTurnPlace(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                    //received = true;
                    break;

                case "TILE":
                    receiveTurnTile(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                    //received = true;
                    break;

                case "QUIT":
                    receiveTurnQuit();
                    //received = true;
                    break;
            }
        }
        //return received;
    }

    //========== Helper Methods for Receive Turn ==========//

    private void receiveTurnPlace(String s){
        Scanner sc = new Scanner(s);

        String tileString = sc.next(); // This gives us the Text representation of the PlayableTile.
        sc.next();                      // Gives us AT
        int x = sc.nextInt();           // This gives us the x coord
        int y = sc.nextInt();           // This gives us the y coord
        int orientation = sc.nextInt(); // This gives us the orientation (rotation degree)
        String predatorStr = sc.next(); // Conditional logic below determines what kind of predator
        Predator predator = null;       // This will hold the predator (tiger, crocodile, null if "NONE" is recieved)
        int zone = 0;
        // The zone of the tile where the predator will be placed
        Player activePlayer = inAdapter.getPlayer(activeplayer); // get the TournamentPlayer object associated with the loginID
        if (predatorStr.equals("TIGER")) {

            predator = new Tiger(activePlayer);
            if (sc.hasNext()) {
                zone = sc.nextInt();//This gives us zone
            }
        } else if (predatorStr.equals("CROCODILE")) {
            predator = new Crocodile(activePlayer);
        } else if (predatorStr.equals("NONE")) {
            predator = null;
        }

        PlayableTile playableTile = new PlayableTile(tileString);
        Turn t = new Turn(activeplayer, playableTile, new Point(x,y), orientation, predator, zone);

        // Send turn downstream
        inAdapter.receiveTurn(t);
    }

    private void receiveTurnTile(String s){
        Scanner scanner = new Scanner(s);
        scanner.next();//This gives us UNPLACEABLE.
        String determiner = scanner.next();//This gives us which one we need.
        switch(determiner){
            case "PASS":
                inAdapter.receivePass();
                break;

            case "RETRIEVE":
                scanner.next();//Gives us TIGER
                scanner.next();//Gives us AT
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                inAdapter.tigerRetrieve(x,y);
                break;

            case "ADD":
                scanner.next();//Gives us ANOTHER
                scanner.next();//Gives us TIGER
                scanner.next();//Gives us TO
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                inAdapter.tigerPlace(a,b);
                break;
        }
    }

    @Override
    public void receiveIllegalMessage() {
        // TODO: 11/26/2016 Need to set message prefix appropriately (player currently null)
        forfeit(activeplayer);
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, "FORFEITED ILLEGAL MESSAGE RECEIVED "));
        inAdapter.forfeit();

    }

    // Only forfeit condition handled in adapter
    // Called in this way to make interface more expressive
    private void receiveTurnQuit(){
        didForfeit = true;
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, "FORFEITED QUIT"));

    }

    //========== End of Helper Methods for Receive Turn ==========//
    @Override
    public void successfulTurn() {
//        String prefix = "GAME " + gid + " MOVE " + turnCount + " PLAYER " + activeplayer;
//        broadcast(prefix + " " + currentTurnString);
//        turnCount++;
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, currentTurnString));
        ++turnCount;
    }

    public void reportScoringEvent(Map<Player,Integer> playerScores) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GAME " + gid + " ");
        Set<Player> players = playerScores.keySet();
        for (Player player: players) {
            stringBuilder.append("PLAYER " + player.getPlayerId() + " SCORED " + playerScores.get(player) + " POINTS ");
        }
        // setResponse(stringBuilder.toString());
    }


    @Override
    public void reportScoringEvent(Map<Player, Integer> playerScores, JungleArea ja) {
        reportScoringEvent(playerScores);
        Logger.addFeatureScored(gid,ja);
    }

    @Override
    public void reportScoringEvent(Map<Player, Integer> playerScores, DenArea da) {
        reportScoringEvent(playerScores);
        Logger.addFeatureScored(gid,da);
    }

    @Override
    public void reportScoringEvent(Map<Player, Integer> playerScores, LakeArea la) {
        reportScoringEvent(playerScores);
//        Logger.addFeatureScored(gid,la);
    }

    @Override
    public void reportScoringEvent(Map<Player, Integer> playerScores, TrailArea ta) {
        reportScoringEvent(playerScores);
//        Logger.addFeatureScored(gid,ta);
    }

    @Override
    public void forfeitIllegalMeeple(String currentPlayerID) {
        forfeit(activeplayer);
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, "FORFEITED ILLEGAL MEEPLE PLACEMENT"));
    }

    @Override
    public void forfeitInvalidMeeple(String currentPlayerID) {
        forfeit(activeplayer);
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, "FORFEITED ILLEGAL MEEPLE PLACEMENT"));
    }

    @Override
    public void forfeitIllegalTile(String currentPlayerID) {
        forfeit(activeplayer);
        setResponse(GameToClientMessageFormatter.generateMessageToBothPlayers(this.gid, this.turnCount, this.activeplayer, "FORFEITED ILLEGAL TILE PLACEMENT "));
    }

    private void forfeit(String currentPlayer){
        didForfeit = true;
        forfeitedPlayer = currentPlayer;
    }

    public String getForfeitedPlayer(){
        return forfeitedPlayer;
    }

//    @Override
//    public void notifyEndGame(Map<Player, Integer> playerScores) {
//        Set<Player> players = playerScores.keySet();
//
//        player1FinalScore = playerScores.get(loginName1);
//        player2FinalScore = playerScores.get(loginName2);
//        gameOver = true;
//    }

    @Override
    public void notifyEndGame(int player1Score, int player2Score) {
        player1FinalScore = player1Score;
        player2FinalScore = player2Score;
        gameOver = true;
    }

    /**
     * To be called at the end of a game so that the match protocol can announce the winner / scores
     * @param playerId
     * @return the score of the player with playerId. Returns 0 if no player exists with that playerId
     */
    @Override
    public int getFinalScore(String playerId) {
        if (playerId.equals(loginName1)) {
            return player1FinalScore;
        } else if (playerId.equals(loginName2)) {
            return player2FinalScore;
        } else {
            return 0;
        }
    }

    //========== Accessors ==========//


    private String getCurrentTurnString(){
        return currentTurnString;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public PlayerInAdapter getInAdapter() {
        return this.inAdapter;
    }


    public String getCurrentTile(){
        return inAdapter.getCurrentTile();
    }

    public String getResponse(){
        //this.turnCount++;
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isDidForfeit(){
        return didForfeit;
    }
}
