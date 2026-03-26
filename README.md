Mess Feedback & Rating Tracker
A Java console application that lets hostel students rate their daily mess meals, log complaints, and generate weekly reports highlighting the worst-rated dishes.
---
Problem It Solves
Hostel students eat mess food every day but have no structured way to give feedback. Complaints are informal and forgotten. This app collects structured ratings and complaints, persists them in a CSV file, and produces data-driven reports — so recurring problems become visible.
---
Features
Submit meal feedback with a rating (1–5) and optional complaint
Supports Breakfast, Lunch, and Dinner
Saves all entries to `feedback.csv` (persists across sessions)
View all submitted feedback
Generate a weekly report showing:
Average rating per dish (worst first)
Breakdown by meal type
Total complaints count
---
Project Structure
```
MessFeedbackTracker/
└── src/
    ├── Main.java                        ← Entry point, console menu
    ├── model/
    │   ├── Meal.java                    ← Dish name, type, date
    │   ├── Student.java                 ← Name, roll number, hostel block
    │   └── FeedbackEntry.java           ← Rating + complaint, CSV serialization
    ├── manager/
    │   ├── FeedbackManager.java         ← Add and list feedback
    │   └── ReportGenerator.java         ← Weekly report with averages
    └── util/
        └── FileHandler.java             ← Read/write feedback.csv
```
---
How to Set Up in IntelliJ IDEA
Open IntelliJ IDEA and click File → New → Project
Select Java, choose your SDK, and click Next → Finish
Inside the project, right-click `src` → New → Package and create:
`model`
`manager`
`util`
Copy each `.java` file into its matching package folder
Place `Main.java` directly inside `src` (no package)
Right-click `Main.java` → Run 'Main.main()'
> The `feedback.csv` file will be created automatically in your project's root directory the first time you submit feedback.
---
How to Run from Terminal
```bash
# From the src/ directory:
javac -d ../out model/Meal.java model/Student.java model/FeedbackEntry.java
javac -d ../out -cp ../out util/FileHandler.java
javac -d ../out -cp ../out manager/FeedbackManager.java manager/ReportGenerator.java
javac -d ../out -cp ../out Main.java

cd ../out
java Main
```
---
Sample Session
```
============================================
       MESS FEEDBACK & RATING TRACKER
============================================

--- Student Login ---
Enter your name        : Rohan Sharma
Enter your roll number : 22BCE1234
Enter your hostel block: A

Welcome, Rohan Sharma!

========= MAIN MENU =========
  1. Submit meal feedback
  2. View all feedback
  3. Generate weekly report
  4. Switch student
  5. Exit
==============================

Enter your choice: 1

--- Submit Feedback ---
Select meal type:
  1. Breakfast
  2. Lunch
  3. Dinner
Choice: 2
Enter dish name (e.g. Dal Makhani): Rajma Chawal
Enter date (YYYY-MM-DD) or press Enter for today [2026-03-26]:
Rate this meal (1 = terrible, 5 = excellent): 2
Any complaint? (press Enter to skip): Too watery, no salt

Feedback saved successfully!
```
---
Sample Report Output
```
========================================
        WEEKLY MESS FEEDBACK REPORT
========================================
Dish                      Avg Rating Votes
----------------------------------------
Rajma Chawal              2.00       3      [##...]
Poha                      2.50       2      [##...]
Dal Makhani               3.75       4      [####.]
Paneer Butter Masala      4.50       2      [####.]
----------------------------------------
Most complained dish : Rajma Chawal (avg 2.00/5)

Average rating by meal type:
  Lunch        : 2.80 / 5
  Breakfast    : 3.20 / 5
  Dinner       : 4.10 / 5

Total entries with complaints : 4 / 11
========================================
```
---
Technologies Used
Java (compatible with Java 8 and above)
Core concepts: OOP, Collections (`ArrayList`, `HashMap`), File I/O (`BufferedReader`, `BufferedWriter`)
No external libraries required
---
Author
Created as a BYOP (Bring Your Own Project) submission for the Programming in Java course.
