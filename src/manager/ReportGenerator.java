package manager;

import model.FeedbackEntry;

import java.util.*;

public class ReportGenerator {

    private List<FeedbackEntry> entries;

    public ReportGenerator(List<FeedbackEntry> entries) {
        this.entries = entries;
    }

    // Weekly summary: average rating per dish, sorted worst first
    public void printWeeklyReport() {
        if (entries.isEmpty()) {
            System.out.println("No data available to generate a report.");
            return;
        }

        // Map: dish name -> list of ratings
        Map<String, List<Integer>> dishRatings = new HashMap<>();

        for (FeedbackEntry e : entries) {
            String dish = e.getMeal().getName();
            dishRatings.computeIfAbsent(dish, k -> new ArrayList<>()).add(e.getRating());
        }

        // Compute averages
        Map<String, Double> averages = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : dishRatings.entrySet()) {
            double avg = entry.getValue().stream()
                              .mapToInt(Integer::intValue)
                              .average()
                              .orElse(0);
            averages.put(entry.getKey(), avg);
        }

        // Sort by average rating ascending (worst first)
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(averages.entrySet());
        sorted.sort(Map.Entry.comparingByValue());

        System.out.println("\n========================================");
        System.out.println("        WEEKLY MESS FEEDBACK REPORT     ");
        System.out.println("========================================");
        System.out.printf("%-25s %-10s %-10s%n", "Dish", "Avg Rating", "Votes");
        System.out.println("----------------------------------------");

        for (Map.Entry<String, Double> entry : sorted) {
            String dish  = entry.getKey();
            double avg   = entry.getValue();
            int votes    = dishRatings.get(dish).size();
            String bar   = ratingBar(avg);
            System.out.printf("%-25s %-10.2f %-5d  %s%n", dish, avg, votes, bar);
        }

        System.out.println("----------------------------------------");
        printWorstDish(sorted);
        printMealTypeBreakdown();
        printComplaintSummary();
        System.out.println("========================================\n");
    }

    private void printWorstDish(List<Map.Entry<String, Double>> sorted) {
        if (!sorted.isEmpty()) {
            Map.Entry<String, Double> worst = sorted.get(0);
            System.out.printf("Most complained dish : %s (avg %.2f/5)%n",
                    worst.getKey(), worst.getValue());
        }
    }

    private void printMealTypeBreakdown() {
        Map<String, List<Integer>> typeRatings = new HashMap<>();
        for (FeedbackEntry e : entries) {
            String type = e.getMeal().getType();
            typeRatings.computeIfAbsent(type, k -> new ArrayList<>()).add(e.getRating());
        }

        System.out.println("\nAverage rating by meal type:");
        for (Map.Entry<String, List<Integer>> entry : typeRatings.entrySet()) {
            double avg = entry.getValue().stream()
                              .mapToInt(Integer::intValue)
                              .average()
                              .orElse(0);
            System.out.printf("  %-12s : %.2f / 5%n", entry.getKey(), avg);
        }
    }

    private void printComplaintSummary() {
        long count = entries.stream()
                            .filter(e -> !e.getComplaint().isEmpty())
                            .count();
        System.out.println("\nTotal entries with complaints : " + count + " / " + entries.size());
    }

    // Simple ASCII bar for rating visualisation
    private String ratingBar(double avg) {
        int filled = (int) Math.round(avg);
        return "[" + "#".repeat(filled) + ".".repeat(5 - filled) + "]";
    }
}
