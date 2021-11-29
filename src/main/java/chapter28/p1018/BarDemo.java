package chapter28.p1018;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Threads start");

        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");

        new MyThread(cb, "x");
        new MyThread(cb, "y");
        new MyThread(cb, "z");

//        System.out.println("Main thread completed");
    }
}

class MyThread implements Runnable {
    CyclicBarrier cbar;
    String name;

    public MyThread(CyclicBarrier cbar, String name) {
        this.cbar = cbar;
        this.name = name;
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(name);

        try {
            cbar.await();
        } catch (InterruptedException e) {
            System.err.println(e);
        } catch (BrokenBarrierException e) {
            System.err.println(e);
        }
    }
}

class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Barrier reached");
    }
}
