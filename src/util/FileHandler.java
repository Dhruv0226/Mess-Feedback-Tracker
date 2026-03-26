package util;

import model.FeedbackEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "feedback.csv";
    private static final String HEADER    = "RollNo,Name,Block,Date,MealType,DishName,Rating,Complaint";

    // Save a single new entry (append mode)
    public static void saveEntry(FeedbackEntry entry) {
        boolean fileExists = new File(FILE_PATH).exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (!fileExists) {
                writer.write(HEADER);
                writer.newLine();
            }
            writer.write(entry.toCsvLine());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving feedback: " + e.getMessage());
        }
    }

    // Load all entries from the CSV file
    public static List<FeedbackEntry> loadAllEntries() {
        List<FeedbackEntry> entries = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return entries;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                if (line.trim().isEmpty()) continue;
                FeedbackEntry entry = FeedbackEntry.fromCsvLine(line);
                if (entry != null) entries.add(entry);
            }
        } catch (IOException e) {
            System.out.println("Error loading feedback: " + e.getMessage());
        }

        return entries;
    }

    public static String getFilePath() {
        return new File(FILE_PATH).getAbsolutePath();
    }
}
