package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.Area.AreaManager;

import java.util.*;

public class Scorer {

    // key = playerId, value = score
    private Map<Integer, Integer> playerScores;

    // Collaborators
    private AreaManager am;


    public Scorer(Map<Integer, Integer> playerScores, AreaManager am) {
        this.playerScores = playerScores;
        this.am = am;
    }


    //==================== During Game Scoring ====================//
    /**
     * Scores an incomplete DenArea
     * @param DenArea
     */
    public void score(DenArea den) {
        Integer points = den.size();
        Integer ownerID = den.getOwnerID();
        Integer currentScore = playerScores.get(ownerID);
        playerScores.put(ownerID, currentScore + points);
    }

    /**
     * Scores an incomplete LakeArea
     * @param LakeArea
     */
    public void score(LakeArea lake) {
        Integer points = den.size();
        Integer ownerID = den.getOwnerID();
        Integer currentScore = playerScores.get(ownerID);
        playerScores.put(ownerID, currentScore + points);
    }

    /**
     * Scores an incomplete TrailArea
     * @param TrailArea
     */
    public void score(TrailArea trail) {

    }


    //==================== End Game Scoring ====================//

    /**
     * Initiates end of game scoring. Will update player scores for:
     * 1) incomplete dens
     * 2) incomplete lakes
     * 3) incomplete trails
     * 4) jungles
     */
    public void endGameScoring() {

    }

    //========== Helper Methods ===========//
    // Score incomplete dens
    private void endGameScore(List<DenArea> dens) {

    }

    // Score incomplete lakes
    private void endGameScore(List<LakeArea> lakes) {

    }

    // Score incomplete trails
    private void endGameScore(List<TrailArea> trails) {

    }

    // Score jungles
    private void endGameScore(List<JungleArea> jungles, List<LakeArea> lakes, List<DenArea> dens) {

    }

    /**
     * Returns a set of playerIDs for the Players with the highest score
     * @return Set<Integer>: a set of playerIDs.
     */
    public Set<Integer> announceWinners() {

        Set<Integer> winners = new HashSet<Integer>();

        Set<Integer> playerIDs = playerScores.keySet();

        // Find the highest score
        Integer highestScore = 0;
        Integer currentPlayerID;
        Integer currentScore;

        Iterator<Integer> iterator = playerIDs.iterator();
        while(iterator.hasNext()) {
            currentPlayerID = iterator.next();
            currentScore = playerScores.get(currentPlayerID);
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
        }

        // Add each player with a score equal to the highest one found to the set of winners
        iterator = playerIDs.iterator(); // reset iterator
        while(iterator.hasNext()) {
            currentPlayerID = iterator.next();
            currentScore = playerScores.get(currentPlayerID);
            if (currentScore == highestScore) {
                winners.add(currentPlayerID);
            }
        }

        return winners;
    }

}