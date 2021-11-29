package task.initial;

public class FooTest {

    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();

//        new Thread(() -> foo.first());






        Thread.sleep(1000L);

        System.out.println("\nMain thread complete");
    }
}
