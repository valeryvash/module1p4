package chapter11;

public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();

        System.out.println("Current execution thread: " + t);

        t.setName("MyThread");
        System.out.println("After thread name change: " + t);

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1_000L);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

    }
}
