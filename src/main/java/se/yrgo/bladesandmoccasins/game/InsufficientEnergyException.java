package se.yrgo.bladesandmoccasins.game;

public class InsufficientEnergyException extends Exception {
    public InsufficientEnergyException(String message) {
        super(message);
    }
}