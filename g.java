import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface GradingOperations {
    void addStudent(String studentID, String name);
    void addGrade(String studentID, double grade);
    void viewGrades(String studentID);
    double calculateAverage(String studentID);
}

abstract class Student {
    protected String studentID;
    protected String name;
    protected List<Double> grades;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }
}

class ConcreteStudent extends Student {
    public ConcreteStudent(String studentID, String name) {
        super(studentID, name);
    }
}

class GradingSystem implements GradingOperations {
    private Map<String, Student> students;

    public GradingSystem() {
        students = new HashMap<>();
    }

    @Override
    public void addStudent(String studentID, String name) {
        students.put(studentID, new ConcreteStudent(studentID, name));
    }

    @Override
    public void addGrade(String studentID, double grade) {
        Student student = students.get(studentID);
        if (student != null) {
            student.addGrade(grade);
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void viewGrades(String studentID) {
        Student student = students.get(studentID);
        if (student != null) {
            System.out.println("Grades for " + student.getName() + ": " + student.getGrades());
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public double calculateAverage(String studentID) {
        Student student = students.get(studentID);
        if (student != null) {
            List<Double> grades = student.getGrades();
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            return grades.size() > 0 ? sum / grades.size() : 0;
        } else {
            System.out.println("Student not found.");
            return 0;
        }
    }
}

public class g {
    public static void main(String[] args) {
        GradingSystem gradingSystem = new GradingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Calculate Average");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    gradingSystem.addStudent(studentID, name);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    gradingSystem.addGrade(studentID, grade);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    gradingSystem.viewGrades(studentID);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    double average = gradingSystem.calculateAverage(studentID);
                    System.out.println("Average grade: " + average);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
