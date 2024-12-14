package se.yrgo.bladesandmoccasins.util;

public class Weapon {
    private final String name;
    private final WeaponType weaponType;

    /**
     * Needs fixing badly
     * @param name
     */
    public Weapon(String name){
        this.name = name;

        // temporary variable to ensure weaponType is initialized
        WeaponType temp = WeaponType.GLADIUS;
        for (WeaponType type : WeaponType.values()){
            if (type.name().equalsIgnoreCase(name)){
                temp = type;
                break;
            }
        }
        this.weaponType = temp;
    }

    public static String getRandomWeapon(){
        return WeaponType.values()[(int) (Math.random() * WeaponType.values().length)].name();
    }

    public String getName() {
        return name;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
