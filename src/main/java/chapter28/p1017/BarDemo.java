package chapter28.p1017;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Threads run");

        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");
    }
}

class MyThread implements Runnable {
    CyclicBarrier cb;
    String name;

    public MyThread(CyclicBarrier cb, String name) {
        this.cb = cb;
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread name: " + name);

        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(e);
        }
    }
}

class BarAction implements Runnable{
    @Override
    public void run() {
        System.out.println("Barrier reached");
    }
}
