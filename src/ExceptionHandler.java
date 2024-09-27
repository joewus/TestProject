import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandler {
    private Scanner scanner = new Scanner(System.in);

    // Method to handle valid input for amount
    public int getValidAmount(String prompt) {
        int amount = -1; // Initialize amount to an invalid value
        System.out.print(prompt); // Prompt user for the amount
        try {
            amount = scanner.nextInt(); // Get user input
            if (amount <= 0) {
                System.out.println("Amount must be positive."); // Inform the user if the amount is not positive
            } else {
                  return amount; // Return the valid amount
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number."); // Handle invalid input
            scanner.next(); // Clear the invalid input from the scanner buffer

        }
        return -1;
    }
}