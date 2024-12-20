package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Weapon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mattias
 * simulates the fight in the arena, keeps track of the difficulty.
 * The difficulty is increased each round.
 */
public class Arena {
    private int difficultyModifier;

    /**
     * Creates an arena with the provided difficulty
     * @param difficultyModifier modifies the opponents health and energy
     */
    public Arena(int difficultyModifier) {
        this.difficultyModifier = difficultyModifier;
    }

    /** NEEDS FIXING IN THE LOOP, TRUE CONDITION MAYBE? CHECK BOOLEAN?
     * The backbone for the game, the player fights an opponent and
     * the difficulty increases for each opponent defeated until
     * the player is eventually defeated and the game is over.
     * @param player provided by Main.menu()
     */
    public void fight(Gladiator player, Scanner scanner) {
        Gladiator opponent = generateOpponent();

        System.out.println("Travelling to the arena...\n");
        System.out.println("Your opponent is: " + opponent.getName() + "\n");

        // Determine first turn randomly
        boolean playerTurn = ThreadLocalRandom.current().nextInt(0, 2) == 0;

        while (player.getHitPoints() > 0 && opponent.getHitPoints() > 0) {
            if (playerTurn) {
                System.out.println(player.getName() + "'s turn!");
                player.turn(opponent, scanner);
            } else {
                System.out.println(opponent.getName() + "'s turn!");
                if (opponent.getEnergy() <= opponent.getWeapon().getWeaponType().getStrain()){
                    opponent.rest();
                }
                else {
                    try {
                        opponent.attack(player);
                    } catch (InsufficientEnergyException e) {
                        // this will never happen.
                        // the AI doesn't attack if energy is too low.
                    }
                }
            }

            // Alternate turns
            playerTurn = !playerTurn;

            // Display current HP and player Energy
            System.out.printf("%n%n%s HP: %d Energy: %d%n%s HP: %d%n",
                    player.getName(), player.getHitPoints(), player.getEnergy(),
                    opponent.getName(),opponent.getHitPoints());
            System.out.println();
        }

        // Announce the winner
        String winner = player.getHitPoints() > 0 ? player.getName() : opponent.getName();
        System.out.println(winner + " wins the fight!");
        if (winner.equals(player.getName())){
            System.out.println("You earned 100 points!");
        }
    }

    /**
     * Generates a new opponent and for each round the opponent gets stronger.
     * Reads a random name from the gladiator_names.txt file.
     * @return an opponent.
     */
    private Gladiator generateOpponent(){
        String name = randomName();
        int hitPoints = 20 + difficultyModifier;
        int energy = 10 + difficultyModifier;
        return new Gladiator(name, hitPoints, energy, Weapon.getRandomWeapon());
    }

    /**
     * Generates a proper name for an opponent.
     * reads from gladiator_names.txt to get name
     * reads from gladiator_suffixes.txt to get suffix.
     * Combines them with a string builder.
     * Handles a very local IOException by assigning a standard name
     * If I had skill and wasn't lazy I would log the exception.
     * @return A combo of name and suffix.
     */
    private String randomName() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> names = Files.readAllLines(Path.of("src/main/resources/gladiator_names.txt"));
            List<String> suffixes = Files.readAllLines(Path.of("src/main/resources/gladiator_suffixes.txt"));
            builder.append(names.get(ThreadLocalRandom.current().nextInt(names.size()))
                            + " "
                            + suffixes.get(ThreadLocalRandom.current().nextInt(suffixes.size())));
            return builder.toString();
        } catch (IOException e) {
            return "Lemmy the Memelord";
        }
    }

}
