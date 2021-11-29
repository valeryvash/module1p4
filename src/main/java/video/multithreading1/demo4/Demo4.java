package video.multithreading1.demo4;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);

        new DepositThread(account).start();

        System.out.println("Calling waitAndWithDraw()... ");

        account.waitAndWithDraw(50_000_000L);

        System.out.println("waitAndWithDraw() finished");
    }

    private static class DepositThread extends Thread{
        private final Account account;

        @Override
        public void run() {
            for (long i = 0; i < 50_000_000; i++) {
                account.deposit(1);
            }
        }

        public DepositThread(Account account) {
            this.account = account;
        }
    }
}
