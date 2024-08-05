import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int id, String studentName) {
        studentID = id;
        name = studentName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int id, int course, char gradeChar) {
        studentID = id;
        courseID = course;
        grade = gradeChar;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int maxStudents, int maxGrades, int maxCourses) {
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        courseCredits = new int[maxCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("Maximum student limit reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Maximum grade limit reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int credits = courseCredits[grades[i].getCourseID()];
                totalPoints += gradeToPoints(grades[i].getGrade()) * credits;
                totalCredits += credits;
            }
        }
        return totalCredits > 0 ? (double) totalPoints / totalCredits : 0.0;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 4;
            case 'B':
                return 3;
            case 'C':
                return 2;
            case 'D':
                return 1;
            case 'F':
                return 0;
            default:
                return 0;
        }
    }

    public void printGradeReport(int studentID) {
        Student student = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID() == studentID) {
                student = students[i];
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Grade Report for " + student.getName() + " (ID: " + student.getStudentID() + ")");
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                System.out.println("Course ID: " + grades[i].getCourseID() + ", Grade: " + grades[i].getGrade());
            }
        }
        System.out.println("GPA: " + calculateGPA(studentID));
    }
}

public class GradingSystemMGTM {
    public static void main(String[] args) {
        int maxStudents = 100;
        int maxGrades = 1000;
        int maxCourses = 10;

        GradingSystem gradingSystem = new GradingSystem(maxStudents, maxGrades, maxCourses);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. Add grade");
            System.out.println("3. Add course credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print grade report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    gradingSystem.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter grade (A-F): ");
                    char grade = scanner.next().charAt(0);
                    gradingSystem.addGrade(new Grade(studentID, courseID, grade));
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    courseID = scanner.nextInt();
                    System.out.print("Enter course credits: ");
                    int credits = scanner.nextInt();
                    gradingSystem.addCourseCredits(courseID, credits);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentID);
                    System.out.println("GPA for student " + studentID + ": " + gpa);
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    gradingSystem.printGradeReport(studentID);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}