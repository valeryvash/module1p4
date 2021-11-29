package chapter11;

import java.util.ArrayList;
import java.util.List;

class Callme2 {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller2 implements Runnable {
    String msg;
    Callme2 target;
    Thread t;

    public Caller2(Callme2 target, String msg) {
        this.target = target;
        this.msg = msg;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        synchronized (target){
            target.call(msg);
        }
    }
}

public class Sync1 {
    public static void main(String[] args) {
        Callme2 target = new Callme2();

        Caller2 ob1 = new Caller2(target, "Welcome ");
        Caller2 ob2 = new Caller2(target, "to synchronized ");
        Caller2 ob3 = new Caller2(target, "world!");


        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
