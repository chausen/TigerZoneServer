package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/20/2016.
 */
public class RoundRobinTest {

    private TileStackGenerator tileStackGenerator;
    private LinkedList<PlayableTile> tileStack;
    private List<TournamentPlayer> playerList;

    @Before
    public void setUp() throws Exception {
        tileStackGenerator = new TileStackGenerator();
        tileStack = tileStackGenerator.createTilesFromTextFile(123456789);
        playerList = new ArrayList<>();
        playerList.addAll(new ArrayList<>(Arrays.asList(new TournamentPlayer("Player1"), new TournamentPlayer("Player2"),new TournamentPlayer("Player3"), new TournamentPlayer("Player4"), new TournamentPlayer("Player5"))));

    }

    @Test
    public void listMatches() throws Exception {
        int numOfRounds = playerList.size() % 2 != 0 ? playerList.size() : playerList.size() - 1;
        for(int i = 0; i < numOfRounds; i++){
            List<Match> matches = RoundRobin.listMatches(playerList, i, tileStack);
            matches.clear();
        }
    }

}