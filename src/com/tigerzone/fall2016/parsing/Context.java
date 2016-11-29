package com.tigerzone.fall2016.parsing;

import com.tigerzone.fall2016.gamesystem.Turn;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by chausen on 11/19/16.
 */
public interface Context {
    Scanner getScanner();
    void setScanner(Scanner scanner);
    void changeState(ProtocolState nextState, ProtocolState previousState);
    ProtocolState getState();
    ProtocolState getPreviousState();
    void setValidMove();
    void setIllegalMove(); // call to indicate an illegal move was made
    boolean wasMoveValid(); // query whether or not an illegal move was made
    int getGid(); //every game context is associated with its Game's id
    boolean compareMoveNum(int moveNumber); //compare to the MoveNum and return true or false if it is the right one
}


