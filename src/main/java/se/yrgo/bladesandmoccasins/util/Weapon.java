package se.yrgo.bladesandmoccasins.util;

/**
 * @author Mattias
 */
public class Weapon {
    private final String name;
    private final WeaponType weaponType;

    /**
     * Returns one of the available weapons in the Weapon enum.
     * The temp variable is supposed to be a failsafe if i/o
     * goes wrong, the default value returned will therefor
     * always be Weapon.GLADIUS.
     * @param name provided by the menu in Main-class.
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

    /**
     * This method is used for generating opponents, they will always have
     * a random weapon, to make the game more random.
     * @return a random weapon from the enum WeaponType.
     */
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
