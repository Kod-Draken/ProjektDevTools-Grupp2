package se.yrgo.bladesandmoccasins.ui;

import se.yrgo.bladesandmoccasins.game.Game;

/**
 * Starts the game
 */
public class Main {
    public static void main(String[] args) {
        Game play = new Game();
        play.menu();
    }
}
