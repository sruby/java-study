package io.github.sruby.cache.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author sruby on 2020-12-3 21:11
 */
@Slf4j
public class RedissonTest {
    private Config config = new Config();
    private RedissonClient redisson;
    private RedissonReactiveClient redissonReactiveClient;
    private RedissonRxClient redissonRxClient;
    private String myLockName;
    String newValue = "newValue";

    @Before
    public void before(){
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
        redissonReactiveClient = Redisson.createReactive(config);
        redissonRxClient = Redisson.createRx(config);
        myLockName = "myLock";
    }

    @Test
    public void testGetAtomicLong(){
        RAtomicLong atomicLong = redisson.getAtomicLong("myLong");
        atomicLong.set(3);
        atomicLong.compareAndSet(3,401);
    }

    @Test
    public void testRBucket(){
        RBucket<MyValue> redissonBucket = redisson.getBucket("str");
        redissonBucket.set(MyValue.builder().myValue("test").build());
        MyValue myValue = redissonBucket.get();
        log.info("result:{}",myValue);
    }

    @Test
    public void testLock(){
        RLock testLock = redisson.getLock("testLock");
        testLock.lock(10,TimeUnit.MINUTES);
    }

    @Test
    public void testRLock_oneMainThread() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(
            () -> {
                log.info("one Thread start");
                RLock myLock = redisson.getLock(myLockName);
                myLock.lock(10, TimeUnit.SECONDS);
                log.info("one Thread get lock");

                RSet<String> dualipatedCheckSet = redisson.getSet("dualipatedCheckSet");
                dualipatedCheckSet.clear();
                log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (dualipatedCheckSet.contains(newValue)){
                    return;
                }else {
                    dualipatedCheckSet.add(newValue);
                }
                log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
                myLock.unlock();
                log.info("one Thread unlock");
                log.info("save to db");

        });
        service.execute(
                ()->{
                    log.info("anotherThread start");
                    RLock myLock = redisson.getLock(myLockName);
                    myLock.lock(10, TimeUnit.SECONDS);
                    log.info("anotherThread get lock");

                    RSet<String> dualipatedCheckSet = redisson.getSet("dualipatedCheckSet");
                    log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (dualipatedCheckSet.contains(newValue)){
                        return;
                    }else {
                        dualipatedCheckSet.add(newValue);
                    }
                    log.info("dualipatedCheckSet2:{}",dualipatedCheckSet);

                    myLock.unlock();
                    log.info("anotherThread save to db");
                }
        );

        TimeUnit.SECONDS.sleep(5);
        log.info("shutdown");
        service.shutdown();
    }

    @Test
    public void testRLock_twoMainThread_one() {
        log.info("one Thread start");
        RLock myLock = redisson.getLock(myLockName);
        myLock.lock(20, TimeUnit.SECONDS);
        log.info("one Thread get lock");

        RSet<String> dualipatedCheckSet = redisson.getSet("dualipatedCheckSet");
        dualipatedCheckSet.clear();
        log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dualipatedCheckSet.contains(newValue)){
            return;
        }else {
            dualipatedCheckSet.add(newValue);
        }
        log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
        myLock.unlock();
        log.info("one Thread unlock");
        log.info("save to db");
    }

    @Test
    public void testRLock_twoMainThread_another() throws InterruptedException {

        log.info("anotherThread start");
        RLock myLock = redisson.getLock(myLockName);
        myLock.lock(20, TimeUnit.SECONDS);
        log.info("anotherThread get lock");

        RSet<String> dualipatedCheckSet = redisson.getSet("dualipatedCheckSet");
        log.info("dualipatedCheckSet:{}",dualipatedCheckSet);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dualipatedCheckSet.contains(newValue)){
            return;
        }else {
            dualipatedCheckSet.add(newValue);
        }
        log.info("dualipatedCheckSet2:{}",dualipatedCheckSet);

        myLock.unlock();
        log.info("anotherThread save to db");

    }
}
