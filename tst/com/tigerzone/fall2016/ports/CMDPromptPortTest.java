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
        CMDPromptPort cmdp = new CMDPromptPort("Taco", "Bell", 123456789);
        cmdp.initialize();
        Scanner sc = null;
        {
            try{
                sc = new Scanner(new File(getClass().getResource("/com/tigerzone/fall2016/ports/Game.txt").getFile()));
            }catch(FileNotFoundException exc){System.out.println("FATAL: The AreaTile text file cannot be found.");}
        }
        while(sc.hasNext()){
            cmdp.receiveTurn(sc.nextLine());
        }
    }
}