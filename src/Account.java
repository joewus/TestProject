public class Account {
    public int Id;
    private String Name;
    private int Balance;

    public Account() {

    }

    public Account(int Id, String Name, int InitialBalance) {
        this.Id = Id;
        this.Name = Name;
        this.Balance = InitialBalance;
    }

    public int getId() {
        return Id;
    }

//    public String getName() {
//        return Name;
//    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        this.Balance = balance;
    }

}