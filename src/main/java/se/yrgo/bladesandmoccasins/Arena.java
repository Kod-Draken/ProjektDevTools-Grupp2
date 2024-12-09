package se.yrgo.bladesandmoccasins;

public class Arena {
    private int difficultyModifier = -5;

    public void fight(Gladiator player){
        Gladiator opponent = generateOpponent();
        System.out.println("Your opponent is...");
        System.out.println(opponent);
    }

    private Gladiator generateOpponent(){
        String name = "Opponent";
        int hitPoints = 20 + difficultyModifier;
        int energy = 10 + difficultyModifier;
        return new Gladiator(name, hitPoints, energy, Weapon.getRandomWeapon());
    }
}
