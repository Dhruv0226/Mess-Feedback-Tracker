import manager.FeedbackManager;
import manager.ReportGenerator;
import model.Meal;
import model.Student;
import util.FileHandler;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static FeedbackManager feedbackManager = new FeedbackManager();
    private static Student currentStudent = null;

    public static void main(String[] args) {
        printBanner();
        loginStudent();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: submitFeedback();   break;
                case 2: feedbackManager.viewAllFeedback(); break;
                case 3: generateReport();   break;
                case 4: changeStudent();    break;
                case 5: running = false;    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("\nThank you for using Mess Feedback Tracker. Goodbye!");
        scanner.close();
    }

    private static void printBanner() {
        System.out.println("============================================");
        System.out.println("       MESS FEEDBACK & RATING TRACKER      ");
        System.out.println("    Making hostel food better, one meal     ");
        System.out.println("============================================\n");
    }

    private static void loginStudent() {
        System.out.println("--- Student Login ---");
        System.out.print("Enter your name        : ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your roll number : ");
        String roll = scanner.nextLine().trim();
        System.out.print("Enter your hostel block: ");
        String block = scanner.nextLine().trim();
        currentStudent = new Student(name, roll, block);
        System.out.println("\nWelcome, " + name + "!\n");
    }

    private static void printMenu() {
        System.out.println("\n========= MAIN MENU =========");
        System.out.println("  1. Submit meal feedback");
        System.out.println("  2. View all feedback");
        System.out.println("  3. Generate weekly report");
        System.out.println("  4. Switch student");
        System.out.println("  5. Exit");
        System.out.println("==============================");
    }

    private static void submitFeedback() {
        System.out.println("\n--- Submit Feedback ---");

        // Meal type
        System.out.println("Select meal type:");
        System.out.println("  1. Breakfast");
        System.out.println("  2. Lunch");
        System.out.println("  3. Dinner");
        int typeChoice = readInt("Choice: ");
        String mealType;
        switch (typeChoice) {
            case 1: mealType = "Breakfast"; break;
            case 2: mealType = "Lunch";     break;
            case 3: mealType = "Dinner";    break;
            default:
                System.out.println("Invalid choice. Defaulting to Lunch.");
                mealType = "Lunch";
        }

        // Dish name
        System.out.print("Enter dish name (e.g. Dal Makhani): ");
        String dishName = scanner.nextLine().trim();
        if (dishName.isEmpty()) dishName = "Unknown Dish";

        // Date - default to today
        String today = LocalDate.now().toString();
        System.out.print("Enter date (YYYY-MM-DD) or press Enter for today [" + today + "]: ");
        String dateInput = scanner.nextLine().trim();
        String date = dateInput.isEmpty() ? today : dateInput;

        // Rating
        int rating = 0;
        while (rating < 1 || rating > 5) {
            rating = readInt("Rate this meal (1 = terrible, 5 = excellent): ");
            if (rating < 1 || rating > 5) System.out.println("Please enter a number between 1 and 5.");
        }

        // Complaint
        System.out.print("Any complaint? (press Enter to skip): ");
        String complaint = scanner.nextLine().trim();

        Meal meal = new Meal(dishName, mealType, date);
        feedbackManager.addFeedback(currentStudent, meal, rating, complaint);
        System.out.println("Data saved to: " + FileHandler.getFilePath());
    }

    private static void generateReport() {
        ReportGenerator report = new ReportGenerator(feedbackManager.getAllEntries());
        report.printWeeklyReport();
    }

    private static void changeStudent() {
        System.out.println("\n--- Switch Student ---");
        loginStudent();
    }

    // Helper: safely read an integer
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
