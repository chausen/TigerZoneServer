# TigerZoneServer
A server for playing the two-player-tile-placement game inspired by Klaus-Jurgen Wrede's Carcassonne boardgame series - TigerZone.

##Authors
[Erik Christiansen](https://github.com/mistiansen)

[Matthew Diaz](https://github.com/matthewddiaz)

[Jeff Grosso](https://github.com/Drakyne)

[Clay Hausen](https://github.com/chausen)

[Aidan Kelliher](https://github.com/dnkllhr)


## Compilation
#### From the command-line
1. Clone TigerZone Repository: `git clone https://github.com/chausen/TigerZoneServer.git` 
2. From the root directory: create a directory called `out`
3. Run (*from the root directory*):
`javac -d out -sourcepath src src/com/tigerzone/fall2016server/server/TournamentServerMain.java`

#### From IntelliJ
1. In Intellij Click on `File`, then `new`, then `Project from Version Control`
and choose `GitHub`   
2. For GitHub repository url: `https://github.com/chausen/TigerZoneServer.git`
3. Mark `src` as `Sources Root` & `tst` as `Test Sources Root`
4. Click on `File`, then `Project Structure`, then set Project SDK (we used java SDK 1.8045) and set
Project Language Level to `8 - lambda`
5. In `root directory` create directory `out` and make 2 subdirectories `out/production`
    and `out/test`. In `Project Structure` set `compile output path` to `out/production`
    and set `compile test output path` to `out/test`
6. Add **JUnit4** to classpath.
7. Click `Build`, then `Build Project`

## Configuring the tournament

`TournamentServerMain.java` can take 0 to 7 command-line arguments. These specify:
+ `port number: int`  The port number clients will connect to
+ `seed: int` Determines the order the tiles will be drawn
+ `maxConnections: int` The server will wait until this many player's have connected, or 45 seconds has elapsed to begin 
+ `tournamentID: int` Identifies the tournament for use in logging
+ `# of Challengers: int` The number of challenges in the Tournament
+ `Death Style: boolean` If true teams that forfeit in current challenge do 
    not continue participate in following challenges 
+ `Server Password: String` The password for AIs to enter Tournament

Providing no arguments will start the server with the default values of: 
+ `Port: 4444` 
+ `Seed: 123456789` 
+ `maxConnections: 2` 
+ `tournamentID: 1`
+ `# of Challenges: 1` 
+ `Death Style: False` 
+ `Server Password: TigerZone`
 
## Running a tournament
#### From the command-line

Run (*from the root directory*): `java -classpath out com.tigerzone.fall2016server.server.TournamentServerMain`
 
#### From IntelliJ
Click Run: Run Main.java in `fall2016gui` directory
 
#### From a bash script
Included in the root directory is a bash script called `StartServer.sh`. 

NOTE 1: Unix-based systems can run this in the command-line using `./StartServer.sh` to continually start new tournaments 
with a random seed and incrementing tournamentID.

NOTE 2: The location of your class files must be in `out/production/TigerZone` vs. just `out`, or the script will be modified.

## Running Tests

#### From IntelliJ
+ Make sure the `tst` directory is marked as your *Tests Root*
+ Make sure **JUnit4** is added to your classpath. 
+ Right-click `tst` and click `Run 'All Tests'` or enter `Ctrl-Shift-F10` (using the default key-mapping)
 
## TigerZone Stellar AIs
+ [TeamP](https://github.com/ldfreedman/TZP) 
+ [TeamM](https://github.com/wheelsandbytes/tigerzone)
 
## TigerZoneServer GUI
![TigerZone](https://github.com/chausen/TigerZone/blob/master/img/tigerZoneGUI.png)