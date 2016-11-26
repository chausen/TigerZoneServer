#!/bin/bash
java ParameterizedClient PLAYER1 PASSWORD1 Player1Moves.txt Player2Moves.txt localhost 4444
java ParameterizedClient PLAYER2 PASSWORD2 Player1Moves.txt Player2Moves.txt localhost 4444

