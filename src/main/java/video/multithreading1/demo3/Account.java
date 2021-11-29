package video.multithreading1.demo3;

public class Account {
    private long balance;

    public  Account(){
        this(0L);}

    public Account(long balance) {
        this.balance = balance;}

    public long getBalance(){
        return this.balance;}

    public void deposit(long amount){
        checkAmountNonNegative(amount);
        synchronized (this){
            balance += amount;
        }
    }

    public void withdraw(long amount) {
        checkAmountNonNegative(amount);
        synchronized (this) {
            if (balance < amount) {
                throw new IllegalArgumentException("not enough money");
            } else {
                this.balance -= amount;
            }
        }
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }
}
