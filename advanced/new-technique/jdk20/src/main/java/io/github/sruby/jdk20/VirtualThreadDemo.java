package io.github.sruby.jdk20;

/**
 * virtual thread demo
 *
 * @author Sruby
 * @date 2023/8/16 23:48
 */
public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello"));
        thread.join();
    }
}
