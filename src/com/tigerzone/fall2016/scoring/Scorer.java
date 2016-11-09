package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.Area.*;

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
     * Scores a DenArea
     * @param DenArea
     */
    public void score(DenArea den) {
        // The point value equals the number of tiles in the area
        Integer points = den.size();

        List<Integer> ownerIDs = den.getOwnerIDs();

        for(int id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
        }
    }

    /**
     * Scores a LakeArea
     * @param LakeArea
     */
    public void score(LakeArea lake) {
        // points = 2 * (# of tiles) * (# of unique animals)
        Integer points = lake.size() * uniqueAnimalCount(lake);

        List<Integer> ownerIDs = lake.getOwnerIDs();

        for(int id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
        }
    }

    /**
     * Scores a TrailArea
     * @param TrailArea
     */
    public void score(TrailArea trail) {
        // points = (# of tiles) + (# of unique animals)
        Integer points = trail.size() + uniqueAnimalCount(trail);

        List<Integer> ownerIDs = trail.getOwnerIDs();

        for(int id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
        }
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

    //========== End Game Scoring Helper Methods ===========//
    // Score incomplete dens
    private void endGameScoreDens(List<DenArea> dens) {

    }

    // Score incomplete lakes
    private void endGameScoreLakes(List<LakeArea> lakes) {

    }

    // Score incomplete trails
    private void endGameScoreTrails(List<TrailArea> trails) {

    }

    // Score jungles
    private void endGameScoreJungles(List<JungleArea> jungles, List<LakeArea> lakes, List<DenArea> dens) {

    }


    //========== Other Helper Methods ===========//
    /**
     *
     */
    private int uniqueAnimalCount(Area area) {
        //TODO add logic for unique animal multiplier
        return 1;
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

        Iterator<Integer> iterator = playerIDs.iterator();
        while(iterator.hasNext()) {
            Integer currentPlayerID = iterator.next();
            Integer currentScore = playerScores.get(currentPlayerID);
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
        }

        // Add each player with a score equal to the highest one found to the set of winners
        iterator = playerIDs.iterator(); // reset iterator
        while(iterator.hasNext()) {
            Integer currentPlayerID = iterator.next();
            Integer currentScore = playerScores.get(currentPlayerID);
            if (currentScore == highestScore) {
                winners.add(currentPlayerID);
            }
        }

        return winners;
    }

}