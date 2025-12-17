import java.util.ArrayList;
import java.util.Scanner;


// Course Class

class Course {
    // use ncapsulation, private instance variables
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int enrolledCount; // To track students in this specific course

    // static variable to track total enrollments across entire system
    private static int totalEnrolledStudents = 0;

    // constructor

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledCount = 0;
    }


    // Getters for public access to private data
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getEnrolledCount() { return enrolledCount; }

    // method to attempt enrollment (increment counters)
    public boolean addStudent() {
        if (enrolledCount < maxCapacity) {
            enrolledCount++;
            totalEnrolledStudents++; // increment static global counter
            return true;
        } else {
            return false;
        }
    }

    // static method to get the global count
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
    
    // Helper to display info
    public String toString() {
        return courseCode + ": " + courseName + " (Capacity: " + enrolledCount + "/" + maxCapacity + ")";
    }
}


// creating student class

class Student {
    // encapsulation, private instance variables
    private String name;
    private String id;
    
    // lists to store courses and corresponding grade
    // index 0 of courses matches index 0 of grades
    private ArrayList<Course> enrolledCourses;
    private ArrayList<Double> grades; 


    // constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // getters and setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }

    // Method to enroll in course
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);

        grades.add(0.0); // Initialize grade as 0.0
        System.out.println("Success: " + name + " enrolled in " + course.getCourseName());
    }

    // method to assign grade
    public void assignGrade(Course course, double grade) {
        // Find the index of the course
        int index = enrolledCourses.indexOf(course);

        if (index != -1) {
            grades.set(index, grade); // Update the grade at that index
            System.out.println("Grade of " + grade + " assigned to " + name + " for " + course.getCourseName());
        } else {
            System.out.println("Error: Student is not enrolled in this course.");
        }
    }

    // helper method to get grades (used for calculation)
    public ArrayList<Double> getGrades() {
        return grades;
    }
    
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}


// create course management class

class CourseManagement {
    // private static variables to hold "Database" of the system
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static ArrayList<Student> studentList = new ArrayList<>();

    // static Method: add new course
    public static void addCourse(String code, String name, int capacity) {
        Course newCourse = new Course(code, name, capacity);
        courseList.add(newCourse);
        System.out.println("Course added: " + name);
    }

    // static Method: Add New Student (helper for main menu)
    public static void addStudent(String name, String id) {
        Student newStudent = new Student(name, id);

        studentList.add(newStudent);
        System.out.println("Student added: " + name);
    }



    // static method: enroll student
    public static void enrollStudent(String studentId, String courseCode) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            // Check if course is full using the Course object's method
            if (course.addStudent()) {
                student.enrollInCourse(course);
            } else {
                System.out.println("Error: Course is full.");
            }
        } else {
            System.out.println("Error: Invalid Student ID or Course Code.");
        }
    }

    // static method: assign gade
    public static void assignGrade(String studentId, String courseCode, double grade) {
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            student.assignGrade(course, grade);
        } else {
            System.out.println("Error: Student or Course not found.");
        }
    }


    // static method: calculate overall grade (Average)
    public static void calculateOverallGrade(String studentId) {
        Student student = findStudent(studentId);
        if (student != null) {
            ArrayList<Double> grades = student.getGrades();
            if (grades.isEmpty()) {
                System.out.println("No grades available for " + student.getName());
                return;
            }

            double sum = 0;
            for (double g : grades) {
                sum += g;
            }
            double average = sum / grades.size();
            System.out.println("--- Report for " + student.getName() + " ---");
            System.out.println("Overall Average Grade: " + average);
        } else {
            System.out.println("Error: Student not found.");
        }
    }

    // helper methods to find object in the lists
    private static Student findStudent(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) return s;

        }
        return null;
    }


    private static Course findCourse(String code) {
        for (Course c : courseList) {
            if (c.getCourseCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }
    

    // helper to view all courses (for the admin)
    public static void listCourses() {
        System.out.println("--- Available Courses ---");
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }
}


// Create Main Interface


public class CourseManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Pre-load some data for testing
        CourseManagement.addCourse("CS101", "Intro to Java", 2); // Small capacity to test limit
        CourseManagement.addCourse("CS102", "Data Structures", 30);
        CourseManagement.addStudent("Alice Smith", "S001");
        CourseManagement.addStudent("Bob Jones", "S002");

        System.out.println("===============================================");
        System.out.println("Welcome to the Course Enrollment & Grade System");
        System.out.println("===============================================");

        while (choice != 6) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add New Course");
            System.out.println("2. Add New Student");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade");
            System.out.println("5. Calculate Student Overall Grade");
            System.out.println("6. Exit");
            System.out.println("Note: Global Enrolled Students: " + Course.getTotalEnrolledStudents());
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear newline
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1: // Add Course
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Max Capacity: ");
                    int cap = scanner.nextInt();
                    CourseManagement.addCourse(code, name, cap);
                    break;

                case 2: // Add Student
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String sId = scanner.nextLine();
                    CourseManagement.addStudent(sName, sId);
                    break;

                case 3: // Enroll
                    CourseManagement.listCourses();
                    System.out.print("Enter Student ID: ");
                    String enrollId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String enrollCode = scanner.nextLine();
                    CourseManagement.enrollStudent(enrollId, enrollCode);
                    break;

                case 4: // Assign Grade
                    System.out.print("Enter Student ID: ");
                    String gradeId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String gradeCode = scanner.nextLine();
                    System.out.print("Enter Grade (0-100): ");
                    double grade = scanner.nextDouble();
                    CourseManagement.assignGrade(gradeId, gradeCode, grade);
                    break;

                case 5: // Calculate
                    System.out.print("Enter Student ID: ");
                    String calcId = scanner.nextLine();
                    CourseManagement.calculateOverallGrade(calcId);
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Please select 1-6.");
            }
        }
        scanner.close();
    }
}