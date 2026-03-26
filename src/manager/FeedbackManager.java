package manager;

import model.FeedbackEntry;
import model.Meal;
import model.Student;
import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class FeedbackManager {

    private List<FeedbackEntry> entries;

    public FeedbackManager() {
        this.entries = FileHandler.loadAllEntries();
    }

    // Add a new feedback entry and persist it
    public void addFeedback(Student student, Meal meal, int rating, String complaint) {
        FeedbackEntry entry = new FeedbackEntry(student, meal, rating, complaint);
        entries.add(entry);
        FileHandler.saveEntry(entry);
        System.out.println("\nFeedback saved successfully!");
    }

    // View all feedback entries
    public void viewAllFeedback() {
        if (entries.isEmpty()) {
            System.out.println("No feedback entries found.");
            return;
        }
        System.out.println("\n===== All Feedback Entries =====");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }
    }

    // Return entries filtered by meal type (Breakfast / Lunch / Dinner)
    public List<FeedbackEntry> getEntriesByMealType(String mealType) {
        List<FeedbackEntry> filtered = new ArrayList<>();
        for (FeedbackEntry e : entries) {
            if (e.getMeal().getType().equalsIgnoreCase(mealType)) {
                filtered.add(e);
            }
        }
        return filtered;
    }

    public List<FeedbackEntry> getAllEntries() {
        return entries;
    }
}
