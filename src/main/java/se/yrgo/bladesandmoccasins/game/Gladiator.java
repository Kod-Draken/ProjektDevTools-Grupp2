package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Dice;
import se.yrgo.bladesandmoccasins.util.Weapon;

import java.util.Scanner;

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

    public void turn(Gladiator target, Scanner scanner){
        System.out.println("""
                What action would you like to take?
                1. Attack
                2. Heal
                3. Rest
                """);
        int choice = scanner.nextInt();
        switch (choice){
            case 1: attack(target); break;
            case 2: heal(); break;
            case 3: rest(); break;
        }
    }

    /**
     * The main procedure of a duel is the attack, we roll the dice
     * for the weapon used by the gladiator, the damage is applied
     * to the target, and the strain is applied to the attacker.
     * @param target defined by the Arena.fight() method
     */
    public void attack(Gladiator target){
        // implement a d20 roll to see if attack hits or misses
        int damage = weapon.getWeaponType().getDamage();
        target.wound(damage);
        energy -= weapon.getWeaponType().getStrain();
        System.out.printf("%s attacks %s with his %s and deals %d damage", this.name, target.name, this.weapon.getName(), damage);
    }

    /**
     * Increase hitPoints with 1-6
     */
    private void heal(){
        this.hitPoints += Dice.D6.roll();
        System.out.printf("%s drinks a potion and heals %d HP!", this.name, this.hitPoints);
    }

    /**
     * This method is called by the attack method when an attack
     * is successful.
     * @param amount is provided by the Dice.roll() method.
     */
    private void wound(int amount){
        this.hitPoints -= amount;
    }

    void rest(){
        this.energy += Dice.D6.roll();
        System.out.printf("%s rests to regain energy!", this.name);
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