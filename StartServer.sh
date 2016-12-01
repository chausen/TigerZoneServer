#!/usr/bin/env bash
I=10000
RANDOM=$$
while true;
do echo "Starting server...";
   java -classpath out/production/TigerZone com.tigerzone.fall2016server.server.TournamentServerMain 4444 $RANDOM 24 $I;
   let "I++";
   sleep 10;
done
