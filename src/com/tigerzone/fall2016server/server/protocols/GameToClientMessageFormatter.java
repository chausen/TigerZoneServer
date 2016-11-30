package com.tigerzone.fall2016server.server.protocols;

import java.util.Arrays;

/**
 * Created by lenovo on 11/21/2016.
 */
public class GameToClientMessageFormatter {
    public static String generateConfirmationMessageToBothPlayers(int gameID, int moveNum, String playerID, String message){
        StringBuffer stringBuffer = generateMessagePrefix(gameID, moveNum);

        // Remove GAME <gid> MOVE <#> from the message we received from the player
        message = message.substring(stringBuffer.length());
        // Change PLACE to PLACED, if tile was placeable
        if (message.contains("PLACE ")) {
            StringBuffer insertTheD = new StringBuffer();
            insertTheD.append(message);
            insertTheD.insert(6, 'D');
            message = insertTheD.toString();
        }

        stringBuffer.append(" PLAYER ");
        stringBuffer.append(playerID);
        stringBuffer.append(message);
        return stringBuffer.toString();
    }

    public static String generateForfeitMessageToBothPlayers(int gameID, int moveNum, String playerID, String message){
        StringBuffer stringBuffer = generateMessagePrefix(gameID, moveNum);
        stringBuffer.append(" PLAYER ");
        stringBuffer.append(playerID);
        stringBuffer.append(" ");
        stringBuffer.append(message);
        return stringBuffer.toString();
    }

    public static String generateMessageToActivePlayer(int gameID, int maxTime, int moveNum, String tile){
        String secondOrSeconds = (maxTime == 1) ? " SECOND" : " SECONDS";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("MAKE YOUR MOVE IN GAME ");
        stringBuffer.append(gameID);
        stringBuffer.append(" WITHIN ");
        stringBuffer.append(maxTime);
        stringBuffer.append(secondOrSeconds);
        stringBuffer.append(": MOVE ");
        stringBuffer.append(moveNum);
        stringBuffer.append(" PLACE ");
        stringBuffer.append(tile);
        return stringBuffer.toString();
    }

    private static StringBuffer generateMessagePrefix(int gameID, int moveNum) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("GAME ");
        stringBuffer.append(gameID);
        stringBuffer.append(" MOVE ");
        stringBuffer.append(moveNum);

        return stringBuffer;
    }
}
