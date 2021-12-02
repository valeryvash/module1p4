package chapter28.p1039;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}

class Shared {
//    static Integer ai = new Integer(0);
    static AtomicInteger ai = new AtomicInteger(0);
}

class AtomThread implements Runnable {
    String name;

    public AtomThread(String name) {
        this.name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread start");

        for (int i = 1; i <= 3 ; i++) {
            System.err.println("Thread " + name + " counter " + i + " get: "
                    + Shared.ai.getAndSet(i));
//                    + Shared.ai.getAndIncrement());
//                    + Shared.ai);
//            Shared.ai = i;
        }
    }
}