public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        Account account1 = new Account(100, "Luiz", 15);
        accountService.addAccount(account1);
        Account account2 = new Account(202, "Chang", 1000);
        accountService.addAccount(account2);

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

        accountService.balance(100);
        accountService.deposit(100);
        accountService.withdraw(100);
        accountService.transfer(100, 202);

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

        accountService.closeScanner();

    }
}