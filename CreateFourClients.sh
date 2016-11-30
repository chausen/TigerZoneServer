#!/bin/bash
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMA  IAMA Player1Moves.txt Player2Moves.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMB  IAMB Player1Moves.txt Player2Moves.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMC  IAMC Player1Moves.txt Player2Moves.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMD  IAMD Player1Moves.txt Player2Moves.txt  localhost 4444