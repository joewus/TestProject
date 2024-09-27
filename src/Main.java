import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        Account account1 = new Account(100, "Luiz", 15);
        accountService.addAccount(account1);
        Account account2 = new Account(202, "Chang", 1000);
        accountService.addAccount(account2);

        Scanner scanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        String abortChoice;
        int userId = 0;
        System.out.print("Please enter user ID: ");
        try {
            userId = scanner.nextShort();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Id");
            scanner.next();
        }

        do {

            System.out.print("Please choose the operation you would like to perform: \n 1. Check balance \n 2. Deposit \n 3. Withdrawal \n 4. Transfer \n");
            System.out.print("Your choice: ");

            try{
                int   userChoice = scanner.nextInt();

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
                        accountService.transfer(userId, transferId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Id");
                    }

                    break;
                default:
                    System.out.print("Invalid choice, try again.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.next();  // Clear the scanner after invalid input
                }
            System.out.print("Do you wish to continue: y | n: ");
            abortChoice = stringScanner.nextLine();
            System.out.println();

        } while (abortChoice.equalsIgnoreCase("y"));

        accountService.closeScanner();
        scanner.close();
        stringScanner.close();
    }
}