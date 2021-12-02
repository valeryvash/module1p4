package chapter28.p1037;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new LockThread(lock, "A");
        new LockThread(lock, "B");

    }
}

class Shared {
    static int count = 0;
}

class LockThread implements Runnable {
    String name;
    ReentrantLock rl;

    public LockThread(ReentrantLock rl, String name) {
        this.name = name;
        this.rl = rl;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Run of thread" + name);

        try {
            System.out.println("Thread " + name + " waiting for counter lock");
            rl.lock();
            System.out.println("Thread " + name + " locks the counter");

            System.out.println("Thread " + name + " is waiting");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            System.out.println("Thread " + name + " unlock the counter");
            rl.unlock();
        }
    }
}
