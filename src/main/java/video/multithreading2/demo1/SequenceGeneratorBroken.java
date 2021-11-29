package video.multithreading2.demo1;

import java.util.ArrayList;
import java.util.List;

public class SequenceGeneratorBroken {
    private static volatile int counter = 0;

    public static /*synchronized*/ int nextInt() {
        return counter++;
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

        System.out.println("counter final value : " + counter);
    }

}
