package io.github.sruby.concurrent.shareresources;

public class SharedResource {
    private int value;

    public synchronized void increment() {
        value++;
        System.out.println(Thread.currentThread().getName() + " incremented value to " + value);
    }

    public synchronized void decrement() {
        value--;
        System.out.println(Thread.currentThread().getName() + " decremented value to " + value);
    }

    public int getValue() {
        return value;
    }
}





