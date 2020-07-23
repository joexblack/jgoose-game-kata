# JGooseGameKata
The Game of the Goose fully written in Java.  
Implementation of [Goose Game Kata](https://github.com/xpeppers/goose-game-kata)

## Compile sources

To build the software JDK version 1.8 and maven 3 are required.  
To compile the project follow these steps:

1. `git clone https://github.com/joexblack/jgoose-game-kata.git`
2. `cd jgoose-game-kata`
3. `mvn clean package`

## Start game

To start game from the project directory go to **/target** subdir and execute `java -jar JGooseGameKata.jar`.

### Game commands

The commands are:

- `add player <PLAYER>`  => add player to game.
- `move <PLAYER>` => rolls the dices and move player.
- `move <PLAYER> <DICE1>, <DICE2>` => move the player with manual rolled dice.
- `exit` => exit the game.
