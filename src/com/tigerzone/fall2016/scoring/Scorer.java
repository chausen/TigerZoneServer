package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.*;
import com.tigerzone.fall2016.gamesystem.Player;

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
    //key = playerID, value = player
    private Map<String, Player> players;

    // Collaborators
    private AreaManager am;
    private PlayerOutAdapter outAdapter;

    public Scorer(List<Player> players, AreaManager am) {
        this.playerScores = new HashMap<>();
        for (Player player : players) {
            String playerID = player.getPlayerId();
            this.players.put(playerID, player);
            playerScores.put(playerID, 0);
        }
        this.am = am;
    }

    public Scorer(Map<String, Integer> playerScores, AreaManager am, PlayerOutAdapter outAdapter) {
        this.playerScores = playerScores;
        this.am = am;
        this.outAdapter = outAdapter;
    }

    /**
     * Returns tigers back to owners after scoring completed areas!
     * @param tigerList
     */
    private void returnTigerToOwnerAfterScoring(List<Tiger> tigerList){
        for(Tiger tiger : tigerList){
            Player tigerOwner = players.get(tiger.getPlayerId());
            tigerOwner.incrementGoodSupply();
        }
    }


    //==================== During Game Scoring ====================//
    /**
     * Scores a DenArea
     * @param  den  a den feature representing connected den tiles
     */
    public void score(DenArea den) {
        // The point value equals the number of tiles in the area
        Integer points = den.getSize();
        HashMap<String, Integer> scoringEvent = new HashMap<>();

        List<String> ownerIDs = den.getOwnerID();

        for(String id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
            scoringEvent.put(id, points);
        }

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(den.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent);
    }

    /**
     * Scores a LakeArea
     * @param  lake  a lake feature representing connected lake tiles
     */
    public void score(LakeArea lake) {
        // points = 2 * (# of tiles) * (1 + # of unique animals)
        Integer points = 2 * lake.getSize() * (1 + lake.getNumOfUniquePreyAnimalsAfterCrocodileEffect());
        HashMap<String, Integer> scoringEvent = new HashMap<>();

        List<String> ownerIDs = lake.getOwnerID();

        for(String id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
            scoringEvent.put(id, points);
        }

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(lake.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent);
    }

    /**
     * Scores a TrailArea
     *
     * @param  trail  a trail feature representing connected trail tiles
     */
    public void score(TrailArea trail) {
        // points = (# of tiles) + (# of unique animals)
        Integer points = trail.getSize() + trail.getNumOfPreyAfterCrocodileEffect();
        HashMap<String, Integer> scoringEvent = new HashMap<>();

        List<String> ownerIDs = trail.getOwnerID();

        for(String id: ownerIDs) {
            Integer currentScore = playerScores.get(id);
            playerScores.put(id, currentScore + points);
            scoringEvent.put(id, points);
        }

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(trail.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent);
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
        Set<DenArea> dens = am.getDenAreas();
        endGameScoreDens(dens);

        Set<LakeArea> lakes = am.getLakeAreas();
        endGameScoreLakes(lakes);

        Set<TrailArea> trails = am.getTrailAreas();
        endGameScoreTrails(trails);

        Set<JungleArea> jungles = am.getJungleAreas();
        endGameScoreJungles(jungles);
    }

    //========== End Game Scoring Helper Methods ===========//
    // Score incomplete dens
    private void endGameScoreDens(Set<DenArea> dens) {
        for (DenArea den: dens) {
            if ( !den.isComplete() ) {
                score(den);
            }
        }
    }

    // Score incomplete lakes
    private void endGameScoreLakes(Set<LakeArea> lakes) {
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
    private void endGameScoreTrails(Set<TrailArea> trails) {
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
    private void endGameScoreJungles(Set<JungleArea> jungles) {
        int completedLakeValue = 3;
        int completedDenValue = 5;
        HashMap<String,Integer> scoringEvent = new HashMap<>();

        for(JungleArea jungle : jungles){
            List<String> ownerIDS = jungle.getOwnerID();

            //pointers = (completedLakeValue * num of completed lakes) + (completedDenValue * num of completed dens)
            Integer points = (completedLakeValue * jungle.countCompletedLakes())
                              + (completedDenValue * jungle.countCompletedDens());

            for(String id: ownerIDS){
                Integer currentScore = playerScores.get(id);
                playerScores.put(id, currentScore + points);
                scoringEvent.put(id, points);
            }
            outAdapter.reportScoringEvent(scoringEvent);
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

    public Map<String, Integer> getPlayerScores(){
        return playerScores;
    }
}