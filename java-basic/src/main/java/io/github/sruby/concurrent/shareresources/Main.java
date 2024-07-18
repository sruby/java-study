package io.github.sruby.concurrent.shareresources;

/**
 * A SharedResource class with a value field and synchronized increment() and decrement() methods.
 * Two thread classes: IncrementThread and DecrementThread, each operating on the same SharedResource instance.
 * The Main class creates a single SharedResource instance and two threads that share this resource.
 * Both threads are started and then joined, ensuring they complete before the final value is printed.
 * The synchronized keyword in the SharedResource methods ensures that only one thread can access these methods at a time, preventing race conditions.
 */
public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        IncrementThread incrementThread = new IncrementThread(resource);
        DecrementThread decrementThread = new DecrementThread(resource);

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value: " + resource.getValue());
    }
}