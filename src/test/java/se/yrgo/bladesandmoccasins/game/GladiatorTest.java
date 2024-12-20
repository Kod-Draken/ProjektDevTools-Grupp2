package se.yrgo.bladesandmoccasins.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.yrgo.bladesandmoccasins.util.Weapon;
import se.yrgo.bladesandmoccasins.util.WeaponType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mattias
 */
class GladiatorTest {
    private Gladiator gladiator1;
    private Gladiator gladiator2;

    /**
     * Ensures we have Gladiators to test the methods with.
     */
    @BeforeEach
    void setUp() {
        gladiator1 = new Gladiator("player", "Gladius");
        gladiator2 = new Gladiator("opponent",20,0,"Gladius");
    }

    @Test
    void testFailedAttack() {
        assertThrows(InsufficientEnergyException.class, () -> {
            gladiator2.attack(gladiator1);
        }, "Expected InsufficientEnergyException to be thrown if energy is too low.");
    }

    @Test
    void testAttack() {
        try {
            gladiator1.attack(gladiator2);
        } catch (InsufficientEnergyException e) {
            // will not happen
        }
        assertTrue(gladiator2.getHitPoints() < 20);
    }

    @Test
    void testRest() {
        gladiator1.rest();
        assertTrue(gladiator1.getEnergy() > 10);
    }

    @Test
    void testGetName() {
        assertEquals(gladiator1.getName(), "player");
    }

    @Test
    void testGetHitPoints() {
        assertEquals(gladiator1.getHitPoints(), 20);
    }

    @Test
    void testGetEnergy() {
        assertEquals(gladiator1.getEnergy(), 10);
    }

    @Test
    void testGetWeapon() {
        assertEquals(gladiator1.getWeapon().getWeaponType(), WeaponType.GLADIUS);
        assertEquals(gladiator1.getWeapon().getName().toLowerCase(), WeaponType.GLADIUS.name().toLowerCase());
    }
}