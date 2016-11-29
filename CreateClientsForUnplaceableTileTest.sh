#!/usr/bin/env bash
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain Red  Obiwan77 P1UnplaceableTestMoves.txt P2UnplaceableTestMoves.txt localhost 4444 &
java -classpath out/production/TigerZone com.tigerzone.fall2016server.client.ParameterizedClientMain Blue  WookieLover P1UnplaceableTestMoves.txt P2UnplaceableTestMoves.txt localhost 4444