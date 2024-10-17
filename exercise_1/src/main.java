package exercise_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Random;



// Base class for Student
class Student {
    String name;

    List<Double> quizzes; // List to hold up to 15 quiz scores

    public Student(String name) {
        this.name = name;

        this.quizzes = new ArrayList<>(); // Initialize the quiz list
    }

    // Method to add a quiz score
    public void AddScore(double score) {
        if (quizzes.size() < 15) {
            quizzes.add(score);
        } else {
            System.out.println("Maximum of 15 quiz scores reached for " + name);
        }
    }

    // Method to get all quiz scores
    public List<Double> GetScores() {
        return quizzes;
    }

    // Method to calculate average quiz score for the student
    public double CalculateAverageScore() {
        if (quizzes.isEmpty()) {
            return 0.0; // Return 0 if no quiz scores are available
        }
        double sum = 0;
        for (double score : quizzes) {
            sum += score;
        }
        return sum / quizzes.size();
    }

    // Overridable method to display student info
    public void DisplayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Quiz Scores: " + quizzes);
        System.out.println("Average Quiz Score: " + CalculateAverageScore());
    }
}

// Derived class for Part-Time students
class PartTimeStudent extends Student {

    public PartTimeStudent(String name) {
        super(name);
    }

    @Override
    public void DisplayInfo() {
        System.out.println("Part-Time Student");
        super.DisplayInfo();
    }
}

// Derived class for Full-Time students with exam scores
class FullTimeStudent extends Student {
    private double exam1;
    private double exam2;

    public FullTimeStudent(String name, double exam1, double exam2) {
        super(name);
        this.exam1 = exam1;
        this.exam2 = exam2;
    }


    // Overriding to display full-time student info including exam scores
    @Override
    public void DisplayInfo() {
        System.out.println("Full-Time Student");
        super.DisplayInfo();
        System.out.println("Exam 1 Score: " + exam1);
        System.out.println("Exam 2 Score: " + exam2);
    }

    // Method to get exam scores
    public void displayExamScores() {
        System.out.println(name + "'s Exam Scores: Exam 1 = " + exam1 + ", Exam 2 = " + exam2);
    }
}

// Class to manage the session of students
class Session {
    private List<Student> students; // List to hold up to 20 students

    public Session() {
        this.students = new ArrayList<>(); // Initialize the student list
    }

    // Method to add a student to the session
    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        } else {
            System.out.println("Session is full. Cannot add more students.");
        }
    }

    // Method to calculate and print the average quiz score for each student
    public void printAverageQuizScorePerStudent() {
        System.out.println("Average Quiz Scores for Each Student:");
        for (Student student : students) {
            double avgScore = student.CalculateAverageScore();
            System.out.println(student.name + "'s average quiz score: " + avgScore);
        }
    }

    // Method to print quiz scores in ascending order for the entire session
    public void printQuizScoresAscending() {
        List<Double> allQuizScores = new ArrayList<>();
        for (Student student : students) {
            allQuizScores.addAll(student.GetScores());
        }
        Collections.sort(allQuizScores);
        System.out.println("Quiz Scores in Ascending Order: " + allQuizScores);
    }

    // Method to print names of all part-time students
    public void printPartTimeStudents() {
        System.out.println("Part-Time Students:");
        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                System.out.println(student.name);
            }
        }
    }

    // Method to print exam scores of all full-time students
    public void printFullTimeExamScores() {
        System.out.println("Full-Time Students' Exam Scores:");
        for (Student student : students) {
            if (student instanceof FullTimeStudent) {
                ((FullTimeStudent) student).displayExamScores();
            }
        }
    }
}

// Main class to test the functionality
public class main {
    public static void main(String[] args) {
        // Create a session
        Random random = new Random();
        Session session = new Session();

        // Sample list of names
        List<String> names = Arrays.asList(
                "Alex", "Jordan", "Taylor", "Queenie", "John",
                "Charlie", "Jamie", "Sam", "Jessica", "Teresa",
                "Sarah", "Jessie", "Tom", "Shawn", "Isabella",
                "Eason", "Hank", "Willy", "Lukas", "Johnson"
        );

// Decide the number of part-time and full-time students
        int totalStudents = 20;
        int numPartTime = random.nextInt(Math.min(totalStudents + 1, names.size()));
        int numFullTime = totalStudents - numPartTime;

        // Shuffle the list of names so they are random
        Collections.shuffle(names);

        // Ensure numFullTime does not exceed available names
        numFullTime = Math.min(numFullTime, names.size() - numPartTime);

        // Assign part-time students
        for (int i = 0; i < numPartTime; i++) {
            PartTimeStudent ptStudent = new PartTimeStudent(names.get(i));
            for (int j = 0; j < 15; j++) {
                ptStudent.AddScore(random.nextInt(101));
            }
            session.addStudent(ptStudent);
        }

        // Assign full-time students
        for (int i = 0; i < numFullTime; i++) {
            FullTimeStudent ftStudent = new FullTimeStudent(
                    names.get(numPartTime + i),
                    random.nextInt(101),
                    random.nextInt(101)
            );
            for (int j = 0; j < 15; j++) {
                ftStudent.AddScore(random.nextInt(101));
            }
            session.addStudent(ftStudent);
        }

        // Calculate and print average quiz scores per student
        session.printAverageQuizScorePerStudent();
        session.printQuizScoresAscending();
        // Display session details
        session.printPartTimeStudents();
        session.printFullTimeExamScores();
    }

}

