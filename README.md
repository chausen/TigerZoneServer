# TigerZone Server
A server for playing the two-player-tile-placement game inspired by Klaus-Jurgen Wrede's Carcassonne boardgame series - TigerZone.

## Compilation
#### From the command-line
From the directory where you cloned the repo (*the root directory*), create a directory called `out`
 
 Run (*from the root directory*):

`javac -d out -sourcepath src src/com/tigerzone/fall2016server/server/TournamentServerMain.java`

#### From IntelliJ
*To be added...*

#### From Eclipse
*To be added...*
 

## Configuring the tournament
`TournamentServerMain.java` can take 0, 3, or 4 command-line arguments. These specify:
+ `port number: int`  The port number clients will connect to
+ `seed: int` Determines the order the tiles will be drawn
+ `maxConnections: int` The server will wait until this many player's have connected, or 45 seconds has elapsed to begin 
+ `tournamentID: int` Identifies the tournament for use in logging

Providing no arguments will start the server with the default values of: 
+ `4444` 
+ `123456789` 
+ `2` 
+ `1` 

Providing three arguments will specify the port, seed, and max connections. Providing fourth will also allow the 
tournament ID to be specified. 


## Running a tournament
#### From the command-line

Run (*from the room directory*):

`java -classpath out com.tigerzone.fall2016server.server.TournamentServerMain`

Command-line arguments can be optionally specified at the end of this line.
 
#### From IntelliJ
*To be added...*
 
#### From Eclipse
*To be added...*

#### From a bash script
Included in the root directory is a bash script called `StartServer.sh`. 
On Unix-based systems, this can be run at the command-line using `./StartServer.sh` to continually start new tournaments 
with a random seed and incrementing tournamentID. Note that the location of your class files must be in `out/production/TigerZone` vs. just `out`, or the script was be modified.

### Running Tests

#### From IntelliJ
+ Make sure the `tst` directory is marked as your *Tests Root*
+ Make sure **JUnit4** is added to your classpath. 
+ Right-click `tst` and click `Run 'All Tests'` or enter `Ctrl-Shift-F10` (using the default key-mapping)
 
 
