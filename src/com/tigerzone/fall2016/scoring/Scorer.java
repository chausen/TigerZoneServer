package com.tigerzone.fall2016.scoring;

import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.animals.Tiger;
import com.tigerzone.fall2016.area.*;
import com.tigerzone.fall2016.gamesystem.Player;

import java.util.*;

/**
 * Maintains scores for each TournamentPlayer playing TigerZone. Responsibilities include:
 * calculating point value for a completed area and providing that value,
 * scoring the end of a game by calculating point value for incomplete areas and jungles,
 * determining who points are awarded to for a given area, based ownership,
 * providing the TournamentPlayer(s) with the highest score
 *
 * @author Clay Hausen
 */
public class Scorer {

    // key = player, value = score
    private Map<Player, Integer> playerScores;

    // Collaborators
    private PlayerOutAdapter outAdapter;

    public Scorer(List<Player> players) {
        this.playerScores = new HashMap<>();

        for (Player player : players) {
            playerScores.put(player, new Integer(0));
        }
    }

    public Scorer(List<Player> players, PlayerOutAdapter outAdapter) {
        this.playerScores = new HashMap<>();

        for (Player player : players) {
            playerScores.put(player, new Integer(0));
        }
        this.outAdapter = outAdapter;
    }

    /**
     * Returns tigers back to owners after scoring completed areas!
     * @param tigerList
     */
//    private void returnTigerToOwnerAfterScoring(List<Tiger> tigerList){
//        for(Tiger tiger : tigerList){
//            Player tigerOwner = tiger.getOwner();
//            tigerOwner.incrementGoodSupply();
//        }
//    }

    private void returnTigerToOwnerAfterScoring(Set<Tiger> tigerList){
        for(Tiger tiger : tigerList){
            Player tigerOwner = tiger.getOwner();
            tigerOwner.incrementGoodSupply();
        }
    }

    /**
     * Updates TournamentPlayer scores and returns a scoringEvent
     * @param players
     * @param points
     * @return
     */
    private Map<Player, Integer> updatePlayersScore(List<Player> players, Integer points){
        Map<Player, Integer> scoringEvent = new HashMap<>();

        for(Player player: players) {
            Integer currentScore = this.playerScores.get(player);
            this.playerScores.put(player, currentScore + points);
            scoringEvent.put(player, points);
        }
        return scoringEvent;
    }


    //==================== During Game Scoring ====================//
    /**
     * Scores a DenArea
     * @param  den  a den feature representing connected den tiles
     */
    public void score(DenArea den) {
        // The point value equals the number of tiles in the area
        Integer points = den.getSize();
        List<Player> owners = den.getOwner();

        Map<Player, Integer> scoringEvent = updatePlayersScore(owners, points);

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(den.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent, den);
    }

    /**
     * Scores a LakeArea
     * @param  lake  a lake feature representing connected lake tiles
     */
    public void score(LakeArea lake) {
        // points = 2 * (# of tiles) * (1 + # of unique animals)
        Integer points = 2 * lake.getSize() * (1 + lake.getNumOfUniquePreyAnimalsAfterCrocodileEffect());
        List<Player> owners = lake.getOwner();

        Map<Player, Integer> scoringEvent = updatePlayersScore(owners, points);

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(lake.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent, lake);
    }

    /**
     * Scores a TrailArea
     *
     * @param  trail  a trail feature representing connected trail tiles
     */
    public void score(TrailArea trail) {
        // points = (# of tiles) + (# of unique animals)
        Integer points = trail.getSize() + trail.getNumOfPreyAfterCrocodileEffect();

        List<Player> owners = trail.getOwner();

        Map<Player, Integer> scoringEvent = updatePlayersScore(owners, points);

        //return tigers back to player after an area is completed
        returnTigerToOwnerAfterScoring(trail.getTigerList());

        outAdapter.reportScoringEvent(scoringEvent,trail);
    }


    //==================== End Game Scoring ====================//

    /**
     * Initiates end of game scoring. Will update player scores for:
     * 1) incomplete dens
     * 2) incomplete lakes
     * 3) incomplete trails
     * 4) jungles
     */
    public void endGameScoring(AreaManager am) {
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
            if ( !den.isComplete() && den.hasOwner()) {
                score(den);
            }
        }
    }

    // Score incomplete lakes
    private void endGameScoreLakes(Set<LakeArea> lakes) {
        for (LakeArea lake: lakes) {
            if ( !lake.isComplete() && lake.hasOwner()) {
                // points = (# of tiles) * (# of unique animals)
                Integer points = lake.getSize() * (1 + lake.getNumOfUniquePreyAnimalsAfterCrocodileEffect());

                List<Player> owners = lake.getOwner();

                updatePlayersScore(owners, points);

//                //// TODO: 11/27/2016 added this, maybe need to remove
             //   Map<Player, Integer> scoringEvent = updatePlayersScore(owners, points);
//                outAdapter.reportScoringEvent(scoringEvent, lake);

            }
        }
    }

    // Score incomplete trails
    private void endGameScoreTrails(Set<TrailArea> trails) {
        for (TrailArea trail: trails) {
            if ( !trail.isComplete() && trail.hasOwner()) {
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
        Map<Player,Integer> scoringEvent;

        for(JungleArea jungle : jungles){
            if(jungle.hasOwner()) {
                List<Player> owners = jungle.getOwner();

                //pointers = (completedLakeValue * num of completed lakes) + (completedDenValue * num of completed dens)
                Integer points = (completedLakeValue * jungle.countCompletedLakes())
                        + (completedDenValue * jungle.countCompletedDens());

                scoringEvent = updatePlayersScore(owners, points);
                outAdapter.reportScoringEvent(scoringEvent, jungle);
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

        Set<Player> players = playerScores.keySet();

        // Find the highest score
        Integer highestScore = 0;

        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()) {
            Player currentPlayer = iterator.next();
            Integer currentScore = playerScores.get(currentPlayer);
            if (currentScore > highestScore) {
                highestScore = currentScore;
            }
        }

        // Add each player with a score equal to the highest one found to the set of winners
        iterator = players.iterator(); // reset iterator
        while(iterator.hasNext()) {
            Player currentPlayer = iterator.next();
            Integer currentScore = playerScores.get(currentPlayer);
            if (currentScore == highestScore) {
                winners.add(currentPlayer.getPlayerId());
            }
        }
        return winners;
    }

    /**
     * Returns a player's score given their playerID.
     *
     * @param player
     * @return  int  player's score. 0 if playerID does not exist
     */
    public int getScore(Player player) {
        if (playerScores.containsKey(player)) {
            return playerScores.get(player);
        } else {
            return 0;
        }
    }

    public void setPlayerScores(Map<Player, Integer> playerScores){
        this.playerScores = playerScores;
    }

    public Map<Player, Integer> getPlayerScores(){
        return this.playerScores;
    }
}