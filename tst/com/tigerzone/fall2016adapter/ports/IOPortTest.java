package com.tigerzone.fall2016adapter.ports;

import com.tigerzone.fall2016adapter.PlayerInAdapter;
import com.tigerzone.fall2016adapter.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.BoardTile;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import com.tigerzone.fall2016server.tournament.TileStackGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Jeff on 2016/11/16.
 */
public class IOPortTest {
    TextFilePort textFilePort = new TextFilePort();
    LinkedList<PlayableTile> tileStack = textFilePort.createTiles();

    private IOPort cmdp = new IOPort(1, "Ruby Red", "Sapphire Blue", tileStack);
    private PlayerInAdapter mockGameSystem;

    @Before
    public void setUp() {
        // Create mock anonymous class
        mockGameSystem = new PlayerInAdapter() {
            String turn;

            @Override
            public void initializeGame(String player1id, String player2id, LinkedList<PlayableTile> playableTiles) {

            }

            @Override
            public Player getPlayer(String playerID) {
                return null;
            }

            @Override
            public Player getCurrentPlayer() {
                return null;
            }

            @Override
            public int getPlayerScore(Player p) {
                return 0;
            }

            @Override
            public String getTileRepresentationString(Set<BoardTile> tiles) {
                return null;
            }

            @Override
            public void setOutAdapter(PlayerOutAdapter outAdapter) {
            }

            @Override
            public void receiveTurn(Turn t) {
                StringBuilder sb = new StringBuilder();
                sb.append("PLACE ");
                sb.append(t.getPlayableTile().getTileString());
                sb.append(" AT ");
                sb.append(t.getPosition().getX());
                sb.append(" ");
                sb.append(t.getPosition().getY());
                sb.append(" ");
                //TODO add stuff that isn't stable in Tile
                turn = sb.toString();
            }

            @Override
            public void receivePass() {

            }

            @Override
            public void tigerRetrieve(int x, int y) {

            }

            @Override
            public void tigerPlace(int x, int y) {

            }

            @Override
            public void truncateTS(int x) {

            }

            public String getTurnString() {
                return turn;
            }

            @Override
            public void forfeit() {

            }

            @Override
            public String getCurrentTile() {
                return null;
            }

        };

        cmdp.initialize(mockGameSystem);
    }

    @Test
    public void testReceiveTurn() throws Exception {
        //TODO: Get these to work
//        String s = "PLACE JJJJ- AT 3 2 90 NONE";
//        cmdp.receiveTurn(s);
//        Object o = mockGameSystem.getClass().getMethod("getTurnString").invoke(mockGameSystem);
//        String str = (String) o;
//        Assert.assertEquals(s, str);
//        s = "TILE Whatever";
//        cmdp.receiveTurn(s);
//        s = "QUIT";
//        cmdp.receiveTurn(s);
    }

    @Test
    public void testSendTilesInOrder() throws Exception {

    }

    @Test
    public void testNotifyBeginGame() throws Exception {

    }

    @Test
    public void testNotifyEndGame() throws Exception {

    }

    @Test
    public void testSendTurn() throws Exception {
        Scanner sc = null;
        {
            try {
                sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/Game.txt").getFile()));
            } catch (FileNotFoundException exc) {
                System.out.println("FATAL: The Tile text file cannot be found.");
            }
        }

        TileStackGenerator tileStackGenerator = new TileStackGenerator();
        LinkedList<PlayableTile> tileStack = tileStackGenerator.createTilesFromTextFile(123456789);

        IOPort cmdp = new IOPort(1, "Taco", "Bell", tileStack);
        cmdp.initialize();
        cmdp.getInAdapter().truncateTS(20);//Make the tile set only 20

//        while (!cmdp.isCurrentMessageQueueEmpty()) {
//            System.out.println(cmdp.getMessageFromCurrentMessageQueue());
//        }
//
//        while (!cmdp.isCurrentMessageQueueEmpty() && (!cmdp.isGameOver() && sc.hasNext())) {
//            String line = sc.nextLine();
//            cmdp.receiveTurn(line);
//            System.out.println(cmdp.getMessageFromCurrentMessageQueue());
//        }
    }
}