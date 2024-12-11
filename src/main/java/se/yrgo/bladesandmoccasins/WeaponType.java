package se.yrgo.bladesandmoccasins;

public enum WeaponType {
    GLADIUS(Dice.D6, 2),
    LONGSWORD(Dice.D8, 4),
    GREATAXE(Dice.D12, 6);

    private final Dice damage;
    private final int strain;

    WeaponType(Dice damage, int strain) {
        this.damage = damage;
        this.strain = strain;
    }

    public Dice getDamage() {
        return damage;
    }

    public int getStrain() {
        return strain;
    }
}

