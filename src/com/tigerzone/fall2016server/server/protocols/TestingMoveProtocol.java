package com.tigerzone.fall2016server.server.protocols;

import java.util.Scanner;

/**
 * Created by clayhausen on 11/19/16.
 */
public class TestingMoveProtocol {
    public static void main(String[] args) {
        ProtocolStateMachine psm = new ProtocolStateMachine();

        String testString1 = "GAME 1 MOVE 1 PLACE TLTTP AT 4 -1 90 NONE";
        String testString2 = "GAME 1 MOVE 2 PLACE TLLL- AT 4 1 90 NONE";
        String testString3 = "GAME 1 MOVE 3 TILE LLLL- UNPLACEABLE PASS";
        String testString4 = "GAME 1 MOVE 4 TILE LLLL- UNPLACEABLE RETRIEVE TIGER AT 1 1";
        String testString5 = "GAME 1 MOVE 4 TILE LLLL- UNPLACEABLE ADD ANOTHER TIGER TO 3 3";

        Scanner scanner = new Scanner(testString1);
        Context context = new MoveProtocolContext(scanner, 1);

        // Test 1
        psm.parse(context);
        System.out.println(testString1 + " is a valid move: " + context.wasMoveValid());

        // Test 2
        context.setScanner(new Scanner(testString2));
        psm.parse(context);
        System.out.println(testString2 + " is a valid move: " + context.wasMoveValid());

        // Test 3
        context.setScanner(new Scanner(testString3));
        psm.parse(context);
        System.out.println(testString3 + " is a valid move: " + context.wasMoveValid());

        // Test 4
        context.setScanner(new Scanner(testString4));
        psm.parse(context);
        System.out.println(testString4 + " is a valid move: " + context.wasMoveValid());

        // Test 5
        context.setScanner(new Scanner(testString5));
        psm.parse(context);
        System.out.println(testString5 + " is a valid move: " + context.wasMoveValid());
    }
}
