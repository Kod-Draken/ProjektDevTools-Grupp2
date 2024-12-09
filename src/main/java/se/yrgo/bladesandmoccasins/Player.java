package se.yrgo.bladesandmoccasins;

public class Player {
    private String name;
    private int hitPoints = 20;
    private int energy = 10;
    private Weapon weapon;

    public Player(String name, String weapon) {
        this.name = name;
        this.weapon = new Weapon(weapon);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, HitPoints: %d, Energy: %d, Weapon: %s", name, hitPoints, energy, weapon.getName());
    }
}
