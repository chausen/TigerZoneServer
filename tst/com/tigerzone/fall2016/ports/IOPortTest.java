package com.tigerzone.fall2016.ports;

import com.tigerzone.fall2016.adapters.PlayerInAdapter;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Turn;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jeff on 2016/11/14.
 */
public class IOPortTest {
    private CMDPromptPort cmdp = new CMDPromptPort("Ruby Red", "Sapphire Blue", 234);
    private PlayerInAdapter mockGameSystem;

    @Before
    public void setUp() {
        // Create mock anonymous class
        mockGameSystem = new PlayerInAdapter() {
            String turn;
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
            public void startGame() {}
            public void initializeGame(String player1id, String player2id, long seed) {}
            public void setOutAdapter(PlayerOutAdapter outAdapter) {}

            @Override
            public boolean isTilePlaceable(PlayableTile playableTile) {
                return false;
            }

            @Override
            public void triggerSendTurn() {

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
        s = "TILE Whatever";
        cmdp.receiveTurn(s);
        s = "QUIT";
        cmdp.receiveTurn(s);
        Assert.assertEquals(s, str);
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