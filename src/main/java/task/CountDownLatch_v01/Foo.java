package task.CountDownLatch_v01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Foo {
//    private static Semaphore s1 = new Semaphore(1);
    private static CountDownLatch cdl2 = new CountDownLatch(1);
    private static CountDownLatch cdl3 = new CountDownLatch(1);

    public Foo() {}

    public void first() {
        print("first");
        cdl2.countDown();
    }

    public void second() {
        try {
            cdl2.await();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        print("second");
        cdl3.countDown();
    }

    public void third() {
        try {
            cdl3.await();
        } catch (InterruptedException e) {
            System.err.println("3rd interrupted");
        }
        print("third");

    }

    private void print(String s){
        System.out.print(s);
    }

}
