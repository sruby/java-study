package io.github.sruby.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockLeakageExample {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread 1 acquired the lock");

                // 可重入锁允许同一个线程多次获取同一个锁
                lock.lock();
                System.out.println("Thread 1 acquired the lock again");

                // 执行一些操作...

            } finally {
                lock.unlock();
                System.out.println("Thread 1 released the lock");

                // 第二次释放锁
//                lock.unlock();
//                System.out.println("Thread 1 released the lock again");
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

//        无法获得锁，一直等待
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread 2 acquired the lock");

                // 模拟执行一些操作...

            } finally {
                // 错误地只调用了一次unlock()方法
                lock.unlock();
                System.out.println("Thread 2 released the lock");
            }
        }).start();
    }
}
