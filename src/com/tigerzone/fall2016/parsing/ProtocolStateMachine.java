package com.tigerzone.fall2016.parsing;

/**
 * Created by chausen on 11/19/16.
 */

public class ProtocolStateMachine {
    public void parse(Context context) {
        while (context.getState().parse(context)) { /* do nothing */ }
    }
}