# Terminal Pacman Game (Java OOP)

## Project Overview

This is a simple terminal-based Pacman game made in Java using OOP.

The player controls Pacman using keyboard keys. The goal is to eat food, avoid the ghost, and move around a board with walls.

The project is made to practice OOP concepts like classes, objects, and how different parts of a program work together.

Main parts of the game:

* GameBoard: the map
* Pacman: the player
* Ghost: the enemy
* Food: gives points
* PacmanGame: controls the game

## Controls

Use these keys:

* ^ move up
* v move down
* < move left
* > move right
* q quit game

## Project Structure

PacmanGame.java contains all classes:

* PacmanGame handles the game loop and input
* GameBoard creates and shows the map and walls
* Pacman handles movement and score
* Ghost moves and tries to catch Pacman
* Food is collected for points

## How to Run

First compile:
javac PacmanGame.java

Then run:
java PacmanGame

