package com.tigerzone.fall2016.parsing;

import com.tigerzone.fall2016.gamesystem.Turn;

import java.util.Scanner;

/**
 * Created by chausen on 11/19/16.
 */
public interface Context {
    Scanner getScanner();
    void changeState(ProtocolState nextState, ProtocolState previousState);
    ProtocolState getState();
    ProtocolState getPreviousState();
    Turn getTurn();
    void setTileString(String tileString);
    void illegalMove(); // call to indicate an illegal move was made
    boolean wasMoveIllegal(); // query whether or not an illegal move was made
}
