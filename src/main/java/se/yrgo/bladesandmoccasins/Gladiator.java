package se.yrgo.bladesandmoccasins;

public class Gladiator {
    private String name;
    private int hitPoints;
    private int energy;
    private Weapon weapon;

    public Gladiator(String name, String weapon) {
        this.name = name;
        this.weapon = new Weapon(weapon);
        this.hitPoints = 20;
        this.energy = 10;
    }

    public Gladiator(String name, int hitPoints, int energy, String weapon) {
        this.name = name;
        this.weapon = new Weapon(weapon);
        this.hitPoints = hitPoints;
        this.energy = energy;
    }

    public void heal(int amount){
        this.hitPoints += amount;
    }

    public void wound(int amount){
        this.hitPoints -= amount;
    }

    public String toString() {
        return String.format("Name: %s, HitPoints: %d, Energy: %d, Weapon: %s", name, hitPoints, energy, weapon.getName());
    }
}