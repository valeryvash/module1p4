package chapter11;

class NewThread6 implements Runnable{
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread6(String name) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New thread " + t);
        suspendFlag = false;
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + " : " + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }
        System.out.println(name + " completed.");
    }

    synchronized void mysuspend() {
        suspendFlag = true;
    }

    synchronized void myresume() {
        suspendFlag = false;
        notify();
    }

}


public class SuspendedResume {
    public static void main(String[] args) {
        NewThread6 ob1 = new NewThread6("One");
        NewThread6 ob2 = new NewThread6("Two");

        try {
            Thread.sleep(1000L);
            ob1.mysuspend();
            System.out.println("Suspend thread one");
            Thread.sleep(1000L);
            ob1.myresume();
            System.out.println("Resume thread one");
            ob2.mysuspend();
            System.out.println("Suspend thread two");
            Thread.sleep(1000L);
            ob2.myresume();
            System.out.println("Resume thread two");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        try {
            System.out.println("Waiting for threads complete");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread completed");
    }
}
