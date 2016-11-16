package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.area.*;

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

    // key = playerID, value = score
    private Map<String, Integer> playerScores;

    // Collaborators
    private AreaManager am;


    public Scorer(List<String> playerIDs, AreaManager am) {
        this.playerScores = new HashMap<>();
        for (String playerID: playerIDs) {
            playerScores.put(playerID, 0);
        }
        this.am = am;
    }

    public Scorer(Map<String, Integer> playerScores, AreaManager am) {
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
        Integer points = den.getSize();

        List<String> ownerIDs = den.getOwnerID();

        for(String id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
        }
    }

    /**
     * Scores a LakeArea
     * @param  lake  a lake feature representing connected lake tiles
     */
    public void score(LakeArea lake) {
        // points = 2 * (# of tiles) * (1 + # of unique animals)
        Integer points = 2 * lake.getSize() * (1 + lake.getNumOfUniquePreyAnimalsAfterCrocodileEffect());

        List<String> ownerIDs = lake.getOwnerID();

        for(String id: ownerIDs) {
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
        Integer points = trail.getSize() + trail.getNumOfPreyAfterCrocodileEffect();

        List<String> ownerIDs = trail.getOwnerID();

        for(String id: ownerIDs) {
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

        List<JungleArea> jungles = am.getJungleAreas();
        endGameScoreJungles(jungles);
    }

    //========== End Game Scoring Helper Methods ===========//
    // Score incomplete dens
    private void endGameScoreDens(List<DenArea> dens) {
        for (DenArea den: dens) {
            if ( !den.isComplete() ) {
                score(den);
            }
        }
    }

    // Score incomplete lakes
    private void endGameScoreLakes(List<LakeArea> lakes) {
        for (LakeArea lake: lakes) {
            if ( !lake.isComplete() ) {
                // points = (# of tiles) * (# of unique animals)
                Integer points = lake.getSize() * lake.getNumOfUniquePreyAnimalsAfterCrocodileEffect();

                List<String> ownerIDs = lake.getOwnerID();

                for (String id : ownerIDs) {
                    Integer currentScore = playerScores.get(id);
                    playerScores.put(id, currentScore + points);
                }
            }
        }
    }

    // Score incomplete trails
    private void endGameScoreTrails(List<TrailArea> trails) {
        for (TrailArea trail: trails) {
            if ( !trail.isComplete() ) {
                score(trail);
            }
        }
    }

    /**
     * Scores all jungle areas of the game and adds those points to owners to those players
     * @param jungles
     */
    private void endGameScoreJungles(List<JungleArea> jungles) {
        int completedLakeValue = 3;
        int completedDenValue = 5;
        for(JungleArea jungle : jungles){
            List<String> ownerIDS = jungle.getOwnerID();

            //pointers = (completedLakeValue * num of completed lakes) + (completedDenValue * num of completed dens)
            Integer points = (completedLakeValue * jungle.countCompletedLakes())
                              + (completedDenValue * jungle.countCompletedDens());

            for(String id: ownerIDS){
                Integer currentScore = playerScores.get(id);
                playerScores.put(id, currentScore + points);
            }
        }
    }

    /**
     * Returns a set of playerIDs for the Players with the highest score.
     *
     * @return  Set  a set of playerIDs.
     */
    public Set<String> announceWinners() {

        Set<String> winners = new HashSet<>();

        Set<String> playerIDs = playerScores.keySet();

        // Find the highest score
        Integer highestScore = 0;

        Iterator<String> iterator = playerIDs.iterator();
        while(iterator.hasNext()) {
            String currentplayerID = iterator.next();
            Integer currentScore = playerScores.get(currentplayerID);
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
        }

        // Add each player with a score equal to the highest one found to the set of winners
        iterator = playerIDs.iterator(); // reset iterator
        while(iterator.hasNext()) {
            String currentplayerID = iterator.next();
            Integer currentScore = playerScores.get(currentplayerID);
            if (currentScore == highestScore) {
                winners.add(currentplayerID);
            }
        }

        return winners;
    }

    /**
     * Returns a player's score given their playerID.
     *
     * @param playerID
     * @return  int  playerID's score. 0 if playerID does not exist
     */
    public int getScore(String playerID) {
        if (playerScores.containsKey(playerID)) {
            return playerScores.get(playerID);    
        } else {
            return 0;
        }
    }

}