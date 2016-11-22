package com.tigerzone.fall2016server.server;

import com.tigerzone.fall2016server.tournament.Game;

/**
 * Created by lenovo on 11/21/2016.
 */
public class Logger {

    private int challengeID;
    private int roundID;
    private int matchID;
    private int gameID;

    public Logger(Game game) {
        gameID = game.getGameID();
        matchID = game.getMatch().getMatchID();
        roundID = game.getMatch().getRound().getRoundID();
        challengeID = game.getMatch().getRound().getChallenge().getChallengeID();
    }


}
