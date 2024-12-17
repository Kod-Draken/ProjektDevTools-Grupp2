package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Weapon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
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
    public void fight(Gladiator player){
        Gladiator opponent = generateOpponent();
        System.out.println("Travelling to the arena");
        System.out.println("Your opponent is... \n" + opponent.getName());

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
