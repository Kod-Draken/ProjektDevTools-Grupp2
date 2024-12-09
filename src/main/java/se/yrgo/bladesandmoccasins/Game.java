package se.yrgo.bladesandmoccasins;

import java.util.Scanner;

public class Game {
    private Gladiator player;
    public void menu() {
        System.out.println("""
               Hello gladiator! Welcome to Blades and Moccasins.
               You are just about to begin your career in the arena...
               But before that, I must ask.. What is your name?
                """);

        try (Scanner scanner = new Scanner(System.in)){
            String name = scanner.nextLine();

            System.out.println("You also need a weapon. Choose from the options below:");
            System.out.println("1. Gladius\n2. Spear\n3. Greataxe");

            int choice = scanner.nextInt();
            String weapon = "Unknown"; // placeholder
            switch (choice) {
                case 1: weapon = "Gladius";
                break;
                case 2: weapon = "Spear";
                break;
                case 3: weapon  = "Greataxe";
                break;
                default:
                    System.out.println("invalid choice!");
                    break;
            }

            player = new Gladiator(name, weapon);
            Arena playRound = new Arena();
            playRound.fight(player);
        }
    }
}
