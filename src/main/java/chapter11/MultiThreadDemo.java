package chapter11;

class NewThread3 implements Runnable {
    String name;
    Thread thread;

    public NewThread3(String name) {
        this.name = name;
        thread = new Thread(this, name);
        System.out.println("New thread: " + thread);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + " : "+ i);
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            System.err.println(name + " interrupted");
        }
        System.out.println(name + " end");
    }
}

public class MultiThreadDemo {
    public static void main(String[] args) {
        new NewThread3("One");
        new NewThread3("Two");
        new NewThread3("Three");

        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread finished");
    }
}
