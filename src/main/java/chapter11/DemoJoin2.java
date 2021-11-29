package chapter11;


class NewThread5 implements Runnable {
    String name;
    Thread t;

    public NewThread5(String name, int priority) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New thread " + t );
        t.setPriority(priority);
        System.out.println("Priority : " + t.getPriority());
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


public class DemoJoin2 {
    public static void main(String[] args) {
        NewThread5 ob1 = new NewThread5("One",Thread.MIN_PRIORITY);
        NewThread5 ob2 = new NewThread5("Two",Thread.NORM_PRIORITY);
        NewThread5 ob3 = new NewThread5("Three",Thread.MAX_PRIORITY);

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
