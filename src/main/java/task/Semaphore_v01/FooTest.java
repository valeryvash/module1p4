package task.Semaphore_v01;

import java.util.function.Consumer;

public class FooTest {

    static class MyThread extends Thread {
        String name;
        Consumer<Foo> consumer;
        Foo foo;

        public MyThread(String name,Consumer<Foo> consumer,Foo foo) {
            this.name = name;
            this.consumer = consumer;
            this.foo = foo;
            new Thread(this,this.name).start();
            System.out.println(this.name + " started");
        }

        public void run(){
            consumer.accept(this.foo);
        }
    }

    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();

        Consumer<Foo> c1 = Foo::first;
        Consumer<Foo> c2 = Foo::second;
        Consumer<Foo> c3 = Foo::third;

        MyThread third = new MyThread("Third",c3,foo);
        MyThread first = new MyThread("First",c1,foo);
        MyThread second = new MyThread("Second",c2,foo);

        first.join();
        second.join();
        third.join();

        Thread.sleep(1000L);

        System.out.println("\nMain thread complete");
    }
}
