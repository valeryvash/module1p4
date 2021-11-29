package task.initial;

public class Foo {
    static volatile boolean flagOne =false;
    static volatile boolean flagTwo =false;


    public Foo() {
    }

    public void first(Runnable runnable) {

        print("first");
        flagOne = true;
        runnable.notifyAll();
    }

    public void second(Runnable runnable) {
        while (true) {
            if (flagOne) {
                runnable.notify();
                break;
            } else {
                try {
                    runnable.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        print("second");
        flagTwo = true;
        runnable.notifyAll();
    }

    public void third(Runnable runnable) {
        while (true) {
            if (flagTwo) {
                runnable.notify();
                break;
            } else {
                try {
                    runnable.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        print("third");

    }

    private void print(String s) {
        System.out.print(s);
    }
}
