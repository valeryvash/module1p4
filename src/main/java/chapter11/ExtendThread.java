package chapter11;

class NewThread2 extends Thread {

    public NewThread2() {
        super("Demonstration thread");
        System.out.println("subsidiary thread: " + this);
        start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("subsidiary thread " + i);
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            System.out.println("subsidiary thread interrupted");
        }
        System.out.println("subsidiary thread end");
    }

}

public class ExtendThread {
    public static void main(String[] args) {
        new NewThread2();

        try{
            for (int i = 5; i > 0; i--) {
                System.out.println("Main thread " + i);
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread end");
    }
}
