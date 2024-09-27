import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        Account account1 = new Account(100, "Luiz", 15);
        accountService.addAccount(account1);
        Account account2 = new Account(202, "Chang", 1000);
        accountService.addAccount(account2);

        Scanner scanner = new Scanner(System.in);
        String abortChoice;

        int userId;
        System.out.print("Please enter user ID: ");

        try {
            userId = scanner.nextShort();

            // Consume the leftover newline after nextInt()
            scanner.nextLine();

            if (accountService.accountMap.get(userId) == null) {
                System.out.println("Invalid Id");
            } else {

                do {
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
                            case 1:
                                accountService.balance(userId);
                                break;
                            case 2:
                                accountService.deposit(userId);
                                break;
                            case 3:
                                accountService.withdraw(userId);
                                break;
                            case 4:
                                System.out.print("Please enter the ID you wish to transfer to: ");
                                try {
                                    int transferId = scanner.nextInt();

                                    // Consume the leftover newline after nextInt()
                                    scanner.nextLine();

                                    accountService.transfer(userId, transferId);
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid Id");
                                    scanner.nextLine();  // Clear the invalid input from the buffer
                                    // Very importnt, pay attention to this issue!
                                    // You can comment the scanner.nextLine(); line and try to type "y"
                                    // instead of transfer id to see what happens next.
                                }
                                break;
                            case 5:
                                return;
                            default:
                                System.out.print("Invalid choice, try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid choice. Please enter a number.");
                        scanner.nextLine();  // Clear the scanner after invalid input
                        //Here we need to use nextLine instead of next! Very important!
                    }
                    System.out.print("Do you wish to continue: y | n: ");
                    abortChoice = scanner.nextLine();
                    System.out.println();

                } while (abortChoice.equalsIgnoreCase("y"));
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Id");
            scanner.next();
        }

        scanner.close();
        accountService.closeScanner();
    }
}
