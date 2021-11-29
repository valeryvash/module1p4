package video.multithreading1.demo3;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(100_000L);
        System.out.println("Begin balance = " + account.getBalance());

        Thread withdrawThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);

        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();


        System.out.println("End balance =   " + account.getBalance());
    }


    private static class WithdrawThread extends Thread{

        private final Account account;

        @Override
        public void run() {
            for (long i = 0; i < 50_000; i++) {
                account.withdraw(1);
            }
        }

        public WithdrawThread(Account account) {
            this.account = account;
        }
    }


    private static class DepositThread extends Thread{
        private final Account account;

        @Override
        public void run() {
            for (long i = 0; i < 50_000; i++) {
                account.deposit(1);
            }
        }

        public DepositThread(Account account) {
            this.account = account;
        }
    }

}
