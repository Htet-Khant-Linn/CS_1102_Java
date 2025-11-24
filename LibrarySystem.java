import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // define arrays to store book data (Capacity for 100 books)
        String[] titles = new String[100];
        String[] authors = new String[100];
        int[] quantities = new int[100];
        
        // create variable to keep track of how many unique books are currently in the library
        int bookCount = 0;
        int choice = 0;

        System.out.println("=======================================");
        System.out.println("Welcome to the Simple Library System");
        System.out.println("=======================================");

        // Create main Loop - Keeps the program running until the user chooses to exit that is 4
        while (choice != 4) {
            // Display Menu Options
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // This is input Validation which check if the user actually entered a number
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
                continue; // Skip the rest of the loop and show menu again
            }

            // Switch statement to handle menu seletion
            switch (choice) {
                case 1: // To add books
                    System.out.print("Enter Book Title: ");
                    String titleToAdd = scanner.nextLine().trim();
                    boolean exists = false;

                    // Loop to check if book already exists
                    for (int i = 0; i < bookCount; i++) {
                        if (titles[i].equalsIgnoreCase(titleToAdd)) {
                            System.out.println("Book exists! Updating quantity...");
                            System.out.print("Enter quantity to add: ");
                            int qty = scanner.nextInt();
                            quantities[i] += qty; // Update existing quantity
                            exists = true;
                            System.out.println("Quantity updated successfully.");
                            break; // Exit the loop early since we found the book
                        }
                    }

                    // If it is a new book add it to the array
                    if (!exists) {
                        if (bookCount < 100) {
                            titles[bookCount] = titleToAdd;
                            System.out.print("Enter Author Name: ");
                            authors[bookCount] = scanner.nextLine();
                            System.out.print("Enter Quantity: ");
                            quantities[bookCount] = scanner.nextInt();
                            bookCount++; // Increment the counter of unique books
                            System.out.println("New book added successfully.");
                        } else {
                            System.out.println("Error: Library is full. Cannot add more books.");
                        }
                    }
                    break;

                case 2: // This is for borrwing the book
                    System.out.print("Enter Book Title to Borrow: ");
                    String titleToBorrow = scanner.nextLine().trim();
                    boolean foundToBorrow = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (titles[i].equalsIgnoreCase(titleToBorrow)) {
                            foundToBorrow = true;
                            System.out.print("Enter number of books to borrow: ");
                            int qtyToBorrow = scanner.nextInt();

                            // Check availability using If-Else
                            if (qtyToBorrow <= quantities[i]) {
                                quantities[i] -= qtyToBorrow;
                                System.out.println("You have successfully borrowed " + qtyToBorrow + " copy(ies).");
                            } else {
                                System.out.println("Error: Insufficient stock. Only " + quantities[i] + " available.");
                            }
                            break;
                        }
                    }
                    if (!foundToBorrow) {
                        System.out.println("Error: Book not found in the library.");
                    }
                    break;

                case 3: // This is to return the books
                    System.out.print("Enter Book Title to Return: ");
                    String titleToReturn = scanner.nextLine().trim();
                    boolean foundToReturn = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (titles[i].equalsIgnoreCase(titleToReturn)) {
                            foundToReturn = true;
                            System.out.print("Enter number of books to return: ");
                            int qtyToReturn = scanner.nextInt();
                            
                            quantities[i] += qtyToReturn; // Add back to stock
                            System.out.println("You have successfully returned " + qtyToReturn + " copy(ies).");
                            break;
                        }
                    }
                    if (!foundToReturn) {
                        System.out.println("Error: This book does not belong to our library system.");
                    }
                    break;

                case 4: // exit from the library system
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }
        scanner.close();
    }
}