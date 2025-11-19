import java.util.Scanner; 

public class SimpleQuizGame {

    public static void main(String[] args) {
        // This initialize Scanner object to capture standard input from the keyboard
        Scanner scanner = new Scanner(System.in);
        
        // This nitialize variables to track the user's score and store the current answer
        int score = 0;
        final int TOTAL_QUESTIONS = 5; // Constant for the total number of questions
        char userAnswer;

        // print the Quiz Introduction
        System.out.println("******************************************");
        System.out.println("Welcome to the Myanmar General Knowledge Quiz!");
        System.out.println("Please select the correct option (A, B, C, or D).");
        System.out.println("******************************************\n");


        // QUESTION 1: Implementation using if-else control structure

        System.out.println("1. What is the capital city of Myanmar?");
        System.out.println("A. Yangon");
        System.out.println("B. Mandalay");
        System.out.println("C. Naypyidaw");
        System.out.println("D. Bagan");
        System.out.print("Your Answer: ");
        
        // Accept the user input, normalize to uppercase to handle case-insensitivity, then extract first char from the user input
        userAnswer = scanner.next().toUpperCase().charAt(0);

        // Verify answer using conditional if-else logic
        if (userAnswer == 'C') {
            System.out.println("Correct! Naypyidaw became the capital in 2005.\n");
            score++; // Increment the score
        } else if (userAnswer == 'A' || userAnswer == 'B' || userAnswer == 'D') {
            System.out.println("Wrong. The correct answer is C (Naypyidaw).\n");
        } else {
            System.out.println("Invalid input provided.\n");
        }


        // QUESTION 2: Implementation using switch-case statement

        System.out.println("2. What is the official currency of Myanmar?");
        System.out.println("A. Baht");
        System.out.println("B. Dollar");
        System.out.println("C. Rupee");
        System.out.println("D. Kyat");
        System.out.print("Your Answer: ");
        
        userAnswer = scanner.next().toUpperCase().charAt(0);

        // Verify answer using switch-case logic for multi-branch selection
        switch (userAnswer) {
            case 'D':
                System.out.println("Correct! The Kyat is the official currency.\n");
                score++;
                break;
            case 'A':
            case 'B':
            case 'C':
                System.out.println("Wrong. The correct answer is D (Kyat).\n");
                break;
            default:
                System.out.println("Invalid input provided.\n");
        }


        // QUESTION 3: Implementation using if-else control structure

        System.out.println("3. Which is the longest river in Myanmar?");
        System.out.println("A. Ayeyarwady River");
        System.out.println("B. Sittaung River");
        System.out.println("C. Thanlwin River");
        System.out.println("D. Chindwin River");
        System.out.print("Your Answer: ");
        
        userAnswer = scanner.next().toUpperCase().charAt(0);

        if (userAnswer == 'A') {
            System.out.println("Correct! The Ayeyarwady is the principal river.\n");
            score++;
        } else if (userAnswer == 'B' || userAnswer == 'C' || userAnswer == 'D') {
            System.out.println("Wrong. The correct answer is A (Ayeyarwady River).\n");
        } else {
            System.out.println("Invalid input provided.\n");
        }


        // QUESTION 4: Implementation using switch-case statement

        System.out.println("4. Which famous pagoda is located in Yangon?");
        System.out.println("A. Kyaiktiyo Pagoda");
        System.out.println("B. Shwedagon Pagoda");
        System.out.println("C. Kuthodaw Pagoda");
        System.out.println("D. Mahamuni Pagoda");
        System.out.print("Your Answer: ");
        
        userAnswer = scanner.next().toUpperCase().charAt(0);

        switch (userAnswer) {
            case 'B':
                System.out.println("Correct! The Shwedagon is in Yangon.\n");
                score++;
                break;
            case 'A':
            case 'C':
            case 'D':
                System.out.println("Wrong. The correct answer is B (Shwedagon Pagoda).\n");
                break;
            default:
                System.out.println("Invalid input provided.\n");
        }

        // ---------------------------------------------------------
        // QUESTION 5: Implementation using if-else control structure
        // ---------------------------------------------------------
        System.out.println("5. What is the traditional Burmese greeting?");
        System.out.println("A. Mingalabar");
        System.out.println("B. Sawasdee");
        System.out.println("C. Namaste");
        System.out.println("D. Ni Hao");
        System.out.print("Your Answer: ");
        
        userAnswer = scanner.next().toUpperCase().charAt(0);

        if (userAnswer == 'A') {
            System.out.println("Correct! Mingalabar is the formal greeting.\n");
            score++;
        } else {
            System.out.println("Wrong. The correct answer is A (Mingalabar).\n");
        }


        // SCORE CALCULATION AND OUTPUT

        // Calculate percentage by casting score to double for floating-point division
        double percentage = ((double) score / TOTAL_QUESTIONS) * 100;

        // printing the final score
        System.out.println("******************************************");
        System.out.println("Quiz Finished!");
        System.out.println("Total Correct Answers: " + score + " / " + TOTAL_QUESTIONS);
        System.out.println("Final Percentage: " + percentage + "%");
        System.out.println("******************************************");

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}