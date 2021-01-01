# Pocket Tanks Game

## Description
Pocket tanks is a multiplayer game between two players. The game can be played online by random matching with another
player on the network or played offline between two players on the same machine. The game has a website to show 
different statistics and provides some functionality related to the game to the users.

## Features
The feature can be split into two main categories:

### Features available for the user on the game itself:
1. Login to the game
2. Choose to play online. The user will be matched with another online player.
3. Choose to play offline with another player on the same machine.
4. Choose to play offline with the computer.

### The feature available for the user on the website:
1. Register a new user.
2. Log in to the site.
3. Download the game.
4. View the match history.
    - The other player's name.	
    - Game winner.
    - Each player score.
5. Read the game description and rules.
6. View the highest score panel.
7. View other users profile and matches history.

## Design
### Architecture
![architecture](https://drive.google.com/file/d/1dsqFL5deAWu0KRmgNfsNXwRrSNW_xyNS/view?usp=sharing)
- As shown in the figure, the user can interact with either the local game, or the web front end in the browser.
- Matching two players will be done through a WebSocket server.
- The WebSocket server is the main communication channel between the two players while playing the game.
- At the end of each game, the local desktop game will send some statistics about the game results(the winner, score, players,.) to the webserver through an HTTP API.
- The webserver communicates with the database server to save and show the data on the site front-end.

### User sequence diagram
![sequence diagram](https://drive.google.com/file/d/1Yir2UcEjBVWCkd0KtH4PY-COO98OX38M/view?usp=sharing)

### Database design
![sequence diagram](https://drive.google.com/file/d/14Etd7imIm3LNl6tj9X8fvA2UNBHZuwst/view?usp=sharing)




