package video.multithreading1.demo1;

import java.util.stream.IntStream;

public class Demo1 {
    public static void main(String[] args) {
        HelloRunnable hr = new HelloRunnable();
        IntStream.range(0,20).forEach((i) ->{
//            new HelloThread().start();
//            new Thread(new HelloRunnable()).start();
//            new Thread(hr).start();
            new Thread(hr).run();
        });


        System.out.println("Hello from main thread");
    }

    private static class HelloThread extends Thread {
        @Override
        public void run() {
//            System.out.println("Hello from "+ getName());
            System.err.println("Hello from "+ getName());
        }
    }

    private static class HelloRunnable implements Runnable {
        @Override
        public void run() {
//            System.out.println("Hello from " + Thread.currentThread());
            System.err.println("Hello from " + Thread.currentThread());
        }
    }


}
