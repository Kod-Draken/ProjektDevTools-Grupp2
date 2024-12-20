package se.yrgo.bladesandmoccasins.main;

import se.yrgo.bladesandmoccasins.game.Game;

/**
 * @author Mattias
 * Starts the game
 */
public class Main {
    public static void main(String[] args) {
        Game play = new Game();
        play.menu();
    }
}
