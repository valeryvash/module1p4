package video.multithreading2.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator2 {
    private static final AtomicInteger counter = new AtomicInteger();

    public static /*synchronized*/ int nextInt() {
        return counter.getAndIncrement();
    }

    public static void main(String[] args) throws Exception{
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 1000; j++) {nextInt();}
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread t:
             threads) {
            t.join();
        }

        System.out.println("counter final value : " + counter.get());
    }

}
