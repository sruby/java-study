package io.github.sruby.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在某些情况下，即使使用了公平锁，线程获取锁的顺序也不一定会按照它们启动的顺序。
 * 公平锁只是尽量保证线程获取锁的顺序是公平的，但并不能保证绝对的顺序。
 */
public class FairLockDemo {
    private static Lock lock = new ReentrantLock(true); // 创建公平锁

    public static void main(String[] args) {
        // 创建并启动多个线程
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new Worker(), "Worker " + i);
            thread.start();
        }
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is trying to acquire the lock.");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " has acquired the lock.");
                // 模拟线程执行一段时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " has released the lock.");
            }
        }
    }
}
