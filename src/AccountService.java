import java.util.Scanner;

public class AccountService extends Account {
    private Scanner scanner = new Scanner(System.in);

    // Constructor for AccountService class
    public AccountService() {
        super(); // Calls the parent class (Account) constructor
    }

    // Deposits the specified amount into this account
    public void deposit(Account account){
        System.out.print("Enter amount to deposit: ");
        int amount = scanner.nextInt();

        if (amount > 0) {
            setBalance(account.getBalance() + amount); // Update balance using setBalance
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraws the specified amount from this account
    public void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();

        if (amount > 0 && amount <= getBalance()) {
            setBalance(account.getBalance() - amount); // Update balance using setBalance
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Transfers the specified amount from one account to another
    public void transfer(Account fromAccount, Account toAccount) {
        System.out.print("Enter amount to transfer: ");
        int amount = scanner.nextInt();

        if (amount > 0 && amount <= fromAccount.getBalance()) {
            fromAccount.setBalance(fromAccount.getBalance() - amount); // Deduct from current account
            toAccount.setBalance(toAccount.getBalance() + amount); // Add to the recipient account
            System.out.println("Transferred: " + amount + " to Account ID: " + toAccount.getId());
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
