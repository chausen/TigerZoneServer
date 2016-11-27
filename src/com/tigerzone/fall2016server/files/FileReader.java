package com.tigerzone.fall2016server.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by clayhausen on 11/26/16.
 */
public class FileReader {

    /**
     * Given a file containing a move on each line, will return the moves as a List.
     *
     * @param filename filename of the file containing the moves
     * @return List of moves
     */
    public static List<String> getMoves(String filename) {
        List<String> moves = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                moves.add(scanner.nextLine());
            }
        } catch (FileNotFoundException exc) {
            System.out.println("FATAL: " + filename + " not found.");
        }
        return moves;
    }

    /**
     * Given a file containing a credential on each line, where a credential is a login name and a password separated by a
     * space, will return the credentials as a hashmap
     *
     * @param filename filename of the file containing the credentials
     * @return HashMap of credentials; key=loginName, value=password
     */
    public static HashMap<String,String> getCredentials(String filename) {
        HashMap<String,String> credentials = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                String loginName = "";
                String password = "";
                if (lineScanner.hasNext()) {
                    loginName = lineScanner.next();
                }
                if (lineScanner.hasNext()) {
                    password = lineScanner.next();
                }
                credentials.put(loginName, password);
            }
        } catch (FileNotFoundException exc) {
            System.out.println("FATAL: " + filename + " not found.");
        }
        return credentials;
    }
}
