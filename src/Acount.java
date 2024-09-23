public class Acount {
    private int Id;
    private String Name;
    private int Balance;

    public Acount(int Id, String Name, int InitialBalance) {
        this.Id = Id;
        this.Name = Name;
        this.Balance = InitialBalance;
    }

    public void deposit(int amount) {
        this.Balance += amount;
    }

    public void withdraw(int amount) {
        this.Balance -= amount;
    }

    public void checkBalance() {
        System.out.println("Account ID: " + this.Id);
        System.out.println("Account Name: " + this.Name);
        System.out.println("Account Balance: " + this.Balance);
    }
    


}