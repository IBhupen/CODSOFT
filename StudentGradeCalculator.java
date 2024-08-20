import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;
    private static final double GRADE_A = 90;
    private static final double GRADE_B = 80;
    private static final double GRADE_C = 70;
    private static final double GRADE_D = 60;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String studentName = getStudentName(scanner);
            int[] grades = getGrades(scanner);
            double averageGrade = calculateAverageGrade(grades);
            String gradeLevel = determineGradeLevel(averageGrade);

            displayResults(studentName, averageGrade, gradeLevel);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter valid grades.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static String getStudentName(Scanner scanner) {
        System.out.print("Enter student's name: ");
        String studentName = scanner.nextLine();
        while (studentName.isEmpty() || studentName == null) {
            System.out.print("Invalid input! Please enter a valid student name: ");
            studentName = scanner.nextLine();
        }
        return studentName;
    }

    private static int[] getGrades(Scanner scanner) {
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] grades = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter grade for subject " + (i + 1) + ": ");
            grades[i] = scanner.nextInt();
            while (grades[i] < MIN_GRADE || grades[i] > MAX_GRADE) {
                System.out.print("Invalid input! Please enter a grade between " + MIN_GRADE + " and " + MAX_GRADE + ": ");
                grades[i] = scanner.nextInt();
            }
        }
        return grades;
    }

    private static double calculateAverageGrade(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    private static String determineGradeLevel(double averageGrade) {
        if (averageGrade >= GRADE_A) {
            return "A";
        } else if (averageGrade >= GRADE_B) {
            return "B";
        } else if (averageGrade >= GRADE_C) {
            return "C";
        } else if (averageGrade >= GRADE_D) {
            return "D";
        } else {
            return "F";
        }
    }

    private static void displayResults(String studentName, double averageGrade, String gradeLevel) {
        System.out.println("Student Name: " + studentName);
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("Grade Level: " + gradeLevel);
    }
}