package io.github.sruby.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
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
                lock.unlock();
                System.out.println("Thread 1 released the lock again");
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread 2 acquired the lock");

                // 执行一些操作...

            } finally {
                lock.unlock();
                System.out.println("Thread 2 released the lock");
            }
        }).start();
    }
}
