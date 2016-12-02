package com.tigerzone.fall2016server.server.protocols;

/**
 * Created by chausen on 11/19/16.
 */

/**
 * To be used with a Context to parse a String and determine whether it follows a protocol
 */
public class ProtocolStateMachine {
    public void parse(Context context) {
        while (context.getState().parse(context)) { /* do nothing */ }
    }
}