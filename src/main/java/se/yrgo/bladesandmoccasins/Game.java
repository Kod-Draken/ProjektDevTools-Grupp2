package se.yrgo.bladesandmoccasins;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    private Gladiator player;
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
    public void startGame(Scanner scanner) {
        System.out.println("""
               Hello gladiator! Welcome to Blades and Moccasins.
               You are just about to begin your career in the arena...
               But before that, I must ask.. What is your name?
                """);
        String name = scanner.nextLine();

        System.out.println("You also need a weapon. Choose from the options below:");
        System.out.println("1. Gladius\n2. Spear\n3. Greataxe");
        int weaponChoice = scanner.nextInt();
        scanner.nextLine();

        String weapon = switch (weaponChoice) {
            case 1 -> "Gladius";
            case 2 -> "Spear";
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

    private void gameLoop(Scanner scanner) {
        boolean playing = true;

        while(playing) {
            Arena playRound = new Arena();
            playRound.fight(player);

            System.out.println("Do you want to play another opponent? (yes/no)");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (!answer.equals("yes")) {
                    playing = false;
                    gameOver();
            }
        }
    }
    private void displayHighScores() {
        System.out.println("High scores:");
        Score score = new Score("dummy", 0);
        System.out.println(score.toString());
    }
    private void gameOver() {
        System.out.println("You have fallen in battle!");
        System.out.println("Returning to the main menu...");
    }
}