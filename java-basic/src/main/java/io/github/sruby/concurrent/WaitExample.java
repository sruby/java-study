package io.github.sruby.concurrent;

public class WaitExample {
    public static void main(String[] args) {
        final Object lock = new Object();
        
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("Thread 1 is waiting");
                    try {
                        lock.wait(); // 线程1等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1 is resumed");
                }
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("Thread 2 is notifying");
                    lock.notify(); // 唤醒等待的线程1
                }
            }
        });
        
        thread1.start();
        thread2.start();
    }
}
