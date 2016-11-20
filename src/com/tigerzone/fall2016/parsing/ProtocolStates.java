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
            return false;
        }
    },
    PASS("Pass turn") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    RETREIVETIGER("Retrieve a tiger from x,y") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    ADDTIGER("Add another tiger to x,y") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },

    PLACE("Entry point to placeable tile") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    NONE("No predator") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    CROCODILE("Place crocodile") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    TIGER("Place tiger") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    ZONE("tile zone (1-9) to place tiger on") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },
    TILECODE("{J,L,T}: must occur 4 times in a row followed by {-,B,C,D,P,X}: occurring just once") {
        @Override
        public boolean parse(Context context) {
            Scanner scanner = context.getScanner();
            Pattern tileCodePattern = Pattern.compile("[J,L,T]{4}+[-,B,C,D,P,X]");
            if (scanner.hasNext(tileCodePattern)) {
                context.setTileString(scanner.next());
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
                int x = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    int y = scanner.nextInt();
                }
            }
            return false;
        }
    },
    ORIENTATION("{0,90,180,270}, Rotation in degrees counter-clockwise") {
        @Override
        public boolean parse(Context context) {
            return false;
        }
    },

    FORFEIT("An alternate path for every state: invalid message trigger forfeit") {
        @Override
        public boolean parse(Context context) {
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
