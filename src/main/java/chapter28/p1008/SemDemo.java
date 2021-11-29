package chapter28.p1008;

import java.util.concurrent.Semaphore;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new IncThread(sem, "A");
        new DecThread(sem, "B");
    }
}
class Shared{
    static int count = 0;
}

class IncThread implements Runnable {
    String name;
    Semaphore semaphore;
    IncThread(Semaphore s, String n){
        this.semaphore = s;
        this.name = n;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Start of thread " + name);
        try {
            System.out.println("Thread " + name + " pending for approval");
            semaphore.acquire();
            System.out.println("Thread " + name + " get approval");
            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name + " : " + Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println("Thread " + name + " release the semaphore");
        semaphore.release();
    }
}

class DecThread implements Runnable{
    String name;
    Semaphore semaphore;

    DecThread(Semaphore s, String n) {
        this.semaphore = s;
        this.name = n;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Start of thread " + name);
        try {
            System.out.println("Thread " + name + " pending for approval");
            semaphore.acquire();
            System.out.println("Thread " + name + " get approval");
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + " : " + Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println("Thread " + name + " release the semaphore");
        semaphore.release();

    }
}
