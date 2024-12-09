package se.yrgo.bladesandmoccasins;

public class Player {
    private String name;
    private int hitPoints = 20;
    private int energy = 10;
    private String weapon;

    public Player(String name) {
        this.name = name;
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

    public String getWeapon() {
        return weapon;
    }
}