package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Crocodile;
import com.tigerzone.fall2016.animals.Predator;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.gamesystem.GameSystem;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.parsing.ProtocolStateMachine;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Jeff on 2016/11/13.
 */
public class IOPort implements PlayerOutAdapter {
    private PlayerInAdapter inAdapter;
    private Queue<String> player1UpstreamMessages;
    private Queue<String> player2UpstreamMessages;
    private Queue<String> currentUpstreamMessages;
    private LinkedList<PlayableTile> tileStack;
    private ProtocolStateMachine protocol;
    
    private int gid;
    private String loginName1;
    private String loginName2;
    private PlayableTile activeTile;
    private String activeplayer;
    private String activeMove;
    private String currentTurnString;
    private boolean gameOver = false;

    /**
     * Constructor: Create a new IOPort which then creates GameSystem/new match for two players.
     * @param gid Game ID
     * @param loginName1 First player in our match. Note that this player will always be the first to go.
     * @param loginName2 Second player in our match. This player will always be second to go.
     * @param tileStack Stack of pre-made Tiles.
     */
    public IOPort(int gid, String loginName1, String loginName2, LinkedList<PlayableTile> tileStack) {
        this.gid = gid;
        this.loginName1 = loginName1;
        this.activeplayer = loginName1;
        this.loginName2 = loginName2;
        player1UpstreamMessages = new LinkedList<>();
        player2UpstreamMessages = new LinkedList<>();
        currentUpstreamMessages = player1UpstreamMessages;
        
        this.tileStack = tileStack;
    }

    public void initialize() {
        this.inAdapter = new GameSystem();
        inAdapter.setOutAdapter(this);
        inAdapter.initializeGame(loginName1, loginName2, tileStack);

    }

    public void initialize(PlayerInAdapter inAdapter) {
        this.inAdapter = inAdapter;
        inAdapter.setOutAdapter(this);
    }

    @Override
    public void notifyBeginGame(List<PlayableTile> allTiles) {
        PlayableTile firstTile = allTiles.get(0);
        this.currentUpstreamMessages.add("NEW MATCH YOUR OPPONENT IS " + loginName2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("THE TILES ARE [ ");
        Iterator<PlayableTile> iter = allTiles.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(" ");
            stringBuilder.append(iter.next().getTileString());
        }
        stringBuilder.append(" ] ");
        this.currentUpstreamMessages.add(stringBuilder.toString());
        this.currentUpstreamMessages.add("MATCH BEGINS IN 15 SECONDS");
        this.currentUpstreamMessages.add("YOU ARE THE ACTIVE PLAYER IN GAME 1 PLACE " + firstTile.getTileString() + " WITHIN 1 SECONDS");
    }

    @Override
    public void receiveTurn(String s) {
        // Needed to output move is inAdapter finds it successful
        currentTurnString = s;

        Scanner sc = new Scanner(s);

        String determiner = sc.next();//This gives us one of three things as guaranteed by the Server: PLACE, TILE, or QUIT
        switch(determiner)
        {
            case "PLACE":
                receiveTurnPlace(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                break;

            case "TILE":
                receiveTurnTile(sc.nextLine().substring(1));//Gets rid of the space and sends the remainder of the line.
                break;

            case "QUIT":
                receiveTurnQuit();
                break;
        }
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
            } else {
                //TODO:  move this to server? Doesn't seem like a bad idea to leave it as an extra layer of protection
                currentUpstreamMessages.add("GAME " + gid + " PLAYER " +  activeplayer + " FORFEITED ILLEGAL MESSAGE RECEIVED " + currentTurnString);
                //TODO: think of way to send the message for "notifyEndGame" that is usually only called by GS here
            }
        } else if (predatorStr.equals("CROCODILE")) {
            predator = new Crocodile(activePlayer);
        } else if (predatorStr.equals("NONE")) {
            predator = null;
        } else {
            //TODO: See about TODO
            currentUpstreamMessages.add("GAME " + gid + " PLAYER " +  activeplayer + " FORFEITED ILLEGAL MESSAGE RECEIVED " + currentTurnString);
        }

        PlayableTile playableTile = new PlayableTile(tileString);
        Turn t = new Turn(activeplayer, playableTile, new Point(x,y), orientation, predator, zone);

        // Send turn downstream
        inAdapter.receiveTurn(t);
    }

    private void receiveTurnTile(String s){
        Scanner scanner = new Scanner(s);
        StringBuilder sb = new StringBuilder();
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

    // Only forfeit condition handled in adapter
    // Called in this way to make interface more expressive
    private void receiveTurnQuit(){
        currentUpstreamMessages.add("GAME 1 PLAYER " + activeplayer + " FORFEITED QUIT");
    }

    //========== End of Helper Methods for Receive Turn ==========//
    @Override
    public void successfulTurn() {
        currentUpstreamMessages.add("GAME "+gid+" PLAYER "+getActivePlayer()+" PLACED "+currentTurnString);
        switchActivePlayer();
    }

    @Override
    public void reportScoringEvent(Map<Player,Integer> playerScores) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GAME " + gid + " ");
        Set<Player> players = playerScores.keySet();
        for (Player player: players) {
            stringBuilder.append("PLAYER " + player.getPlayerId() + " SCORED " + playerScores.get(player) + " POINTS ");
        }
        this.currentUpstreamMessages.add(stringBuilder.toString());
    }

    @Override
    public void forfeitIllegalMeeple(String currentPlayerID) {
        this.currentUpstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL MEEPLE PLACEMENT "+ activeMove);
    }

    @Override
    public void forfeitInvalidMeeple(String currentPlayerID) {
        this.currentUpstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED INVALID MEEPLE PLACEMENT "+ activeMove);
    }

    @Override
    public void forfeitIllegalTile(String currentPlayerID) {
        this.currentUpstreamMessages.add("GAME 1 PLAYER " + currentPlayerID + " FORFEITED ILLEGAL TILE PLACEMENT "+ activeMove);
    }

    @Override
    public void notifyEndGame(Map<Player, Integer> playerScores) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GAME 1 OUTCOME ");
        Set<Player> players = playerScores.keySet();
        Iterator<Player> iterator = players.iterator();
        Player player1 = iterator.next();
        Player player2 = iterator.next();
        stringBuilder.append("PLAYER " + player1.getPlayerId() + " " + playerScores.get(loginName1) + " ");
        stringBuilder.append("PLAYER " + player2.getPlayerId() + " " + playerScores.get(loginName2));
        this.currentUpstreamMessages.add(stringBuilder.toString());
        //        System.exit(0);
        gameOver = true;
    }


    //========== Accessors ==========//

    private PlayableTile getActiveTile(){
        return activeTile;
    }

    private String getActivePlayer(){
        return activeplayer;
    }

    public Queue<String> getCurrentMessageQueue() {
        return currentUpstreamMessages;
    }

    public Queue<String> getPlayer1MessageQueue() {
        return player1UpstreamMessages;
    }

    public Queue<String> getPlayer2MessageQueue() {
        return player2UpstreamMessages;
    }

    private String getCurrentTurnString(){
        return currentTurnString;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public PlayerInAdapter getInAdapter() {
        return this.inAdapter;
    }

    //========== Helper Methods ==========//
    // Helps alternate between player1 and player2
    private void switchActivePlayer() {
        activeplayer = (activeplayer == loginName1) ? loginName2 : loginName1;
        currentUpstreamMessages = (currentUpstreamMessages == player1UpstreamMessages) ? player2UpstreamMessages : player1UpstreamMessages;
    }

}
