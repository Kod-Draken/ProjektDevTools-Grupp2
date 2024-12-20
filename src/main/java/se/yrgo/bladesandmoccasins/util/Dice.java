package se.yrgo.bladesandmoccasins.util;

/**
 * @author Mattias
 * Represents polyhedral dices, each have its own amount of sides
 */
public enum Dice {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    private final int sides;

    Dice(int sides) {
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    /**
     *
     * @return one of the faces the die can land on as an int.
     */
    public int roll() {
        return (int) (Math.random() * sides) + 1;
    }
}
