package practice_3;

public class BankAccount {
    String owner;
    double balance;

    BankAccount(String someOwner, double someBalance) {
        this.owner = someOwner;
        this.balance = someBalance;
    }
    String getOwner() {
        return this.owner;
    }
    double getBalance() {
        return this.balance;
    }
    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }
    void deposit(double amount) {
        balance += amount;
        System.out.println("Внесено на счёт: " + amount);
    }
    void withdraw(double amount) {
        balance -= amount;
        System.out.println("Снято с счёта: " + amount);
    }
    void printBalance() {
        System.out.println("Баланс счёта: " + this.balance);
    }
}
