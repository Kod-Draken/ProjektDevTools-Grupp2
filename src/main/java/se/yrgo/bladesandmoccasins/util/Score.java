package se.yrgo.bladesandmoccasins.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author Daniel
 */
public class Score {
    private String name;
    private int value;

    public Score() {}
    public Score(String name, int value) {
        this.name = name;
        this.value = value;

        try {
            updateScoreBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    /**
     * Uppdaterar score-borden baserat på den nya poängen.
     */
    private void updateScoreBoard() throws IOException {
        // Läs in befintlig score till en Map
        Map<String, Integer> scores = loadScores();

        // Uppdatera med den nya poängen
        scores.put(name, Math.max(scores.getOrDefault(name, 0), value));

        // Sortera poängen efter värden (högsta först)
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Skriv de sorterade poängen tillbaka till filen
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("src/main/resources/Score.txt"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Map.Entry<String, Integer> entry : sortedScores) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }

    /**
     * Laddar befintliga poäng från filen.
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
     * Returnerar en snygg representation av de 10 bästa resultaten.
     */
    @Override
    public String toString() {
        StringBuilder topScores = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Path.of("src/main/resources/Score.txt"))) {
            String line;

            for(int rank = 1;(line = reader.readLine()) != null && rank <= 10; rank++){
                topScores.append(rank).append(". ").append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading score file: " + e.getMessage());
        }

        return topScores.toString();
    }
}