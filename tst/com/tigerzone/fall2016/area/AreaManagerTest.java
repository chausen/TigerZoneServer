package com.tigerzone.fall2016.area;

import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Player;
import com.tigerzone.fall2016.ports.CMDPromptPort;
import com.tigerzone.fall2016.ports.IOPort;
import com.tigerzone.fall2016.ports.TextFilePort;
import com.tigerzone.fall2016.scoring.Scorer;
import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aidan on 11/16/2016.
 */
public class AreaManagerTest {
    AreaManager areaManager;

    @Before
    public void setUp() throws Exception {
        List<Player> players = new ArrayList<>() ;
        TextFilePort textFilePort = new TextFilePort();
        LinkedList<PlayableTile> tileStack = textFilePort.createTiles();
        PlayerOutAdapter playerOutAdapter = new CMDPromptPort(5,"Clay", "Matt",tileStack);

        players.add(new Player("Clay"));
        Scorer scorer = new Scorer(players, playerOutAdapter);
        areaManager = new AreaManager(scorer);
    }

    @Test
    public void addTile() throws Exception {
        areaManager.addTile(new Point(-1,0), new PlayableTile("TJTJ-"), 0);
        assertTrue(areaManager.getTrailAreas().size() ==  2);
        assertTrue(areaManager.getJungleAreas().size() == 3);
        areaManager.addTile(new Point(0,1), new PlayableTile("TJTT-"), 90);
        assertTrue(areaManager.getJungleAreas().size() == 4);
        assertTrue(areaManager.getLakeAreas().size() == 1);
        areaManager.addTile(new Point(1,0), new PlayableTile("LLLL-"), 0);
        assertTrue(areaManager.getLakeAreas().size() == 1);
    }

    @Test
    public void addTile2() throws Exception {
        areaManager.addTile(new Point(0,-1), new PlayableTile("TJTT-"), 270);
        areaManager.addTile(new Point(0,-2), new PlayableTile("TJJT-"), 180);
        areaManager.addTile(new Point(0,-3), new PlayableTile("TLLTB"), 0);
        areaManager.addTile(new Point(1, -1), new PlayableTile("JJTJX"), 270);
        assertTrue(areaManager.getJungleAreas().size() == 3);
        assertTrue(areaManager.getLakeAreas().size() == 2);
        assertTrue(areaManager.getTrailAreas().size() == 4);
        assertTrue(areaManager.getDenAreas().size() == 1);
    }

    @Test
    public void addTile3() throws Exception {
        areaManager.addTile(new Point(1, 0), new PlayableTile("LJJJ-"), 90);
    }

}