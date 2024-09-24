import java.util.Scanner; // Import the Scanner class

public class AccountService extends Account{

    // Creating an instance of the Scanner class
    private Scanner scanner = new Scanner(System.in);

    public AccountService(Account account) {
        super(account.getId(), account.getName(), account.getBalance());
    }


    // Deposits the specified amount into the given account

    public void deposit(Account account) {
        System.out.print("Enter amount to deposit: ");

        // Taking deposit amount from the user
        int amount = scanner.nextInt();

        // Ensuring the user enters a valid deposit amount
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraws the specified amount from the given account.

    public void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");

        // Taking withdrawal amount from the user
        int amount = scanner.nextInt();

        // Ensuring the user enters a valid withdrawal amount
        if (amount > 0) {
            account.withdraw(amount);
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }

    public void transfer(Account fromAccount, Account toAccount) {
        System.out.print("Enter amount to transfer: ");

        // Taking transfer amount from the user
        int amount = scanner.nextInt();

//        if (amount > 0 && amount <= getBalance(fromAccount)) {
//            fromAccount.withdraw(amount);
//            toAccount.deposit(amount);
//
//            System.out.println("Transferred: " + amount + " to Account ID: " + getId(toAccount));
//        } else {
//            System.out.println("Invalid transfer amount.");
//        }

    }

    //  Closes the scanner to prevent resource leaks.

    public void closeScanner() {
        scanner.close();
    }
}
