import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandler {
    private final Scanner scanner = new Scanner(System.in);

    // Method to handle valid input for amount
    public int getValidAmount(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt(); // Get user input
        } catch (InputMismatchException e) {
            scanner.next(); // Clear invalid input from the scanner buffer
            return -1; // Return -1 to indicate invalid input
        }
    }
}
