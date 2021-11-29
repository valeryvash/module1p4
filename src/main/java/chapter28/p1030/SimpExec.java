package chapter28.p1030;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpExec {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Threads start");

        es.execute(new MyThread("A",cdl));
        es.execute(new MyThread("B",cdl2));
        es.execute(new MyThread("C",cdl3));
        es.execute(new MyThread("D",cdl4));

        try {
            cdl.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        es.shutdown();
        System.out.println("Threads shutdown");
    }
}

class MyThread implements Runnable {
    String name;
    CountDownLatch cdl;

    public MyThread(String name, CountDownLatch cdl) {
        this.name = name;
        this.cdl = cdl;

        new Thread(this);
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(this.name + " : " + i);
            cdl.countDown();
        }
    }
}

