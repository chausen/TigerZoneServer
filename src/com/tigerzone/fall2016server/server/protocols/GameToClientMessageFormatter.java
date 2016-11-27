package com.tigerzone.fall2016server.server.protocols;

/**
 * Created by lenovo on 11/21/2016.
 */
public class GameToClientMessageFormatter {
    public static String generateMessageToBothPlayers(int gameID, int moveNum, String playerID, String message){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("GAME ");
        stringBuffer.append(gameID);
        stringBuffer.append(" MOVE ");
        stringBuffer.append(moveNum);
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
}
