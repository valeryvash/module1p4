package task.Phaser_v01;

import java.util.concurrent.Phaser;

public class Foo {
//    Phaser ph1 = new Phaser(2);
    Phaser ph2 = new Phaser(2);
    Phaser ph3 = new Phaser(2);

    public Foo() {}

    public void first() {
        print("first");

        ph2.arriveAndAwaitAdvance();
    }

    public void second() {
        ph2.arriveAndAwaitAdvance();
        print("second");
        ph3.arriveAndAwaitAdvance();
    }

    public void third() {
        ph3.arriveAndAwaitAdvance();
        print("third");

    }

    private void print(String s){
        System.out.print(s);
    }

}
