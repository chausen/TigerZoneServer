package com.tigerzone.fall2016adapter;
import com.tigerzone.fall2016.area.DenArea;
import com.tigerzone.fall2016.area.JungleArea;
import com.tigerzone.fall2016.area.LakeArea;
import com.tigerzone.fall2016.area.TrailArea;
import com.tigerzone.fall2016.gamesystem.Player;

import java.util.Map;

public interface PlayerOutAdapter
{
    //public void promptForTurn(PlayableTile currentTile);
    public void receiveTurn(String s);
    public void successfulTurn();
    public void reportScoringEvent(Map<Player,Integer> playerScores, JungleArea ja);
    public void reportScoringEvent(Map<Player,Integer> playerScores, DenArea da);
    public void reportScoringEvent(Map<Player,Integer> playerScores, LakeArea la);
    public void reportScoringEvent(Map<Player,Integer> playerScores, TrailArea ta);
    public void forfeitInvalidMeeple(String currentPlayerID);
    public void forfeitIllegalMeeple(String currentPlayerID);
    public void forfeitIllegalTile(String currentPlayerID);
    //public void notifyEndGame(Map<Player,Integer> playerScores);
    public void notifyEndGame(int score1, int score2);
    public int getFinalScore(String playerId);
}