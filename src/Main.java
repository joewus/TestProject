public class Main {
    public static void main(String[] args) {
        // Create two accounts
        Account account1 = new Account(123, "Chang", 100);
        Account account2 = new Account(456, "Luiz", 50);

        // Create AccountService for account1 (AccountService extends Account)
        AccountService accountService1 = new AccountService();


        // Transfer between account1 and account2 using the transfer method
        accountService1.transfer(account1, account2); // Pass both accounts for the transfer

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        accountService1.withdraw(account1);
        System.out.println(accountService1.getBalance(account2));
        // Close the scanner
        accountService1.closeScanner();
    }
}
