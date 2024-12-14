package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Weapon;

import java.util.concurrent.ThreadLocalRandom;

/**
 * simulates the fight in the arena, keeps track of the difficulty.
 * The difficulty is increased each round.
 */
public class Arena {
    private int difficultyModifier = -6;

    /** NEEDS FIXING IN THE LOOP, TRUE CONDITION MAYBE? CHECK BOOLEAN?
     * The backbone for the game, the player fights an opponent and
     * the difficulty increases for each opponent defeated until
     * the player is eventually defeated and the game is over.
     * @param player provided by Main.menu()
     */
    public void fight(Gladiator player){
        Gladiator opponent = generateOpponent();
        System.out.println("Travelling to the arena");
        System.out.println("Your opponent is... \n" + opponent);

        while (!(player.getHitPoints() <= 0) || !(opponent.getHitPoints() <= 0)){
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

    /**
     * Generates a new opponent and for each round the opponent gets stronger.
     * @return an opponent.
     */
    private Gladiator generateOpponent(){
        String name = "Opponent";
        int hitPoints = 20 + difficultyModifier;
        int energy = 10 + difficultyModifier;
        return new Gladiator(name, hitPoints, energy, Weapon.getRandomWeapon());
    }
}
