package se.yrgo.bladesandmoccasins;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.yrgo.bladesandmoccasins.util.Score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Score}.
 * This class verifies the behavior of the {@code Score} class and ensures that its
 * operations involving the "Score.txt" file are correctly implemented.
 */
public class ScoreTest {
    private static final Path SCORE_FILE = Path.of("Score.txt");

    /**
     * Sets up the test environment by creating a fresh "Score.txt" file before each test.
     *
     * @throws IOException if an I/O error occurs while creating the file
     */
    @BeforeEach
    public void setUp() throws IOException {
        Files.createFile(SCORE_FILE);
    }

    /**
     * Cleans up the test environment by deleting the "Score.txt" file after each test.
     *
     * @throws IOException if an I/O error occurs while deleting the file
     */
    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(SCORE_FILE);
    }

    /**
     * Tests the {@code Score} constructor and getter methods.
     * <p>
     * Verifies that the constructor correctly sets the name and value of a {@code Score} object.
     */
    @Test
    public void testConstructorAndGetters() {
        Score score = new Score("Dummy1", 10);
        assertEquals("Dummy1", score.getName());
        assertTrue(10 <= score.getValue());
    }

    /**
     * Tests the scoreboard updating functionality of the {@code Score} class.
     * <p>
     * Ensures that invalid score entries (e.g., negative scores) are not written to the "Score.txt" file.
     *
     * @throws IOException if an I/O error occurs while reading the file
     */
    @Test
    public void testUpdateScoreBoard() throws IOException {
        new Score("Dummy2", 20);
        new Score("Dummy2", -15);

        String content = Files.readString(SCORE_FILE);
        assertFalse(content.contains("Dummy2 -15"));
    }
}
