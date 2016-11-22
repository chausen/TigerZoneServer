package com.tigerzone.fall2016.parsing;

import java.util.Scanner;

/**
 * Created by clayhausen on 11/19/16.
 */
public class testing {
    public static void main(String[] args) {
        ProtocolStateMachine psm = new ProtocolStateMachine();

        String testString1 = "PLACE TLTTP AT 4 -1 90 NONE";
        String testString2 = "PLACE TD AT 4 100 30 NONE";

        Scanner scanner1 = new Scanner(testString1);
        Scanner scanner2 = new Scanner(testString2);

        Context context = new GameContext(scanner1, 1);
        psm.parse(context);
        System.out.println(testString1 + " is a valid move: " + context.wasMoveValid());

        context = new GameContext(scanner2, 1);

        psm.parse(context);
        System.out.println(testString2 + " is a valid move: " + context.wasMoveValid());
    }
}
