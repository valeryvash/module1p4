package chapter28.p1050;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FJExperiment {
    public static void main(String[] args) {
        int pLevel;
        int threshold;

        if (args.length != 2) {
            System.out.println("Usage: FJExperiment parallelism threshold");
            return;
        }

        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());


        pLevel = Integer.parseInt(args[0]);
        threshold = Integer.parseInt(args[1]);

        long beginT, endT;

        ForkJoinPool fjp = new ForkJoinPool(pLevel);

        System.out.println("getParallelism() method call result: " + fjp.getParallelism());

        System.out.println("ForkJoinPool.getCommonPoolParallelism() method call result: "
                + ForkJoinPool.getCommonPoolParallelism());

        double[] nums = new double[1_000_000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        Transform task = new Transform(nums, 0, nums.length, threshold);

        beginT = System.nanoTime();

        fjp.invoke(task);

        endT = System.nanoTime();

        System.out.println("Parallelism level: " + pLevel);

        System.out.println("Threshold value: "+ threshold);

        System.out.println("Elapsed time: " + (endT - beginT)/1000 + " microsec");

        System.out.println();

    }
}

class Transform extends RecursiveAction {

    int seqThreshold;

    double[] data;

    int start, end;

    public Transform(double[] data, int start, int end, int t) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.seqThreshold = t;
    }

    protected void compute() {
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                if ((data[i] % 2) == 0) {
                    data[i] = Math.sqrt(data[i]);
                } else {
                    data[i] = Math.cbrt(data[i]);
                }
            }
        }  else {
            int middle = (start + end) / 2;

            invokeAll(
                    new Transform(data, start, middle,seqThreshold),
                    new Transform(data, middle, end, seqThreshold));
        }
    }
}