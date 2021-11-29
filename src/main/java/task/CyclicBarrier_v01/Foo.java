package task.CyclicBarrier_v01;

import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.CyclicBarrier;

public class Foo {
    CyclicBarrier cb1 = new CyclicBarrier(2);
    CyclicBarrier cb2 = new CyclicBarrier(2);


    public Foo() {}

    public void first() {

        print("first");
        try {
            cb1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void second() {
        try {
            cb1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        print("second");
        try {
            cb2.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            cb2.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        print("third");

    }

    private void print(String s){
        System.out.print(s);
    }
}
