#!/usr/bin/env bash
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMA IAMA moveset4_P1.txt moveset4_P2.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain TEAMB IAMB moveset4_P1.txt moveset4_P2.txt localhost 4444