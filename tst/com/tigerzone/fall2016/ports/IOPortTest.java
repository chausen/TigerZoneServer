package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by Jeff on 2016/11/14.
 */
public class IOPortTest {

    TextFilePort textFilePort = new TextFilePort();
    LinkedList<PlayableTile> tileStack = textFilePort.createTiles();

    private CMDPromptPort cmdp = new CMDPromptPort(1, "Ruby Red", "Sapphire Blue", tileStack);
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
            public void setOutAdapter(PlayerOutAdapter outAdapter) {}

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

            @Override
            public Player getPlayer(String playerID) {
                return null;
            }

            public String getTurnString() { return turn; }
        };

        cmdp.initialize(mockGameSystem);
    }

    @Test
    public void testSendTurn() throws Exception {

    }

    @Test
    public void testReceiveTurn() throws Exception {
        String s = "PLACE JJJJ- AT 3 2 90 NONE";
        cmdp.receiveTurn(s);
        Object o = mockGameSystem.getClass().getMethod("getTurnString").invoke(mockGameSystem);
        String str = (String) o;
        //TODO: Get these to work
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
}