package com.tigerzone.fall2016server.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by clayhausen on 12/1/16.
 */
public class LogParser {
        public static void main(String args[]) {

            String filename = "";
            int roundID = 0;
            int gameID  = 0;
            String player1 = "";
            String player2 = "";
            int lineBreakCount = 0;

            if (args.length != 5) {
                System.out.println("Proper usage: <logFileName> <roundID> <gameID> <player1> <player2>");
            } else {
                filename = args[0];
                roundID = Integer.parseInt(args[1]);
                gameID = Integer.parseInt(args[2]);
                player1 = args[3];
                player2 = args[4];
            }

            String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
            StringBuilder sb = new StringBuilder();
            sb.append(currentDirectory);
            sb.append(filename);
            String fullFilePath = sb.toString();

            try {
                Scanner fs = new Scanner(new File(fullFilePath));
                while (fs.hasNextLine()) {
                    String line = fs.nextLine().replace("\t", " ").trim();
                    String[] testString = line.split(" ");
                    if (testString.length > 8 && !testString[7].equals("---")) {
                        Scanner ls = new Scanner(line);
                        ls.next(); // date
                        ls.next(); // timestamp
                        ls.next(); // tournamentID
                        ls.next(); // challengeID
                        int parsedRoundID = ls.nextInt(); // roundID
                        ls.next(); // match
                        int parsedGameID = ls.nextInt(); // gameID
                        String parsedPlayer = ls.next();

                        if ( parsedRoundID == roundID && parsedGameID == gameID && (parsedPlayer.equals(player1) || (parsedPlayer.equals(player2))) ) {
                            System.out.println(line);
                            if (lineBreakCount == 2) {
                                System.out.println();
                                lineBreakCount = -1;
                            }
                            lineBreakCount++;
                        }

                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(fullFilePath + ": Not found");
            }
        }
}


