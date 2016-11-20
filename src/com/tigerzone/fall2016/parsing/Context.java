package com.tigerzone.fall2016.parsing;

import com.tigerzone.fall2016.gamesystem.Turn;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by chausen on 11/19/16.
 */
public interface Context {
    Scanner getScanner();
    void changeState(ProtocolState nextState, ProtocolState previousState);
    ProtocolState getState();
    ProtocolState getPreviousState();
    void validMove();
    void illegalMove(); // call to indicate an illegal move was made
    boolean wasMoveValid(); // query whether or not an illegal move was made
}


