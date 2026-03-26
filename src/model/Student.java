package model;

public class Student {
    private String name;
    private String rollNumber;
    private String hostelBlock;

    public Student(String name, String rollNumber, String hostelBlock) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.hostelBlock = hostelBlock;
    }

    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getHostelBlock() { return hostelBlock; }

    @Override
    public String toString() {
        return name + " (" + rollNumber + ") - Block " + hostelBlock;
    }
}
