package se.yrgo.bladesandmoccasins.game;

import se.yrgo.bladesandmoccasins.util.Score;
import se.yrgo.bladesandmoccasins.util.Weapon;
import se.yrgo.bladesandmoccasins.util.WeaponType;

import java.util.Scanner;

/**
 * @author Emilia, Mattias
 * The {@code Game} class represents the main entry point for the "Blades and Moccasins" game.
 * It provides a menu system, handles player creation, and controls the main game loop.
 */
public class Game {

    /**
     * The {@code Gladiator} instance representing the player in the game.
     */
    private Gladiator player;
    private int rounds;

    /**
     * @author Emilia
     * Displays the main menu and handles user interaction to navigate through the game.
     * <p>
     * The menu offers options to start the game, view high scores, or exit the program.
     * </p>
     */
    public void menu() {
        try (Scanner scanner = new Scanner(System.in)){
            while(true) {
                System.out.println("""
                Welcome to Blades and Moccasins!
                    1. Start Game
                    2. View High Scores
                    3. Exit
                """);

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 1 -> startGame(scanner);
                    case 2 -> displayHighScores();
                    case 3 -> {
                        System.out.println("Goodbye Gladiator!");
                        System.exit(0);
                    }
                    default -> System.out.println("invalid choice!");
                }
            }
        }
    }

    /**
     * @author Emilia
     * Starts the game by prompting the player to enter their name and select a weapon.
     * <p>
     * This method also initializes the {@code Gladiator} instance and begins the game loop.
     * </p>
     *
     * @param scanner A {@code Scanner} object for reading user input.
     */
    public void startGame(Scanner scanner) {
        System.out.println("""
               Hello gladiator! Welcome to Blades and Moccasins.
               You are just about to begin your career in the arena...
               But before that, I must ask.. What is your name?
                """);

        String name = scanner.nextLine();

        System.out.println("You also need a weapon. Choose from the options below: (1-3)");
        for (WeaponType weapon : WeaponType.values()){
            System.out.println(weapon.name());
        }

        int weaponChoice = scanner.nextInt();
        scanner.nextLine();

        String weapon = switch (weaponChoice) {
            case 1 -> "Gladius";
            case 2 -> "Longsword";
            case 3 -> "Greataxe";
            default -> {
                System.out.println("Invalid choice! Defaulting to Gladius.");
                yield "Gladius";
            }
        };

        player = new Gladiator(name, weapon);
        System.out.println("Welcome " + name + "! Let the battles begin!");

        gameLoop(scanner);
    }

    /**
     * @author Emilia, Mattias
     * Controls the main game loop where the player battles opponents in the arena.
     * <p>
     * The loop continues until the player chooses to stop or is defeated.
     * </p>
     *
     * @param scanner A {@code Scanner} object for reading user input.
     */
    private void gameLoop(Scanner scanner) {
        boolean playing = true;
        int difficulty = -6;

        while(playing) {
            Arena playRound = new Arena(difficulty);
            playRound.fight(player, scanner);
            difficulty += 3;

            // Clear the scanner
            scanner.nextLine();
            if (player.getHitPoints() < 0) {
                System.out.println("You have fallen in battle...");
                gameOver();
            }
            else {
                rounds += 100;
                System.out.println("Do you want to play another opponent? (yes/no)");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (!answer.equals("yes")) {
                        playing = false;
                        gameOver();
                }
            }
        }
    }

    /**
     * @author Emilia
     * Displays the high scores of the game.
     * <p>
     * This method currently displays a dummy high score as a placeholder.
     * </p>
     */
    private void displayHighScores() {
        System.out.println("High scores:");
        Score score = new Score();
        System.out.println(score.toString());
    }

    /**
     * @author Emilia
     * Ends the game and displays a message indicating that the player has fallen in battle.
     * <p>
     * The player is then returned to the main menu.
     * </p>
     */
    private void gameOver() {
        Score score = new Score(player.getName(),rounds);
        System.out.println("GAME OVER");
        System.out.println("Returning to the main menu...");
        menu();
    }
}