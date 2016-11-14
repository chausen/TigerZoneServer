package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.area.*;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.FreeSpaceBoard;
import com.tigerzone.fall2016.tileplacement.terrain.JungleTerrain;
import com.tigerzone.fall2016.tileplacement.tile.AreaTile;
import com.tigerzone.fall2016.tileplacement.tile.Edge;
import com.tigerzone.fall2016.tileplacement.tile.Tile;
import org.junit.Test;

import javafx.geometry.Point2D;
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

    private List<DenArea> denAreas;        // }
    private List<JungleArea> jungleAreas;  // |
    private List<LakeArea> lakeAreas;      // |---Needed for AreaManager
    private List<TrailArea> trailAreas;    // |
    private FreeSpaceBoard freeSpaceBoard; // }
    private AreaManager areaManager;

    @org.junit.Before
    public void setUp() throws Exception {
        // Populate player scores, with player1 having the higher score
        player1 = new Player(1);
        player2 = new Player(2);
        playerScores = new HashMap();
        playerScores.put(player1.getPlayerId(), 0);
        playerScores.put(player2.getPlayerId(), 0);

        // Create AreaManager
        denAreas = new ArrayList();
        jungleAreas = new ArrayList();
        lakeAreas = new ArrayList();
        trailAreas = new ArrayList();
        freeSpaceBoard = new FreeSpaceBoard();
        areaManager = new AreaManager(denAreas, jungleAreas, lakeAreas, trailAreas, freeSpaceBoard);

        // Create scorer
        scorer = new Scorer(playerScores, areaManager);
    }

    @org.junit.Test
    public void scoreDen() throws Exception {
        // Create a DenArea of size 9 whose owner is player1
//        DenArea den = new DenArea();
//        Edge edge = new Edge(new JungleTerrain(), new JungleTerrain(), new JungleTerrain());
//        for (int i = 0; i < 9; ++i) {
//            den.addTile(new Point2D(0,i), new AreaTile(edge, edge, edge, edge, new JungleTerrain()));
//        }
//        scorer.score(den);

        // player1 should have 9 points and player2 should have 0 points
//        assertTrue(scorer.getScore(1) == 9);
//        assertTrue(scorer.getScore(2) == 0);
    }

    @org.junit.Test
    public void scoreLake() throws Exception {
        // Create a LakeArea of size 3 with 2 unique animals, whose owner is player 1

        // player1 should have 3*2*(1+2) = 18 points and player2 should have 0 points
//        assertTrue(scorer.getScore(1) == 18);
//        assertTrue(scorer.getScore(2) == 0);
    }

    @org.junit.Test
    public void scoreTrail() throws Exception {
        // Create a TrailArea of size 5 with 1 unique animal, whose owner is player 1

        // player1 should have 5+1 = 6 points and player2 should have 0 points
//        assertTrue(scorer.getScore(1) == 6);
//        assertTrue(scorer.getScore(2) == 0);
    }

    @org.junit.Test
    public void endGameScoring() throws Exception {
        // Create 2 dens, 2 lakes, 2 trails and 1 jungle
        // For each Area in which 2 were created, 1 should be incomplete
        // The incomplete den should be of size 2 (including the den)
        // The incomplete lake should be of size 1
        // The incomplete trail should be of size 3
        // The jungle should be adjacent to the complete Den and the complete Lake
        // No area should have any prey
        // player1 should own each area

        // player1 should have 2 (dens) + 1 (lakes) + 3 (trails) + 8 (jungles) = 14 points
        // player2 should have 0 points
//        assertTrue(scorer.getScore(1) == 14);
//        assertTrue(scorer.getScore(2) == 0);
    }

    @org.junit.Test
    public void testAnnounceWinner() throws Exception {
        // player1 score = 32
        // player2 score = 15
        playerScores.put(player1.getPlayerId(), 32);
        playerScores.put(player2.getPlayerId(), 15);
        // player1 should be in the winners set, but player2 should not
        Set<Integer> winners = scorer.announceWinners();
        assertTrue(winners.contains(player1.getPlayerId()));
        assertFalse(winners.contains(player2.getPlayerId()));

        // Add a third player with the same score as player1
        Player player3 = new Player(3);
        playerScores.put(player3.getPlayerId(), 32);
        scorer = new Scorer(playerScores, areaManager);
        // Now the set of winners should contain player1 and player3
        winners = scorer.announceWinners();
        assertTrue(winners.contains(player1.getPlayerId()) && winners.contains(player3.getPlayerId()));
    }
}