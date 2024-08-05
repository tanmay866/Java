import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "CourseID: " + courseID + ", CourseName: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private int[][] studentCourses;
    private int[] count;

    public Enrollment(int maxStudents, int maxCourses) {
        studentCourses = new int[maxStudents][maxCourses];
        count = new int[maxStudents];
    }

    public void enroll(int studentID, int courseID) {
        if (count[studentID] < studentCourses[studentID].length) {
            studentCourses[studentID][count[studentID]++] = courseID;
            System.out.println("Student " + studentID + " enrolled in course " + courseID);
        } else {
            System.out.println("Student " + studentID + " cannot enroll in more courses.");
        }
    }

    public void drop(int studentID, int courseID) {
        boolean found = false;
        for (int i = 0; i < count[studentID]; i++) {
            if (studentCourses[studentID][i] == courseID) {
                found = true;
                for (int j = i; j < count[studentID] - 1; j++) {
                    studentCourses[studentID][j] = studentCourses[studentID][j + 1];
                }
                count[studentID]--;
                System.out.println("Student " + studentID + " dropped course " + courseID);
                break;
            }
        }
        if (!found) {
            System.out.println("Student " + studentID + " is not enrolled in course " + courseID);
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) {
        if (count[studentID] > 0) {
            System.out.println("Student " + studentID + " is enrolled in the following courses:");
            for (int i = 0; i < count[studentID]; i++) {
                int enrolledCourseID = studentCourses[studentID][i];
                for (Course course : courseCatalog) {
                    if (course.getCourseID() == enrolledCourseID) {
                        System.out.println(course);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Student " + studentID + " is not enrolled in any courses.");
        }
    }
}

public class CourseEnrollment {
    private Course[] courseCatalog;
    private Enrollment enrollment;

    public CourseEnrollment(Course[] courseCatalog, int maxStudents, int maxCourses) {
        this.courseCatalog = courseCatalog;
        this.enrollment = new Enrollment(maxStudents, maxCourses);
    }

    public void displayCourseCatalog() {
        System.out.println("Course Catalog:");
        for (Course course : courseCatalog) {
            System.out.println(course);
        }
    }

    public static void main(String[] args) {
        Course[] courseCatalog = {
            new Course(1, "Mathematics", 3),
            new Course(2, "Physics", 4),
            new Course(3, "Chemistry", 3)
        };

        int maxStudents = 100;
        int maxCourses = 10;
        CourseEnrollment courseEnrollment = new CourseEnrollment(courseCatalog, maxStudents, maxCourses);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. View course catalog");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    courseEnrollment.displayCourseCatalog(); // Display catalog before enrolling
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    courseEnrollment.enrollment.enroll(studentID, courseID);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseID = scanner.nextInt();
                    courseEnrollment.enrollment.drop(studentID, courseID);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    courseEnrollment.enrollment.getEnrolledCourses(studentID, courseCatalog);
                    break;
                case 4:
                    courseEnrollment.displayCourseCatalog();
                    break;
                case 5:
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
