package com.tigerzone.fall2016server.tournament;

import com.tigerzone.fall2016.tileplacement.tile.PlayableTile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aidan on 11/19/2016.
 */
public class RoundRobinTest {

    private int round;

    /**
     * Either pass in an incremented round each time or increment the round in this class and call it
     * the amount of times based on the rounds you need. If you have odd amount of players iterate with
     * this method n times. If you have even number of players iterate through n-1 times
     *
     * @param playerList
     * @param round
     * @param tileStack
     * @return
     */
    public static List<Match> listMatches(List<TournamentPlayer> playerList, int round, LinkedList<PlayableTile> tileStack) {

        List<Match> matchList = new ArrayList<>();

        TournamentPlayer dummyPlayer = new TournamentPlayer("ByePlayer");

        boolean isOddNumOfPlayers = playerList.size() % 2 != 0 ? true : false;

        if (isOddNumOfPlayers) {
            //TODO: Make this player be a dummy player that has different behavior when played against
            // If odd number of players add a dummy Player
            playerList.add(dummyPlayer);
        }

        int halfSize = playerList.size() / 2;

        List<TournamentPlayer> players = new ArrayList<>();

        players.addAll(playerList); // Add players to List and remove the first player
        players.remove(0);

        int playersSize = players.size();

        int playerIdx = round % playersSize;

        matchList.add(new Match(playerList.get(0), players.get(playerIdx), tileStack));

        for (int idx = 1; idx < halfSize; idx++)
        {
            int firstTeam = (round + idx) % playersSize;
            int secondTeam = (round  + playersSize - idx) % playersSize;
            matchList.add(new Match(players.get(firstTeam), players.get(secondTeam), tileStack));
        }

        if(isOddNumOfPlayers) {
            playerList.remove(dummyPlayer);
        }
        return matchList;

    }
}
