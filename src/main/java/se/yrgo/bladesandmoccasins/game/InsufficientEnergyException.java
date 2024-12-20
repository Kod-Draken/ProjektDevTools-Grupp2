package se.yrgo.bladesandmoccasins.game;

/**
 * @author Mattias
 * Happens when a Gladiator tries to attack without enough energy.
 */
public class InsufficientEnergyException extends Exception {
    public InsufficientEnergyException(String message) {
        super(message);
    }
}