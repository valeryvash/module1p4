package chapter28.p1015;

import java.util.concurrent.CountDownLatch;

public class CDLDmo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);

        System.out.println("Main thread run");

        new MyThread(cdl);

        try {
            cdl.await();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("Main thread complete");
    }
}

class MyThread implements Runnable {
    CountDownLatch latch;

    public MyThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            latch.countDown();
        }
    }
}
