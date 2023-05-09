package io.github.sruby.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedisBlockingQueueExamplePoll {
    private final RBlockingQueue<String> queue;
    private final RedissonClient redisson;

    public RedisBlockingQueueExamplePoll() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
        queue = redisson.getBlockingQueue("myQueue");
    }

    public void addData(String data) throws InterruptedException {
        queue.put(data);
        System.out.println("add data");
    }

    public void processData() throws Exception {
        String data1 = queue.poll(3, TimeUnit.SECONDS);
        String data2 = queue.poll(3, TimeUnit.SECONDS);
        String data3 = queue.poll(3, TimeUnit.SECONDS);
        if (data1 != null && data2 != null && data3 != null) {
            System.out.println("Processing data: " + data1 + ", " + data2 + ", " + data3);
        } else {
            throw new Exception("timeout");
        }
        Thread.sleep(1000);
        System.out.println("end");
    }

/**
 * success
 */
//    public static void main(String[] args) throws Exception {
//        RedisBlockingQueueExamplePoll example = new RedisBlockingQueueExamplePoll();
//        new Thread(() -> {
//            try {
//                example.addData("data1");
//                Thread.sleep(1000);
//                example.addData("data2");
//                Thread.sleep(1000);
//                example.addData("data3");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        example.processData();
//    }

    /**
     * exception
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RedisBlockingQueueExamplePoll example = new RedisBlockingQueueExamplePoll();
        new Thread(() -> {
            try {
                example.addData("data1");
                Thread.sleep(1000);
                example.addData("data2");
                Thread.sleep(1000);
//                example.addData("data3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        example.processData();
    }
}
