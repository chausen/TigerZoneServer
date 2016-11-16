package com.tigerzone.fall2016;

import com.tigerzone.fall2016.ports.CMDPromptPort;

import java.util.Scanner;

/**
 * Created by Aidan on 11/7/2016.
 */
public class Main {
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        System.out.print("Enter username for player 1: ");
        String loginName1 = in.nextLine();
        System.out.print("Enter username for player 2: ");
        String loginName2 = in.nextLine();

        long seed = 123456789;

        CMDPromptPort cmdPort = new CMDPromptPort(loginName1, loginName2, seed);
        cmdPort.initialize();
        while(!cmdPort.isGameOver()){
        cmdPort.receiveTurn(in.nextLine());
        }
    }
}
