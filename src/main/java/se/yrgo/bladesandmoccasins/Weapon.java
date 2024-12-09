package se.yrgo.bladesandmoccasins;

public class Weapon {
    private String name;
    private Type weaponType;
    private Dice damage;

    public Weapon(String name){
        this.name = name;
        for (Type type : Type.values()){
            if (type.name().equalsIgnoreCase(name)){
                this.weaponType = type;
            }
        }
        switch (weaponType){
            case GLADUS -> this.damage = Dice.D6;
            case SPEAR -> this.damage = Dice.D8;
            case GREATAXE -> this.damage = Dice.D12;
        }
    }

    private enum Type {
        GLADUS, SPEAR, GREATAXE
    }

    private enum Dice {
        D6, D8, D12
    }

    public String getName() {
        return name;
    }
}
