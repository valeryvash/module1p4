package video.multithreading1.demo2;

public class Demo2 {
    public static void main(String[] args) throws Exception {
        Thread worker = new WorkerThread();
        worker.setDaemon(true);
        Thread sleeper = new SleeperThread();
        sleeper.setDaemon(true);

        System.out.println("\nStarting threads");
        worker.start();
        sleeper.start();

        Thread.sleep(100L);

//        System.out.println("\nInterrupting threads");
//        worker.interrupt();
//        sleeper.interrupt();
//
//        System.out.println("\nJoining threads");
//        worker.join();
//        sleeper.join();

        System.out.println("\nAll done");

    }

    private static class WorkerThread extends Thread {
        @Override
        public void run() {
            long sum = 0;
            for (int i = 0; i < 1_000_000_000; i++) {
                sum += i;
                if (i % 100 == 0 && isInterrupted()) {
                    System.out.println("Loop interupted at i =" + i);
                    break;
                }
            }
        }
    }

    private static class SleeperThread extends Thread {
        @Override
        public void run() {
            try{
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
        }
    }



}
