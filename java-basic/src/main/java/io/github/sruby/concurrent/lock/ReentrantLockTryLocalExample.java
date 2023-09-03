package io.github.sruby.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTryLocalExample {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 尝试获取锁
        if (lock.tryLock()) {
            try {
                System.out.println("获取到锁，执行任务");
                // 模拟执行任务
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("未获取到锁，执行其他逻辑");
        }

        // 在一定时间内尝试获取锁
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    System.out.println("获取到锁，执行任务");
                    // 模拟执行任务
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("未获取到锁，执行其他逻辑");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
