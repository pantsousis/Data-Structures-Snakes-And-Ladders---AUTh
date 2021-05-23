# Data-Structures-Snakes-And-Ladders---AUTh
This project is a snakes and ladders games with a GUI.
It was developed as a team assignment (2 people) for the course of data structures.
The GUI is basic and was extra for the assignment. We were new and this was our first GUI.

The game consists of 2 players trying to reach the top of the board.
There are 3 type of players to choose from:
1. Random player - rolls a random dice number
2. Heuristic player - rolls the optimal dice number (usually 6)
3. MinMax player - takes into account a 'move score' funtion and tries to maximize it
The best player is MinMax (and also the most sophisticated), then Heuristic, then Random.

When the game starts (by running the Game.java file), the GUI is presented, that has the info you need to know.
First you must choose the player, then you need to generate the players on the board and last you need to start the game.
When the game starts, the player who rolls the LOWEST dice starts (we like to give the advantage to the unlucky).
Clicking next turn, the players will roll the dice and move on the board.
There is a maximum of 20 rounds in which one of the players must reach the top and win.
If that doesn't happen, then the player with the highest score wins.
Red apples increase score, Black decrease score and the furthest a player is on the board, the higher the score.
In addition to the GUI, I recommend looking at the console to keep track of the changes in each round.

Due to being one of the first projects I ever made at 19 years old, the documentation is missing, but you are free to explore the code.
