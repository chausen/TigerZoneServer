#!/bin/bash
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain PLAYER1 PASSWORD1 Player1Moves.txt Player2Moves.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain PLAYER2 PASSWORD2 Player1Moves.txt Player2Moves.txt localhost 4444 &


