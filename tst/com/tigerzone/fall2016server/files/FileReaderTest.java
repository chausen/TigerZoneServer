package com.tigerzone.fall2016server.files;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by clayhausen on 11/26/16.
 */
public class FileReaderTest {
    private String currentDirectory;

    @Before
    public void setUp() {
        currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
    }

    @Test
    public void getMoves() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/moveset1_P1.txt");
        List<String> moves = FileReader.getMoves(sb.toString());
        // Check to make sure the first move was read in correctly
        assertEquals(moves.get(0), "PLACE JJJJX AT -1 0 0 NONE");
        // Check to make sure the last move was read in correctly
        assertEquals(moves.get(moves.size() - 1), "PLACE JLTTB AT 3 -2 270 NONE");
    }

    @Test
    public void getCredentials() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(currentDirectory);
        sb.append("/src/com/tigerzone/fall2016server/files/TournamentCredentials.txt");
        HashMap<String,String> credentials = FileReader.getCredentials(sb.toString());
        // Check to make sure the first credential exists and it's password was added correctly
        assertEquals(credentials.get("TEAMA"), "IAMA");
        // Check to make sure the second credential exists and it's password was added correctly
        assertEquals(credentials.get("TEAMB"), "IAMB");
    }

}