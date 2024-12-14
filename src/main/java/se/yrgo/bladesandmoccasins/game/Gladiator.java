package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Weapon;

public class Gladiator {
    private final String name;
    private int hitPoints;
    private int energy;
    private final Weapon weapon;

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

    public void attack(Gladiator target){
        // implement a d20 roll to see if attack hits or misses
        target.wound(weapon.getWeaponType().getDamage());
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

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getEnergy() {
        return energy;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}