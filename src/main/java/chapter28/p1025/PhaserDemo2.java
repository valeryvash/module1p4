package chapter28.p1025;

import java.util.concurrent.Phaser;

class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        this.numPhases = phaseCount - 1;
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println("Phase " + phase + " completed.");
        if (phase == this.numPhases || registeredParties == 0 ){
            return true;
        } else {
            return false;
        }
    }
}

public class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phsr = new MyPhaser(1, 4);

        System.out.println("Threads start");

        new MyThread(phsr, "A");
        new MyThread(phsr, "B");
        new MyThread(phsr, "C");

        while (!phsr.isTerminated()) {
            phsr.arriveAndAwaitAdvance();
        }

        System.out.println("Phase synchronization completed");
    }
}

class MyThread implements Runnable {
    Phaser phsr;
    String name;

    MyThread(Phaser p,String n) {
        this.phsr = p;
        this.name = n;
        this.phsr.register();
        new Thread(this).start();
    }

    @Override
    public void run() {

        while (!phsr.isTerminated()) {
            System.out.println("Thread " + this.name + " begins the phase " + this.phsr.getPhase());
            phsr.arriveAndAwaitAdvance();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}