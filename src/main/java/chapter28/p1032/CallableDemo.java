package chapter28.p1032;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        Future<Integer> f;
        Future<Double> f2;
        Future<Integer> f3;

        System.out.println("Run");

        f = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3,4));
        f3 = es.submit(new Factorial(5));

        try {
            System.out.println(f.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException exc) {
            System.err.println(exc);
        }
        es.shutdown();
        System.out.println("Complete");
    }
}

class Sum implements Callable<Integer> {
    int i = 0;

    public Sum(int i) {
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int j = 1; j <= i; j++) {
            sum += j;
        }
        return sum;
    }
}

class Hypot implements Callable<Double> {
    double a, b;

    public Hypot(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }
}

class Factorial implements Callable<Integer> {
    int stop;

    public Factorial(int stop) {
        this.stop = stop;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        for (int i = 2; i <= stop ; i++) {
            result *= i;
        }
        return result;
    }
}
