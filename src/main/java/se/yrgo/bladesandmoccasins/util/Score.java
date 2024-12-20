package se.yrgo.bladesandmoccasins.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Represents a player's score and manages the leaderboard.
 * <p>
 * This class provides functionality for creating, updating, and retrieving scores.
 * Scores are saved in a file and are displayed in descending order of value.
 */
public class Score {
    private String name;
    private int value;

    /**
     * Default constructor.
     */
    public Score() {}

    /**
     * Constructs a new {@code Score} with a given name and value.
     * Automatically updates the leaderboard with the new score.
     *
     * @param name  the player's name
     * @param value the player's score value
     */
    public Score(String name, int value) {
        this.name = name;
        this.value = value;

        try {
            updateScoreBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the name associated with the score.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of the score.
     *
     * @return the score value
     */
    public int getValue() {
        return value;
    }

    /**
     * Updates the scoreboard with the current score.
     * <p>
     * Reads existing scores from the file, updates them with the current score if it is higher,
     * sorts the scores in descending order, and writes the updated scores back to the file.
     *
     * @throws IOException if an I/O error occurs while updating the file
     */
    private void updateScoreBoard() throws IOException {
        // Load existing scores into a map
        Map<String, Integer> scores = loadScores();

        // Update the map with the new score
        scores.put(name, Math.max(scores.getOrDefault(name, 0), value));

        // Sort the scores by value in descending order
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Write the sorted scores back to the file
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("src/main/resources/Score.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Map.Entry<String, Integer> entry : sortedScores) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }

    /**
     * Loads existing scores from the file into a map.
     * <p>
     * Each line of the file should be in the format: {@code name value}.
     *
     * @return a map of player names to their scores
     */
    private Map<String, Integer> loadScores() {
        Map<String, Integer> scores = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/main/resources/Score.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                scores.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Score file could not be loaded: " + e.getMessage());
        }

        return scores;
    }

    /**
     * Returns a nicely formatted representation of the top 10 scores.
     * <p>
     * Reads the scores from the file and formats them with ranking numbers.
     *
     * @return a string representation of the top 10 scores
     */
    @Override
    public String toString() {
        StringBuilder topScores = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/main/resources/Score.txt"))) {
            String line;

            for (int rank = 1; (line = reader.readLine()) != null && rank <= 10; rank++) {
                topScores.append(rank).append(". ").append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading score file: " + e.getMessage());
        }

        return topScores.toString();
    }
}