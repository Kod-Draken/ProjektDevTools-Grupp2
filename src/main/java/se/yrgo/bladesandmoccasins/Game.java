package se.yrgo.bladesandmoccasins;

import java.util.Scanner;

public class Game {
    Player player;
    public void menu() {
        System.out.println("Hello gladiator! Welcome to Blades and Moccasins.");
        System.out.println("You are just about to begin your career in the arena...");
        System.out.println("But before that, I must ask.. What is your name?");
        try (Scanner scanner = new Scanner(System.in)) {
            String name = scanner.nextLine();

            System.out.println("Chose a weapon\n1. Gladus\n2. Spear\n3. Greatsword");
            String weapon = "No weapon";
            int choice = Integer.MAX_VALUE;
            while (choice < 0 || choice > 3) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        weapon = "Gladus";
                        break;
                    case 2:
                        weapon = "Spear";
                        break;
                    case 3:
                        weapon = "Greatsword";
                        break;
                    default:
                        System.out.println("Something went wrong");
                        break;
                }
            }
            player = new Player(name, weapon);
            System.out.println(player);

        }
    }
}
