package chapter28.p1022;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        int curPhase;

        System.out.println("Threads run");
        new MyThread(phaser, "A");
        new MyThread(phaser, "B");
        new MyThread(phaser, "C");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");

        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");

        phaser.arriveAndDeregister();

        if (phaser.isTerminated()) {
            System.out.println("Phaser completed");
        }
    }
}

class MyThread implements Runnable {
    Phaser phaser;
    String name;

    MyThread(Phaser p, String n) {
        this.phaser = p;
        this.name = n;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread " + this.name + " begin 1st phase");
        this.phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("Thread " + this.name + " begin 2nd phase");
        this.phaser.arriveAndAwaitAdvance();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println("Thread " + this.name + " begin 3rd phase");
        this.phaser.arriveAndDeregister();

    }
}
