import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final FileHandler fileHandler = new FileHandler();
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
            System.out.println("ID " + account.getId() + ", balance is: " + account.getBalance());
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
            System.out.println("Invalid input detected. Deposit cancelled.");
            return;  // Skip the rest of the method
        }

        if (amount <= 0) {
            System.out.println("Invalid input detected. Deposit cancelled.");
        } else {
            account.setBalance(account.getBalance() + amount);
            fileHandler.updateAccountBalance(accountId, account.getBalance());
            System.out.println("Deposited: " + amount + ". Balance is: " + account.getBalance());
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
            System.out.println("Invalid input detected. Withdraw cancelled.");
            return;  // Skip the rest of the method
        }

        if (amount <= 0) {
            System.out.println("Invalid input detected. Withdraw cancelled.");
        } else if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            account.setBalance(account.getBalance() - amount);
            fileHandler.updateAccountBalance(accountId, account.getBalance());
            System.out.println("Withdrew: " + amount + ". Balance is: " + account.getBalance());
        }
    }

    // Transfers amount from one account to another
    public void transfer(int fromAccountId, int toAccountId, FileHandler fileHandler) {
        // Check if the source and destination accounts are the same
        if (fromAccountId == toAccountId) {
            System.out.println("Transfer cancelled: Cannot transfer to the same account.");
            return; // Exit the method
        }

        // Use FileHandler to ensure both accounts are loaded
        fileHandler.loadAccountIfNotLoaded(fromAccountId, accountMap);
        fileHandler.loadAccountIfNotLoaded(toAccountId, accountMap);

        // Now that both accounts are loaded, proceed with the transfer
        Account fromAccount = accountMap.get(fromAccountId);
        Account toAccount = accountMap.get(toAccountId);

        // Check if either of the accounts is null (does not exist)
        if (fromAccount == null || toAccount == null) {
            System.out.println("Account with this ID not found.");
            return; // Exit the method if accounts are not valid
        }

        // Get the amount to transfer
        int amount = exceptionHandler.getValidAmount("Enter amount to transfer: ");

        // If the amount is -1, it means invalid input was entered
        if (amount == -1) {
            System.out.println("Invalid input detected. Transfer cancelled.");
            return;  // Skip the rest of the method
        }

        if (amount <= 0) {
            System.out.println("Invalid input detected. Transfer cancelled.");
        } else if (amount > fromAccount.getBalance()) {
            System.out.println("Insufficient funds for the transfer.");
        } else {
            // Perform the transfer
            fromAccount.setBalance(fromAccount.getBalance() - amount); // Withdraw from source account
            toAccount.setBalance(toAccount.getBalance() + amount);     // Deposit to target account
            fileHandler.updateAccountBalance(fromAccountId, fromAccount.getBalance());
            fileHandler.updateAccountBalance(toAccountId, toAccount.getBalance());
            System.out.println("Transferred from ID " + fromAccount.getId() + ", amount: " + amount + " to "
                + toAccount.getName() + "(ID " + toAccount.getId() + ")");
        }
    }

    // Closes the scanner to prevent resource leaks
    public void closeScanner() {
        scanner.close(); // Close the scanner safely
    }
}
