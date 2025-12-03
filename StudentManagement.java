import java.util.Scanner;

public class StudentManagement {

    
    // STATIC VARIABLES (Shared Memory)
    // use parallel arrays to act as our "database" for up to 100 students
    static int[] studentIds = new int[100];
    static String[] studentNames = new String[100];
    static int[] studentAges = new int[100];
    static String[] studentGrades = new String[100]; 
    // storing grades as String (e.g., "A", "B")
    
    // static variable keeps track of how many students we actually have
    static int totalStudents = 0;
    
    // single static Scanner to be used by all methods
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        System.out.println("================================");
        System.out.println("Student Record Management System");
        System.out.println("================================");

        while (choice != 4) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Error Handling: check if input is a number
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the 'enter' key
            } else {
                System.out.println("Error: Invalid Input. Please enter a number.");
                scanner.nextLine(); // clear the bad input
                continue;
            }

            // use Switch to call specific STATIC methods
            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Error: Please select option 1-4.");
            }
        }
        scanner.close();
    }

   
    // STATIC METHODS
    
    // method to add a student
    public static void addNewStudent() {
        if (totalStudents >= 100) {
            System.out.println("Error: Database is full.");
            return;
        } // this will return error if the database exceed 100 students

        System.out.println("\n--- Add New Student ---");
        
        // input ID
        System.out.print("Enter Student ID (Number): ");
        if (scanner.hasNextInt()) {
            int newId = scanner.nextInt();
            scanner.nextLine(); // clear buffer
            
            // check for duplicate ID
            for (int i = 0; i < totalStudents; i++) {
                if (studentIds[i] == newId) {
                    System.out.println("Error: A student with this ID already exists.");
                    return;
                }
            }
            studentIds[totalStudents] = newId;
        } else {
            System.out.println("Error: ID must be a number.");
            scanner.nextLine();
            return;
        }

        // input student name
        System.out.print("Enter Student Name: ");
        studentNames[totalStudents] = scanner.nextLine();

        // input student age
        System.out.print("Enter Student Age: ");
        if (scanner.hasNextInt()) {
            studentAges[totalStudents] = scanner.nextInt();
            scanner.nextLine(); 
        } else {
            System.out.println("Error: Age must be a number.");
            scanner.nextLine();
            return; // Cancel operation
        }

        // input grade
        System.out.print("Enter Student Grade (e.g., A, B, C): ");
        studentGrades[totalStudents] = scanner.nextLine();

        totalStudents++; // increase count
        System.out.println("Student added successfully!");
    }

    // method to update a student
    public static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter the ID of the student to update: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Error: Invalid ID format.");
            scanner.nextLine();
            return;
        }
        
        int searchId = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < totalStudents; i++) {
            if (studentIds[i] == searchId) {
                // if found the student, ask for new details
                System.out.println("Student found: " + studentNames[i]);
                
                System.out.print("Enter New Name: ");
                studentNames[i] = scanner.nextLine();
                
                System.out.print("Enter New Age: ");
                if (scanner.hasNextInt()) {
                    studentAges[i] = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    // default to 0 if they type nonsense, to prevent crash
                    studentAges[i] = 0; 
                    scanner.nextLine();
                }
                
                System.out.print("Enter New Grade: ");
                studentGrades[i] = scanner.nextLine();
                
                System.out.println("Student information updated!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Error: Student ID not found."); // return error if student ID is not found
        }
    }

    // method to view all students
    public static void viewStudentDetails() {
        System.out.println("\n--- Student List ---");
        if (totalStudents == 0) {
            System.out.println("No records found.");
        } else {
            System.out.println("ID\tName\t\tAge\tGrade");
            System.out.println("------------------------------------");
            for (int i = 0; i < totalStudents; i++) {
                System.out.println(studentIds[i] + "\t" + studentNames[i] + "\t\t" + studentAges[i] + "\t" + studentGrades[i]);
            }
        }
    }
}