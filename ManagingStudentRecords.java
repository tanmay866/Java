import java.util.Scanner;

class Student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    public Student(int studentID, String name, int age, String department) {
        this.studentID = studentID;  // Use 'this' to refer to instance variables
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }
}

class StudentRecords {
    private Student[] students;
    private int count;
    private static final int MAX_STUDENTS = 100;

    public StudentRecords() {
        students = new Student[MAX_STUDENTS];
        count = 0;
    }

    public void addNewStudent(Scanner sc) {
        if (count < MAX_STUDENTS) {
            System.out.println("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Student Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("Enter Student Department: ");
            String department = sc.nextLine();

            students[count] = new Student(id, name, age, department);
            count++;
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public void displayAllRecords() {
        if (count == 0) {
            System.out.println("No records found.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(students[i].getStudentID() + " | " + students[i].getName() + " | " +
                        students[i].getAge() + " | " + students[i].getDepartment());
            }
        }
    }

    public void findStudentById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == id) {
                System.out.println("Student found:");
                System.out.println(students[i].getStudentID() + " | " + students[i].getName() + " | " +
                        students[i].getAge() + " | " + students[i].getDepartment());
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

public class ManagingStudentRecords {
    public static void main(String[] args) {
        StudentRecords records = new StudentRecords();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student\n2. View All Records\n3. Search Student\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    records.addNewStudent(scanner);
                    break;
                case 2:
                    records.displayAllRecords();
                    break;
                case 3:
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    records.findStudentById(searchId);
                    break;
                case 4:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
