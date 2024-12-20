package se.yrgo.bladesandmoccasins.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Emilia 
 */
class WeaponTest {
    private Weapon weapon;

    @BeforeEach
    void setUp() {
        weapon = new Weapon("gladius");
    }

    @Test
    void getRandomWeapon() {
       List<String> validWeapons = Arrays.asList("gladius", "longsword", "greataxe");
       assertTrue(validWeapons.contains(weapon.getName()));
    }

    @Test
    void getName() {
        assertEquals(weapon.getName().toLowerCase(), "gladius");
    }

    @Test
    void getWeaponType() {
        assertEquals(weapon.getWeaponType(), WeaponType.GLADIUS);
    }
}