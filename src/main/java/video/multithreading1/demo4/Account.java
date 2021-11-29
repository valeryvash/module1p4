package video.multithreading1.demo4;

public class Account {
    private long balance;

    public Account(){
        this(0L);}

    public Account(long balance) {
        this.balance = balance;}

    public long getBalance(){
        return this.balance;}

    public synchronized void deposit(long amount){
        checkAmountNonNegative(amount);
        synchronized (this){
            balance += amount;
        }
        notifyAll();
    }

    public synchronized void withdraw(long amount) {
        checkAmountNonNegative(amount);
        synchronized (this) {
            if (balance < amount) {
                throw new IllegalArgumentException("not enough money");
            } else {
                this.balance -= amount;
            }
        }
    }

    public synchronized void waitAndWithDraw(long amount) throws InterruptedException {
        checkAmountNonNegative(amount);
        while (balance < amount) {
            wait(1000L);
            System.out.println("WakeUp "+ balance);
        }
        balance -= amount;
    }

    private static void checkAmountNonNegative(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("negative amount");
        }
    }
}
