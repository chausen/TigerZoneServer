package com.tigerzone.fall2016.parsing;

import java.util.Scanner;

/**
 * Created by clayhausen on 11/19/16.
 */
public class testing {
    public static void main(String[] args) {
        ProtocolStateMachine psm = new ProtocolStateMachine();

        String testString1 = "GAME 1 MOVE 1 PLACE TLTTP AT 4 -1 90 NONE";
        String testString2 = "GAME 2 MOVE 1 PLACE TLLL- AT 4 1 90 NONE";
        String testString3 = "GAME 1 MOVE 2 TILE LLLL- UNPLACEABLE PASS";
        String testString4 = "GAME 1 MOVE 2 TILE LLLL- UNPLACEABLE RETRIEVE TIGER AT 1";

        Scanner scanner1 = new Scanner(testString1);
        Scanner scanner2 = new Scanner(testString2);
        Scanner scanner3 = new Scanner(testString3);
        Scanner scanner4 = new Scanner(testString4);

        Context context = new GameContext(scanner1, 1);
        psm.parse(context);
        System.out.println(testString1 + " is a valid move: " + context.wasMoveValid());

        context = new GameContext(scanner2, 2);

        psm.parse(context);
        System.out.println(testString2 + " is a valid move: " + context.wasMoveValid());

        context = new GameContext(scanner3, 3);

        psm.parse(context);
        System.out.println(testString3 + " is a valid move: " + context.wasMoveValid());

        context = new GameContext(scanner2, 4);

        psm.parse(context);
        System.out.println(testString4 + " is a valid move: " + context.wasMoveValid());
    }
}
