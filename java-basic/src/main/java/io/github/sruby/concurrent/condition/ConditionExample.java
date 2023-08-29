package io.github.sruby.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean isReady = false;

    public void waitForSignal() throws InterruptedException {
        lock.lock();
        try {
            while (!isReady) {
                condition.await();
            }
            // 执行等待后的逻辑
            System.out.println("Received the signal!");
        } finally {
            lock.unlock();
        }
    }

    public void sendSignal() {
        lock.lock();
        try {
            isReady = true;
            condition.signal();
            // 其他逻辑
            System.out.println("Signal sent!");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample example = new ConditionExample();

        Thread t1 = new Thread(() -> {
            try {
                example.waitForSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            example.sendSignal();
        });

        t1.start();
        Thread.sleep(1000);  // 确保 t1 先执行等待
        t2.start();

        t1.join();
        t2.join();
    }
}
