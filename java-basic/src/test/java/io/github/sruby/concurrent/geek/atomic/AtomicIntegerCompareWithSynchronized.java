package io.github.sruby.concurrent.geek.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对于资源竞争严重（线程冲突严重）的情况，CAS自旋的概率会比较大，从而浪费更多的CPU资源，效率低于synchronized。
 * 随着cycleNum数量增大，Atomic性能会越差。
 * @date 2020/10/15 11:17
 */
@Slf4j
public class AtomicIntegerCompareWithSynchronized {
    private static volatile int count;
    private static AtomicInteger countAtomic = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        log.info("startTime:{}", startTime);
        int cycleNum = 2000;
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int max = 10000;
        for (int i = 0; i< cycleNum; i++){
            executorService.submit(() -> {
                for (int j=0;j<max; j++){
                    add();
                }
                latch.countDown();
            });
        }
        latch.await();
        log.info("count:{},countTime:{}",count, (System.currentTimeMillis() - startTime));
        executorService.shutdown();

        long startTime2 = System.currentTimeMillis();
        log.info("startTime2:{}", startTime2);
        CountDownLatch latch2 = new CountDownLatch(cycleNum);
        ExecutorService executorService2 = Executors.newFixedThreadPool(cycleNum);
        for (int i = 0; i< cycleNum; i++){
            executorService2.submit(() -> {
                for (int j=0;j<max; j++){
                    countAtomic.incrementAndGet();
                }
                latch2.countDown();
            });
        }
        latch2.await();
        log.info("count2:{},countTime2:{}",count, (System.currentTimeMillis() - startTime2));
        executorService2.shutdown();
    }

    public static synchronized void add(){
        count = ++count;
//        log.info("count:{}",count);
    }

}
