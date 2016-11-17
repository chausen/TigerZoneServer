package com.tigerzone.fall2016server;

import com.tigerzone.fall2016.ports.CMDPromptPort;
import com.tigerzone.fall2016.ports.IOPort;

import java.util.Queue;
import java.util.Scanner;

/**
 * Created by clayhausen on 11/16/16.
 */
public class CmdPromptInterface {
    private IOPort gamePort;
    private Scanner scanner;
    private Queue<String> msgQueue;
    private String message;
    private String turn;

    private int gid = 1;
    private String loginName1;
    private String loginName2;
    private long seed = 123456789;

    public CmdPromptInterface() {
        scanner = new Scanner(System.in);
    }

    public void startGame() {

        login();

        gamePort = new CMDPromptPort(gid, loginName1, loginName2, seed);
        msgQueue = gamePort.getMessageQueue();

        gamePort.initialize();

        while ( !gamePort.isGameOver() ) {
            while (!msgQueue.isEmpty()) {
                message = msgQueue.remove();
                System.out.println(message);
            }
            System.out.print("> ");
            turn = scanner.nextLine();
            gamePort.receiveTurn(turn);
        }
    }

    private void login() {
        System.out.print("Enter username for player 1: ");
        this.loginName1 = scanner.nextLine();
        System.out.print("Enter username for player 2: ");
        this.loginName2 = scanner.nextLine();
    }

}



