package task.CountDownLatch_v01;

import java.util.concurrent.Semaphore;

public class Foo {
//    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);

    public Foo() {}

    public void first() {
        print("first");
        s2.release();
    }

    public void second() {
        try {
            s2.acquire();
        } catch (InterruptedException e) {
            System.err.println("2nd interrupted");
        }
        print("second");
        s3.release();
    }

    public void third() {
        try {
            s3.acquire();
        } catch (InterruptedException e) {
            System.err.println("3rd interrupted");
        }
        print("third");

    }

    private void print(String s){
        System.out.print(s);
    }

}
