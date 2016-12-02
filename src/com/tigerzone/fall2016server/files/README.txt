This file gives information on the different files contained in the different .txt files contained in this directory.

MOVESETS
Movesets are used by client creating scripts located in the root directory. They are prefixed with the player in
each game that will be using the moveset (player1, or P1, always goes first). For a game to complete with a moveset that
is less than 76 moves, the tile stack must be truncated in GameSystem.

moveset1
A 20 move moveset for use with seed 123456789. At the end of the moveset, P1 should score 7 points and P2 should score 17.

moveset2
A 12 move moveset for use with seed 1.

moveset3
A 12 move moveset for use with seed 3.

moveset4
A 6 move moveset that requires a custom made Tilestack. Use getUnplaceableTestStack() in GameSystem.
This moveset is for testing all of the different Unplaceable cases.

moveset5
Contains 10 moves and can be used by either player. Has several malformed moves in it and can be used to test forfeiting.