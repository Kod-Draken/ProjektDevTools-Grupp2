package se.yrgo.bladesandmoccasins.util;

/**
 * @author Mattias
 * Relies on enum Dice to get corresponding damage
 * Each type has an energy strain.
 */
public enum WeaponType {
    GLADIUS(Dice.D6, 2),
    LONGSWORD(Dice.D8, 4),
    GREATAXE(Dice.D12, 6);

    private final Dice damageDice;
    private final int strain;

    WeaponType(Dice damage, int strain) {
        this.damageDice = damage;
        this.strain = strain;
    }

    public int getDamage() {
        return damageDice.roll();
    }

    public int getStrain() {
        return strain;
    }
}

