package com.tigerzone.fall2016server.server.protocols;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by chausen on 11/19/16.
 *
 * Each enum implements a parse method determines whether part of a String follows the Move Protocol.
 * A Context is passed along to each state and keeps track of the current state of the String traversal.
 * parse(Context context) will return false if the traversal should end (the current String is done being parsed) or
 * True otherwise. The boolean does not determine whether or not the Move was valid; the Context keeps track of this.
 *
 */
enum MoveProtocolStates implements ProtocolState {

    START("Entry point") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                String token = scanner.next();
                if (token.equals("GAME")) {
                    int gid = context.getGid();
                    if(scanner.hasNextInt()) {
                        if(scanner.nextInt() == gid) {
                            context.changeState(MOVE, this);
                            return true;
                        }
                    }
                }
            }
            context.setIllegalMove();
            return false;
        }
    },

    MOVE("Move number specified") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                String token = scanner.next();
                if (token.equals("MOVE")) {
                    if(scanner.hasNextInt()) {
                        if(context.compareMoveNum(scanner.nextInt())) {
                            context.changeState(GAME, this);
                            return true;
                        }
                    }
                }
            }
            context.setIllegalMove();
            return false;
        }
    },

    GAME("Entry point to game messages") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                String token = scanner.next();
                if (token.equals("TILE")) {
                    context.changeState(TILE, this);
                    return true;
                } else if (token.equals("PLACE")) {
                    context.changeState(PLACE, this);
                    return true;
                }
            }
            context.setIllegalMove();
            return false;
        }
    },

    TILE("Entry point to unplaceable tile") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                context.changeState(TILECODE, this);
                return true;
            }
            context.setIllegalMove();
            return false;
        }
    },
    UNPLACEABLE("Entry point to the three unplaceable tile options") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                String token = scanner.next();
                if (token.equals("PASS")) {
                    context.setValidMove();
                    context.changeState(START, this);
                    return false; // end iteration through state machine due to valid move
                } else {
                    Pattern addOrRetrieveTiger = Pattern.compile("(RETRIEVE TIGER AT)|(ADD ANOTHER TIGER TO)");
                    if (scanner.hasNext(addOrRetrieveTiger)) {
                        context.changeState(POINT, this);
                        return true;
                    }
                }
            }
            return false;
        }
    },
    PLACE("Entry point to placeable tile") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                context.changeState(TILECODE, this);
                return true;
            }
            context.setIllegalMove();
            return false;
        }
    },
    ZONE("An integer between 0 & 9") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNextInt()) {
                int zone = scanner.nextInt();
                if (0 <= zone && zone <= 9) {
                    context.setValidMove();
                    context.changeState(START, this);
                    return false;
                }
            }
            context.setIllegalMove();
            return false;
        }
    },
    TILECODE("{J,L,T}: must occur 4 times in a row followed by {-,B,C,D,P,X}: occurring just once") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            Pattern tileCodePattern = Pattern.compile("[J,L,T]{4}+[-,B,C,D,P,X]");
            if (scanner.hasNext(tileCodePattern)) {
                scanner.next(tileCodePattern); // move past the tile code after validating
                //String token = scanner.next();
                if (context.getPreviousState() == TILE && scanner.hasNext()) {
                    if (scanner.next().equals("UNPLACEABLE")) {
                        context.changeState(UNPLACEABLE, this);
                        return true;
                    }
                } else if (context.getPreviousState() == PLACE && scanner.hasNext()) {
                    if (scanner.next().equals("AT")) {
                        context.changeState(POINT, this);
                        return true;
                    }
                }
            }
            context.setIllegalMove();
            return false;
        }
    },
    POINT("Two successive integers representing a point") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNextInt()) {
                scanner.nextInt();
                if (scanner.hasNextInt()) {
                    scanner.nextInt();
                    if (context.getPreviousState() == UNPLACEABLE) {
                        context.setValidMove();
                        context.changeState(START, this);
                        return false; // end iteration through state machine due to valid move
                    } else {
                        context.changeState(ORIENTATION, this);
                        return true;
                    }
                }
            }
            context.setIllegalMove();
            return false;
        }
    },
    ORIENTATION("{0,90,180,270}, Rotation in degrees counter-clockwise") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNextInt()) {
                int orientation = scanner.nextInt();
                if (orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270) {
                    if (scanner.hasNext()) {
                        String token = scanner.next();
                    if (token.equals("NONE") || token.equals("CROCODILE")) {
                        context.setValidMove();
                        context.changeState(START, this);
                        return false; // end iteration through state machine due to valie move
                    } else if (token.equals("TIGER")) {
                        context.changeState(ZONE, this);
                        return true;
                    }
                    return true;
                }
                }
            }
            context.setIllegalMove();
            return false;
        }
    };

    private final String description;

    MoveProtocolStates(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProtocolState: " + description;
    }
}
