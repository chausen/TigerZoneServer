package com.tigerzone.fall2016server;

import com.sun.java_cup.internal.runtime.Scanner;
import com.tigerzone.fall2016.adapters.PlayerOutAdapter;
import com.tigerzone.fall2016.gamesystem.Player;

/**
 * Created by clayhausen on 11/16/16.
 */
public class CmdPromptInterface {
    private PlayerOutAdapter outAdapter;
    Scanner scanner;

    CmdPromptInterface(PlayerOutAdapter outAdapter) {
        this.outAdapter = outAdapter;
    }

    CmdPromptInterface(PlayerOutAdapter outAdapter, Scanner scanner) {
        this.outAdapter = outAdapter;
        this.scanner = scanner;
    }


}
