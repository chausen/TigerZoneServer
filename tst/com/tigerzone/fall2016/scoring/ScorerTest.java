package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.area.Area;
import com.tigerzone.fall2016.area.AreaManager;
import com.tigerzone.fall2016.gamesystem.Player;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by matthewdiaz on 11/7/16.
 */
public class ScorerTest {

    private Player player1;
    private Player player2;
    private HashMap<Integer,Integer> playerScores;
    private Scorer scorer;

    private List<Area> areaList;
    private AreaManager areaManager;

    @org.junit.Before
    public void setUp() throws Exception {
        // Populate player scores, with player1 having the higher score
        player1 = new Player(1092);
        player2 = new Player(3211);
        playerScores = new HashMap();
        playerScores.put(player1.getPlayerId(), 32);
        playerScores.put(player2.getPlayerId(), 15);

        // Create AreaManager
        areaList = new ArrayList();
        areaManager = new AreaManager(areaList);

        // Create scorer
        scorer = new Scorer(playerScores, areaManager);
    }

    @org.junit.Test
    public void testUpdateScore() throws Exception {

    }

    @org.junit.Test
    public void testAnnounceWinner() throws Exception {
        // player1 score = 32
        // player2 score = 15
        // player1 should be in the winners set, but player2 should not
        Set<Integer> winners = scorer.announceWinners();
        assertTrue(winners.contains(player1.getPlayerId()));
        assertFalse(winners.contains(player2.getPlayerId()));

        // Add a third player with the same score as player1
        Player player3 = new Player(1597);
        playerScores.put(player3.getPlayerId(), 32);
        scorer = new Scorer(playerScores, areaManager);
        // Now the set of winners should contain player1 and player3
        winners = scorer.announceWinners();
        assertTrue(winners.contains(player1.getPlayerId()) && winners.contains(player3.getPlayerId()));
    }
}