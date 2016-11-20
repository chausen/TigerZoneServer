package com.tigerzone.fall2016.parsing;

import java.util.Scanner;

import static com.tigerzone.fall2016.parsing.ProtocolStates.START;

/**
 * Created by clayhausen on 11/19/16.
 */
public class MatchContext implements Context {
    private Scanner scanner;
    ProtocolState currentState;
    ProtocolState previousState;
    boolean validMove;

    public MatchContext(Scanner scanner) {
        this.scanner = scanner;
        this.currentState = START;
        this.previousState = null;
        this.validMove = false;
    }

    @Override
    public Scanner getScanner() {
        return this.scanner;
    }

    @Override
    public void changeState(ProtocolState nextState, ProtocolState previousState) {
        this.currentState = nextState;
        this.previousState = previousState;
    }

    @Override
    public ProtocolState getState() {
        return currentState;
    }

    @Override
    public ProtocolState getPreviousState() {
        return previousState;
    }

    @Override
    public void validMove() {
        validMove = true;
    }

    @Override
    public void illegalMove() {
        validMove = false;
    }

    @Override
    public boolean wasMoveValid() {
        return validMove;
    }
}
