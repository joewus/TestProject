public class Account {
    private int Id;
    private String Name;
    private int Balance;

    public Account(int Id, String Name, int InitialBalance) {
        this.Id = Id;
        this.Name = Name;
        this.Balance = InitialBalance;
    }

    public int getId() {
        return Id;
    }

    public int getId(Account account) {
        return this.Id;
    }

    public String getName() {
        return Name;
    }

    public int getBalance() {
        return Balance;
    }

    public int getBalance(Account account) {
        return this.Balance;
    }

    public void setBalance(int balance) {
        this.Balance = balance;
    }

    public void deposit(int amount) {
        this.Balance += amount;
    }

    public void withdraw(int amount) {
        this.Balance -= amount;
    }

}