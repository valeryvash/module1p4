package chapter11;

public class NewThread implements Runnable {
    Thread t;

    NewThread(){
        t = new Thread(this, "Demonstration thread");
        System.out.println("subsidiary thread created");
        t.start();
    }

    @Override
    public void run() {
        try{
            for (int i = 5; i > 0; i--) {
                System.out.println("subsidiary thread " + i);
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            System.out.println("subsidiary thread interrupted");
        }
        System.out.println("subsidiary thread end");
    }

    public static void main(String[] args) {
        new NewThread();

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
