import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class AccountService {
    ExceptionHandler exceptionHandler = new ExceptionHandler();
    fileHandler filehandler = new fileHandler(); // Instance of fileHandler for file I/O

    // Creating an instance of the Scanner class
    private final Scanner scanner = new Scanner(System.in);

    // Map to store accounts with their IDs as keys
    public Map<Integer, Account> accountMap = new HashMap<>();

    // Constructor to load accounts from the file at the start
    public AccountService() {
        loadAccounts();
    }

    // Method to add an account to the map
    public void addAccount(Account account) {
        accountMap.put(account.getId(), account); // Add the account using the correct method
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
        if (amount == -1) return;

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
        if (amount == -1) return;

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

        if (fromAccount == null || toAccount == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        int amount = exceptionHandler.getValidAmount("Enter amount to transfer: ");
        if (amount == -1) return;

        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
        } else if (amount > fromAccount.getBalance()) {
            System.out.println("Insufficient funds for the transfer.");
        } else {
            fromAccount.setBalance(fromAccount.getBalance() - amount); // Withdraw from source account
            toAccount.setBalance(toAccount.getBalance() + amount);     // Deposit to target account
            System.out.println("Transferred from Id " + fromAccount.getId() + ", amount: " + amount + " to "
                + toAccount.getName() + "(Id " + toAccount.getId() + ")");
        }
    }

    // Method to load accounts from file using fileHandler
    private void loadAccounts() {
        List<Account> accounts = filehandler.readAccounts();
        for (Account account : accounts) {
            accountMap.put(account.getId(), account);
        }
    }

    // Method to save accounts to file using fileHandler
    public void saveAccounts() {
        filehandler.writeAccounts(new ArrayList<>(accountMap.values()));
    }

    // Closes the scanner to prevent resource leaks
    public void closeScanner() {
        scanner.close();
    }
}
