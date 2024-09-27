import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountService extends Account {

    public AccountService() {
        super();
    }

    ExceptionHandler exceptionHandler = new ExceptionHandler();

    // Creating an instance of the Scanner class
    private Scanner scanner = new Scanner(System.in);

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
        if (amount > 0) {
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
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdraw amount.");
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

        int amount = exceptionHandler.getValidAmount("Enter amount to transfer: ");
        if (amount > 0 && amount <= fromAccount.getBalance()) {
            fromAccount.setBalance(fromAccount.getBalance() - amount); // Withdraw from source account
            toAccount.setBalance(toAccount.getBalance() + amount);     // Deposit to target account
            System.out.println("Transferred from Id " + fromAccount.getId() + ", amount: " + amount + " to Id " + toAccount.getId());
        } else {
            System.out.println("Your transfer amount is invalid due to insufficient funds.");
        }
    }

    // Closes the scanner to prevent resource leaks
    public void closeScanner() {
        if (scanner != null) {
            scanner.close(); // Close the scanner safely
        }
    }
}