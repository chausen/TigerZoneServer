package com.tigerzone.fall2016server.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by clayhausen on 11/26/16.
 */
public class MoveReader {

    private Scanner scanner;

    List<String> getMoves(String filename) {
        List<String> moves = new ArrayList<>();
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException exc) {
            System.out.println("FATAL: " + filename + " not found.");
        }
        while (scanner.hasNext()) {
            moves.add(scanner.next());
        }
        return moves;
    }
}
