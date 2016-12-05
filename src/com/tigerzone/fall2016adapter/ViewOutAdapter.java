package com.tigerzone.fall2016adapter;

import com.tigerzone.fall2016server.tournament.tournamentplayer.PlayerStats;
import javafx.collections.ObservableList;

/**
 * Created by chausen on 12/5/16.
 */
public interface ViewOutAdapter {
    void notifyEndOfTournament();
    void giveListOfPlayerStats(ObservableList<PlayerStats> listOfPlayerStats);
}
