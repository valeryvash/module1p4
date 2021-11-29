#Task

You have the class:

```java
public class Foo {
  public void first(Runnable r) { print("first"); }
  public void second(Runnable r) { print("second"); }
  public void third(Runnable r) { print("third"); 
  }
```

One instance of this class will be run by several threads.

- Thread A -> first();
- Thread B -> second();
- Thread C -> third().

Result: "firstsecondthird"

We don't know the call order, buy we need to guarantee the result sequence.
