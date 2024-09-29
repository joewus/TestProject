import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler(); // Create FileHandler object

        boolean validIdEntered = false;  // Flag to track if a valid ID is entered
        int userId = -1;  // Initialize userId to an invalid value

        // Loop to keep asking for user ID until a valid one is entered or user decides to exit
        while (!validIdEntered) {
            try {
                System.out.print("Please enter user ID: ");
                userId = scanner.nextInt();

                // Search for the account by userId in the file
                Account account = fileHandler.findAccountById(userId);

                if (account != null) {
                    // If the account is found, add it to accountService
                    accountService.addAccount(account);
                    validIdEntered = true; // Exit the loop once a valid account is found
                } else {
                    // If the account is not found
                    System.out.println("Account with this ID not found.");
                    // Ask user if they want to continue or exit
                    if (askUserToContinue(scanner)) {
                        return;  // Exit the program if the user does not want to continue
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID input. Please enter a valid numeric ID.");
                scanner.nextLine(); // Clear the invalid input from the scanner buffer

                // Ask the user if they want to continue or exit
                if (askUserToContinue(scanner)) {
                    return;  // Exit the program if the user does not want to continue
                }
            }
        }

        // Once a valid ID is entered, allow user to choose operations
        boolean continueOperations = true;
        while (continueOperations) {
            System.out.print("""
                Please choose the operation you would like to perform:\s
                 1. Check balance\s
                 2. Deposit\s
                 3. Withdrawal\s
                 4. Transfer\s
                 5. Exit\s
                """);
            System.out.print("Your choice: ");

            try {
                int userChoice = scanner.nextInt();

                // Consume the leftover newline after nextInt()
                scanner.nextLine();

                switch (userChoice) {
                    case 1 -> accountService.balance(userId);
                    case 2 -> accountService.deposit(userId);
                    case 3 -> accountService.withdraw(userId);
                    case 4 -> {
                        System.out.print("Please enter the ID you wish to transfer to: ");
                        try {
                            int transferId = scanner.nextInt();

                            // Consume the leftover newline after nextInt()
                            scanner.nextLine();

                            // Pass FileHandler to the transfer method to load accounts if necessary
                            accountService.transfer(userId, transferId, fileHandler);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid transfer ID.");
                            scanner.nextLine();  // Clear the invalid input from the buffer
                        }
                    }
                    case 5 -> {
                        return;  // Exit the program if the user chooses to exit
                    }

                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.nextLine();  // Clear the scanner after invalid input
            }

            // Ask if the user wants to continue with operations unless they chose to exit
            if (askUserToContinue(scanner)) {
                continueOperations = false; // Reuse method to ask user if they wish to continue
            }
        }

        scanner.close();
        accountService.closeScanner();
    }

    // Helper method to ask the user if they want to continue or exit
    private static boolean askUserToContinue(Scanner scanner) {
        System.out.print("Do you wish to continue (y | n): ");
        String choice = scanner.next();
        scanner.nextLine(); // Consume the leftover newline
        return !choice.equalsIgnoreCase("y");
    }
}
