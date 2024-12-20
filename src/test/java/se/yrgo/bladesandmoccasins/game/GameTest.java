package se.yrgo.bladesandmoccasins.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Game game;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        game = new Game();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testMenuDisplay() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        game.menu();

        String expectedOutput = """
                Welcome to Blades and Moccasins!
                    1. Start Game
                    2. View High Scores
                    3. Exit
                Goodbye Gladiator!
                """;
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testMenuStartGameOption() {
        // Simulerar att användaren väljer alternativ "1" (Start Game)
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        game.menu();

        // Kontrollera att spelet startar (kräver en viss text som indikerar spelets början)
        String output = outContent.toString();
        assertTrue(output.contains("Hello gladiator! Welcome to Blades and Moccasins."),
                "The game did not display the start message for starting the game.");
    }

    @Test
    void testMenuViewHighScoresOption() {
        // Simulerar att användaren väljer alternativ "2" (Visa High Scores)
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        game.menu();

        // Kontrollera att High Scores visas
        String output = outContent.toString();
        assertTrue(output.contains("High scores:"),
                "The menu did not display the high scores when option 2 was selected.");
    }
}