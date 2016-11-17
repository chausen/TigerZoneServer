package com.tigerzone.fall2016;

import com.tigerzone.fall2016.ports.CMDPromptPort;
import com.tigerzone.fall2016server.CmdPromptInterface;

import java.util.Scanner;

/**
 * Created by Aidan on 11/7/2016.
 */
public class Main {
    public static void main(String args[]){
        CmdPromptInterface cmd = new CmdPromptInterface();
        cmd.startGame();
    }
}
