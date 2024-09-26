import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountService extends Account {

    public AccountService() {
        super();
    }

    // Creating an instance of the Scanner class
    private Scanner scanner = new Scanner(System.in);

    // Map to store accounts with their IDs as keys
    private Map<Integer, Account> accountMap = new HashMap<>();

    // Method to add an account to the map
    public void addAccount(Account account) {
        accountMap.put(account.getId(), account);
    }

    // Shows the balance in the given account
    public void balance(int accountId) {
        Account depositAccount = accountMap.get(accountId);

        System.out.println("Balance is: " + depositAccount.getBalance());
    }

    // Deposits the specified amount into the given account
    public void deposit(int accountId) {
        Account depositAccount = accountMap.get(accountId);
        System.out.print("Enter amount to deposit: ");

        // Taking deposit amount from the user
        int amount = scanner.nextInt();

        // Ensuring the user enters a valid deposit amount
        if (amount > 0) {
            depositAccount.setBalance(depositAccount.getBalance() + amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }

    }

    // Withdraws the specified amount from the given account.
    public void withdraw(int accountId) {
        Account depositAccount = accountMap.get(accountId);
        System.out.print("Enter amount to withdraw: ");

        // Taking withdrawal amount from the user
        int amount = scanner.nextInt();

        // Ensuring the user enters a valid withdrawal amount
        if (amount <= depositAccount.getBalance()) {
            depositAccount.setBalance(depositAccount.getBalance() - amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }

    public void transfer(int fromAccountId, int toAccountId) {
        // Retrieve accounts using the IDs
        Account fromAccount = accountMap.get(fromAccountId);
        Account toAccount = accountMap.get(toAccountId);

        System.out.print("Enter amount to transfer: ");

        // Taking transfer amount from the user
        int amount = scanner.nextInt();

        if (amount > 0 && amount <= fromAccount.getBalance()) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            System.out.println("Transferred from Id " + fromAccountId + ", amount: " + amount + " to Id " + toAccountId);
        } else {
            System.out.println("Your transfer amount is invalid due to insufficient funds.");
        }

    }

    //  Closes the scanner to prevent resource leaks.
    public void closeScanner() {
        scanner.close();
    }


}
