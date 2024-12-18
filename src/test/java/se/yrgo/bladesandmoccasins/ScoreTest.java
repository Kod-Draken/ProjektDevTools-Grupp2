package se.yrgo.bladesandmoccasins;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.yrgo.bladesandmoccasins.util.Score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {
    private static final Path SCORE_FILE = Path.of("Score.txt");

    @BeforeEach
    public void setUp() throws IOException {
        // Skapa en tom Score.txt-fil innan varje test
        Files.createFile(SCORE_FILE);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Ta bort Score.txt-filen efter varje test
        Files.deleteIfExists(SCORE_FILE);
    }

    @Test
    public void testConstructorAndGetters() {
        Score score = new Score("Dummy1", 10);
        assertEquals("Dummy1", score.getName());
        assertTrue(10 <= score.getValue());
    }

    @Test
    public void testUpdateScoreBoard() throws IOException {
        new Score("Dummy2", 20);
        new Score("Dummy2", -15);

        String content = Files.readString(SCORE_FILE);
        assertFalse(content.contains("Dummy2 -15"));
    }
}
