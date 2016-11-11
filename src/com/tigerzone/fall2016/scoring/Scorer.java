package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.Area.*;

import java.util.*;

/**
 * Maintains scores for each Player playing TigerZone. Responsibilities include:
 * calculating point value for a completed area and providing that value,
 * scoring the end of a game by calculating point value for incomplete areas and jungles,
 * determining who points are awarded to for a given area, based ownership,
 * providing the Player(s) with the highest score
 *
 * @author Clay Hausen
 */
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
     * @param  den  a den feature representing connected den tiles
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
     * @param  lake  a lake feature representing connected lake tiles
     */
    public void score(LakeArea lake) {
        // points = 2 * (# of tiles) * (# of unique animals)
        Integer points = 2 * lake.size() * uniqueAnimalCount(lake);

        List<Integer> ownerIDs = lake.getOwnerIDs();

        for(int id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
        }
    }

    /**
     * Scores a TrailArea
     *
     * @param  trail  a trail feature representing connected trail tiles
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
        List<DenArea> dens = am.getDenAreas();
        endGameScoreDens(dens);

        List<LakeArea> lakes = am.getLakeAreas();
        endGameScoreLakes(lakes);

        List<TrailArea> trails = am.getTrailAreas();
        endGameScoreTrails(trails);

        List<JungleArea> jungles = am.getDenAreas();
        List<LakeArea> completedLakes = am.getCompletedLakeAreas();
        List<DenArea> completedDens = am.getCompletedDenAreas();
        endGameScoreJungles(jungles, completedLakes, completedDens);
    }

    //========== End Game Scoring Helper Methods ===========//
    // Score incomplete dens
    private void endGameScoreDens(List<DenArea> dens) {
        for (DenArea den: dens) {
            score(den);
        }
    }

    // Score incomplete lakes
    private void endGameScoreLakes(List<LakeArea> lakes) {
        for (LakeArea lake: lakes) {
            // points = (# of tiles) * (# of unique animals)
            Integer points = lake.size() * uniqueAnimalCount(lake);

            List<Integer> ownerIDs = lake.getOwnerIDs();

            for(int id: ownerIDs) {
                Integer currentScore = playerScores.get(id);
                playerScores.put(id, currentScore + points);
            }
        }
    }

    // Score incomplete trails
    private void endGameScoreTrails(List<TrailArea> trails) {
        for (TrailArea trail: trails) {
            score(trail);
        }
    }

    // Score jungles
    private void endGameScoreJungles(List<JungleArea> jungles, List<LakeArea> lakes, List<DenArea> dens) {

    }


    //========== Helper Methods ===========//
    /**
     *
     */
    private int uniqueAnimalCount(Area area) {
        //TODO add logic for unique animal multiplier
        
        return 1;
    }



    /**
     * Returns a set of playerIDs for the Players with the highest score
     *
     * @return  Set  a set of playerIDs.
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