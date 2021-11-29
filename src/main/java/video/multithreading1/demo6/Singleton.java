package video.multithreading1.demo6;

public class Singleton {
    private int foo;
    private String bar;

    private Singleton() {
        foo = 13;
        bar = "zap";
    }

    private static /*volatile*/ Singleton instance;

    public synchronized static Singleton getInstance() {
        if (instance == null) {
//            synchronized (Singleton.class) {
//                if (instance == null) {
                    instance = new Singleton();
//                }
//            }
        }
        return instance;
    }
}
