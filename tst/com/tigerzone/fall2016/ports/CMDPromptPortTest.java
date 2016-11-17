package com.tigerzone.fall2016.ports;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Jeff on 2016/11/16.
 */
public class CMDPromptPortTest {

    @Test
    public void testSendTurn() throws Exception {
        Scanner sc = null;
        {
            try {
                sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/Game.txt").getFile()));
            } catch(FileNotFoundException exc){System.out.println("FATAL: The Tile text file cannot be found.");}
        }
        CMDPromptPort cmdp = new CMDPromptPort(1, "Taco", "Bell", 123456789);
        cmdp.initialize();

//        while (true) {
//            cmdp.sendTurn();
//        }
    }
}