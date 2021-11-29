package chapter11;


class NewThread4 implements Runnable {
    String name;
    Thread t;

    public NewThread4(String name) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New thread " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + " : " + i);
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }
        System.out.println(name + " completed");
    }
}


public class DemoJoin {
    public static void main(String[] args) {
        NewThread4 ob1 = new NewThread4("One");
        NewThread4 ob2 = new NewThread4("Two");
        NewThread4 ob3 = new NewThread4("Three");

        System.out.println("Thread 'One' ran: " + ob1.t.isAlive());
        System.out.println("Thread 'Two' ran: " + ob2.t.isAlive());
        System.out.println("Thread 'Three' ran: " + ob3.t.isAlive());

        try {
            System.out.println("Waiting for threads complete.");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("The 'Main thread' interrupted.");
        }

        System.out.println("Thread 'One' ran: " + ob1.t.isAlive());
        System.out.println("Thread 'Two' ran: " + ob2.t.isAlive());
        System.out.println("Thread 'Three' ran: " + ob3.t.isAlive());

        System.out.println("The 'Main thread' completed");
    }
}
