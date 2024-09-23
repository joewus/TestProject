import java.util.Scanner; // Import the Scanner class

public class AccountService {
    // Creating an instance of the Scanner class
    private Scanner scanner = new Scanner(System.in);

    /*
      Deposits the specified amount into the given account.
     */
    public void deposit(Account account) {
        System.out.print("Enter amount to deposit: ");
        // Taking deposit amount from the user
        int amount = scanner.nextInt();

        // Ensuring the user enters a valid deposit amount
        if (amount > 0) {
            account.deposit(amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /*
     Withdraws the specified amount from the given account.
     */
    public void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt(); // Taking withdrawal amount from the user

        // Ensuring the user enters a valid withdrawal amount
        if (amount > 0) {
            account.withdraw(amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }

    /*
     * @param fromAccount The account to transfer money from.
     * @param toAccount   The account to transfer money to.
     */
    public void transfer(Account fromAccount, Account toAccount) {
        System.out.print("Enter amount to transfer: ");
        // Taking transfer amount from the user
        int amount = scanner.nextInt();

        /*Checking if the user has enough balance for the transfer but i can not
        implment it since we need a get balance in the class Account before
         */
        /*
        if (amount > 0 && amount <= fromAccount.getBalance()) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred: " + amount + " to Account ID: " + toAccount.getId());
        } else {
            System.out.println("Invalid transfer amount.");
        }

         */
    }

    /**
     * Checks the balance of the given account.

     * @param account The account whose balance is to be checked.
     */
    public void checkBalance(Account account) {
        account.checkBalance();
    }

    /*
      Closes the scanner to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }
}
