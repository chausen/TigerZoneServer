#!/usr/bin/env bash
java -classpath ../out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMA IAMA P1TestGameMoves2.txt P2TestGameMoves2.txt localhost 4444 &
java -classpath ../out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMB IAMB P1TestGameMoves2.txt P2TestGameMoves2.txt localhost 4444