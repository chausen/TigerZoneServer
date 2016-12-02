package com.tigerzone.fall2016server.server.protocols;

import java.util.Scanner;

/**
 * Created by clayhausen on 11/19/16.
 *
 * To be used with a ProtocolStateMachine. Keeps track of the current state of the traversal of a String that is being
 * parsed. The String is being parsed to determine conformance with the Move Protocol. A ProtocolStateMachine will pass
 * the context from State to State, and when this recursion completes, the Context can be queried to determine whether
 * or not the move was valid.
 *
 */
public class MoveProtocolContext implements Context {
    private Scanner scanner;
    ProtocolState currentState;
    ProtocolState previousState;
    boolean validMove;
    int gid;
    int moveNumber = 1;

    public MoveProtocolContext(Scanner scanner, int gid) {
        this.scanner = scanner;
        this.currentState = MoveProtocolStates.START;
        this.previousState = null;
        this.validMove = false;
        this.gid = gid;
    }


    public MoveProtocolContext(int gid) {
        this.currentState = MoveProtocolStates.START;
        this.previousState = null;
        this.validMove = false;
        this.gid = gid;
    }


    public void setScanner(Scanner scanner){
        this.scanner = scanner;
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
    public void setValidMove() {
        validMove = true;
    }

    @Override
    public void setIllegalMove() {
        validMove = false;
    }

    @Override
    public boolean wasMoveValid() {
        return validMove;
    }

    @Override
    public int getGid() {
        return gid;
    }

    @Override
    public boolean compareMoveNum(int moveNumber) {
        if(moveNumber == this.moveNumber){
            this.moveNumber++;
            return true;
        }
        return false;
    }
}
