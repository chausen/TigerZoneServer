package com.tigerzone.fall2016.parsing;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by chausen on 11/19/16.
 */
enum ProtocolStates implements ProtocolState {

    START("Entry point") {
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
            context.illegalMove();
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
            context.illegalMove();
            return false;
        }
    },
    UNPLACEABLE("Entry point to the three unplaceable tile options") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            if (scanner.hasNext()) {
                String token = scanner.next();
                if (token == "PASS") {
                    context.validMove();
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
                    context.validMove();
                    context.changeState(START, this);
                    return false;
                }
            }
            context.illegalMove();
            return false;
        }
    },
    TILECODE("{J,L,T}: must occur 4 times in a row followed by {-,B,C,D,P,X}: occurring just once") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            Pattern tileCodePattern = Pattern.compile("[J,L,T]{4}+[-,B,C,D,P,X]");
            if (scanner.hasNext(tileCodePattern)) {
                if (context.getPreviousState() == TILE && scanner.next().equals("UNPLACEABLE")) {
                    context.changeState(UNPLACEABLE, this);
                    return true;
                } else if (context.getPreviousState() == PLACE) {
                    context.changeState(POINT, this);
                    return true;
                }
            }
            context.illegalMove();
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
                        context.validMove();
                        context.changeState(START, this);
                        return false; // end iteration through state machine due to valid move
                    }
                    context.changeState(ORIENTATION, this);
                    return true;
                }
            }
            context.illegalMove();
            return false;
        }
    },
    ORIENTATION("{0,90,180,270}, Rotation in degrees counter-clockwise") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            Pattern tileCodePattern = Pattern.compile("[0, 90, 180, 270]");
            if (scanner.hasNext(tileCodePattern)) { // has valid orientation
                if (scanner.hasNext()) {
                    String token = scanner.next();
                    if (token == "NONE" || token == "CROCODILE") {
                        context.validMove();
                        context.changeState(START, this);
                        return false; // end iteration through state machine due to valie move
                    } else if (token == "TIGER") {
                        context.changeState(ZONE, this);
                        return true;
                    }
                    return true;
                }
            }
            context.illegalMove();
            return false;
        }
    };

    private final String description;

    ProtocolStates(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProtocolState: " + description;
    }
}
