package model;

public class FeedbackEntry {
    private Student student;
    private Meal meal;
    private int rating;       // 1 to 5
    private String complaint; // Optional, can be empty

    public FeedbackEntry(Student student, Meal meal, int rating, String complaint) {
        this.student = student;
        this.meal = meal;
        this.rating = rating;
        this.complaint = complaint;
    }

    public Student getStudent() { return student; }
    public Meal getMeal()       { return meal; }
    public int getRating()      { return rating; }
    public String getComplaint(){ return complaint; }

    // Convert to a CSV line for saving to file
    public String toCsvLine() {
        return student.getRollNumber() + "," +
               student.getName() + "," +
               student.getHostelBlock() + "," +
               meal.getDate() + "," +
               meal.getType() + "," +
               meal.getName() + "," +
               rating + "," +
               complaint.replace(",", ";"); // avoid comma conflicts
    }

    // Reconstruct a FeedbackEntry from a CSV line
    public static FeedbackEntry fromCsvLine(String line) {
        String[] parts = line.split(",", 8);
        if (parts.length < 8) return null;

        Student student = new Student(parts[1].trim(), parts[0].trim(), parts[2].trim());
        Meal meal       = new Meal(parts[5].trim(), parts[4].trim(), parts[3].trim());
        int rating      = Integer.parseInt(parts[6].trim());
        String complaint = parts[7].trim();

        return new FeedbackEntry(student, meal, rating, complaint);
    }

    @Override
    public String toString() {
        String stars = "*".repeat(rating);
        String c = complaint.isEmpty() ? "No complaint" : complaint;
        return "[" + meal.getDate() + "] " + meal.getType() + " | " + meal.getName() +
               " | Rating: " + stars + " (" + rating + "/5)" +
               " | By: " + student.getName() +
               " | Complaint: " + c;
    }
}
