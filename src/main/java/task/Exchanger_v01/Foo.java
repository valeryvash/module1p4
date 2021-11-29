package task.Exchanger_v01;

import java.util.concurrent.Exchanger;

public class Foo {
    private static Exchanger<Boolean> exc1 = new Exchanger<>();
    private static Exchanger<Boolean> exc2 = new Exchanger<>();

    public Foo() {}

    public void first() {

        print("first");
        try {
            exc1.exchange(true);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void second() {
        try {
            exc1.exchange(true);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        print("second");
        try {
            exc2.exchange(true);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void third() {
        try {
            exc2.exchange(true);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        print("third");

    }

    private void print(String s){
        System.out.print(s);
    }
}
