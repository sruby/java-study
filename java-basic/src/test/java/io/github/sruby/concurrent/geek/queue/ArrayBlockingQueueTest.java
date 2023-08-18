package io.github.sruby.concurrent.geek.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: ArrayBlockingQueue test
 * @author: sruby
 * @create: 2020-06-09 16:30
 */
@Slf4j
public class ArrayBlockingQueueTest {
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(2);


    @Test
    public void add(){
        for (int i = 0; i < 10; i++){
            arrayBlockingQueue.add(i);
        }
    }
    @Test
    public void put() throws InterruptedException {
        for (int i = 0; i < 10; i++){
            arrayBlockingQueue.put(i);
            log.debug("arrayBlockingQueue:{}",arrayBlockingQueue);
        }
    }
    @Test
    public void offer() throws InterruptedException {
        for (int i = 0; i < 10; i++){
            arrayBlockingQueue.offer(i,2, TimeUnit.SECONDS);
            log.debug("arrayBlockingQueue:{}",arrayBlockingQueue);
        }
    }

//    The method you are referring to is offer(E e) in the Queue interface.
//    This method inserts the specified element into the queue if it is possible to do so immediately
//    without violating capacity restrictions. It returns true if the element was successfully added,
//    and false otherwise.
//    优先使用 offer 添加元素，避免队列满的时候抛出异常
    @Test
    public void offer2() {
        for (int i = 0; i < 10; i++){
            boolean offer = arrayBlockingQueue.offer(i);
            log.debug("arrayBlockingQueue:{},offer:{}",arrayBlockingQueue,offer);
        }
    }
}
