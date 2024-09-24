public class Main {
    public static void main(String[] args) {
        Account account = new Account(123, "Chang", 50 );
        AccountService accountserivce = new AccountService(account);

        System.out.println(accountserivce.getBalance());
        accountserivce.deposit(accountserivce);
        System.out.println(accountserivce.getBalance());
    }
}