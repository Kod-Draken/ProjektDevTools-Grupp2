package se.yrgo.bladesandmoccasins;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    public void menu() {
        System.out.println("Hello gladiator! Welcome to Blades and Moccasins.");
        System.out.println("You are just about to begin your career in the arena...");
        System.out.println("But before that, I must ask.. What is your name?");

        try (Scanner scanner = new Scanner(System.in)){
            String name = scanner.nextLine();
            Player player = new Player(name);
            System.out.println("Welcome to Blades and Moccasins." + player.getName());
            System.out.println("You have " + player.getHitPoints() + " hitpoints and a " + player.getWeapon());

            while(true) {
                System.out.println("Main Menu");
                System.out.println("    1. Play");
                System.out.println("    2. View player status");
                System.out.println("    3. Exit game");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
            }
        }
    }
}
