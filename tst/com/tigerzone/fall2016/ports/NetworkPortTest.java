package com.tigerzone.fall2016.ports;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jeff on 2016/11/14.
 */
public class NetworkPortTest {
    private CMDPromptNetPort cmdp = new CMDPromptNetPort("Ruby Red", "Sapphire Blue", 234);

    @Test
    public void testSendTurn() throws Exception {

    }

    @Test
    public void testReceiveTurn() throws Exception {
        String s = "PLACE JJJJ- AT 3 2 90 NONE";
        cmdp.receiveTurn(s);
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