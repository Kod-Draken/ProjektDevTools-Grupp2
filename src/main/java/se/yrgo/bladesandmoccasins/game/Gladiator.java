package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Weapon;

public class Gladiator {
    private final String name;
    private int hitPoints;
    private int energy;
    private final Weapon weapon;

    /**
     * This is the constructor for creating the player's gladiator.
     * @param name given by the menu in Main
     * @param weapon given by the menu in Main
     */
    public Gladiator(String name, String weapon) {
        this.name = name;
        this.weapon = new Weapon(weapon);
        this.hitPoints = 20;
        this.energy = 10;
    }

    /**
     * This constructor is used for generated opponent gladiators.
     * @param name
     * @param hitPoints
     * @param energy
     * @param weapon All parameters are provided by the generateOpponent() method in Arena.
     */
    public Gladiator(String name, int hitPoints, int energy, String weapon) {
        this.name = name;
        this.weapon = new Weapon(weapon);
        this.hitPoints = hitPoints;
        this.energy = energy;
    }

    /**
     * The main procedure of a duel is the attack, we roll the dice
     * for the weapon used by the gladiator, the damage is applied
     * to the target, and the strain is applied to the attacker.
     * @param target defined by the Arena.fight() method
     */
    public void attack(Gladiator target){
        // implement a d20 roll to see if attack hits or misses
        target.wound(weapon.getWeaponType().getDamage());
        energy -= weapon.getWeaponType().getStrain();
    }

    /** UNIMPLEMENTED
     * Allows the gladiator to heal
     * @param amount
     */
    public void heal(int amount){
        this.hitPoints += amount;
    }

    /**
     * This method is called by the attack method when an attack
     * is successful.
     * @param amount is provided by the Dice.roll() method.
     */
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