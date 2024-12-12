package se.yrgo.bladesandmoccasins;

import java.util.concurrent.ThreadLocalRandom;

public class Arena {
    private int difficultyModifier = -5;

    public void fight(Gladiator player){
        Gladiator opponent = generateOpponent();
        System.out.println("Travelling to the arena");
        System.out.println("Your opponent is... \n" + opponent);

        while (player.getHitPoints() > 0 && opponent.getHitPoints() > 0){
            int whoStarts = ThreadLocalRandom.current().nextInt(0, 2);
            if (whoStarts == 0){
                player.attack(opponent);
                opponent.attack(player);
            }
            else {
                opponent.attack(player);
                player.attack(opponent);
            }
        }
        System.out.println(player);
        System.out.println(opponent);
    }

    private Gladiator generateOpponent(){
        String name = "Opponent";
        int hitPoints = 20 + difficultyModifier;
        int energy = 10 + difficultyModifier;
        return new Gladiator(name, hitPoints, energy, Weapon.getRandomWeapon());
    }
}
