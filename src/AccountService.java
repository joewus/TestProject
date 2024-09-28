import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountService {
    ExceptionHandler exceptionHandler = new ExceptionHandler();

    // Creating an instance of the Scanner class
    private final Scanner scanner = new Scanner(System.in);

    // Map to store accounts with their IDs as keys
    public Map<Integer, Account> accountMap = new HashMap<>();

    // Method to add an account to the map
    public void addAccount(Account account) {
        accountMap.put(account.getId(), account);
    }

    // Shows the balance in the given account
    public void balance(int accountId) {
        Account account = accountMap.get(accountId);
        if (account != null) {
            System.out.println("Balance is: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Deposits the specified amount into the given account
    public void deposit(int accountId) {
        Account account = accountMap.get(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        int amount = exceptionHandler.getValidAmount("Enter amount to deposit: ");

        // If amount is -1, it means invalid input was provided, so we skip further processing
        if (amount == -1) {
            return;  // Skip the rest of the method
        }

        if (amount <= 0) {
            System.out.println("Cannot deposit a negative or zero amount.");
        } else {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited: " + amount);
        }
    }

    // Withdraws the specified amount from the given account
    public void withdraw(int accountId) {
        Account account = accountMap.get(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        int amount = exceptionHandler.getValidAmount("Enter amount to withdraw: ");

        // If amount is -1, it means invalid input was provided, so we skip further processing
        if (amount == -1) {
            return;  // Skip the rest of the method
        }

        if (amount <= 0) {
            System.out.println("Cannot withdraw a negative or zero amount.");
        } else if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        }
    }

    // Transfers amount from one account to another
    public void transfer(int fromAccountId, int toAccountId) {

        Account fromAccount = accountMap.get(fromAccountId);
        Account toAccount = accountMap.get(toAccountId);

        // Check if either of the accounts is null (does not exist)
        if (fromAccount == null || toAccount == null) {
            System.out.println("One or both accounts not found.");
            return; // Exit the method if accounts are not valid
        }

        // Get the amount to transfer
        int amount = exceptionHandler.getValidAmount("Enter amount to transfer: ");

        // If the amount is -1, it means invalid input was entered
        if (amount == -1) {
            return;  // Skip further processing if input was invalid
        }

        // Handle insufficient funds or invalid transfer amount
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
        } else if (amount > fromAccount.getBalance()) {
            System.out.println("Insufficient funds for the transfer.");
        } else {
            // Perform the transfer
            fromAccount.setBalance(fromAccount.getBalance() - amount); // Withdraw from source account
            toAccount.setBalance(toAccount.getBalance() + amount);     // Deposit to target account
            System.out.println("Transferred from Id " + fromAccount.getId() + ", amount: " + amount + " to "
                    + toAccount.getName() + "(Id " + toAccount.getId() + ")");

        }
    }

    // Closes the scanner to prevent resource leaks
    public void closeScanner() {
        scanner.close(); // Close the scanner safely
    }
}
