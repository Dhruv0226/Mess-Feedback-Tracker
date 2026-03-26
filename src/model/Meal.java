package model;

public class Meal {
    private String name;
    private String type; // Breakfast, Lunch, Dinner
    private String date; // Format: YYYY-MM-DD

    public Meal(String name, String type, String date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return type + " - " + name + " (" + date + ")";
    }
}
