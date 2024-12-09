package se.yrgo.bladesandmoccasins;

import java.util.Scanner;

public class Game {
    public void menu() {
        System.out.println("Hello gladiator! Welcome to Blades and Moccasins.");
        System.out.println("You are just about to begin your career in the arena...");
        System.out.println("But before that, I must ask.. What is your name?");
        try (Scanner scanner = new Scanner(System.in)){
            String name = scanner.nextLine();
            Player player = new Player(name);
        }
    }
}
