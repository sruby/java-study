package io.github.sruby.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisBlockingQueueExampleTake {
    private final RBlockingQueue<String> queue;
    private final RedissonClient redisson;

    public RedisBlockingQueueExampleTake() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
        queue = redisson.getBlockingQueue("myQueue");
    }

    public void addData(String data) throws InterruptedException {
        queue.put(data);
        System.out.println("add data");
    }

    public void processData() throws InterruptedException {
        while (true) {
            String data1 = queue.take();
            String data2 = queue.take();
            String data3 = queue.take();
            if (data1 != null && data2 != null && data3 != null) {
                System.out.println("Processing data: " + data1 + ", " + data2 + ", " + data3);
            }
            Thread.sleep(1000);
            System.out.println("end");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        RedisBlockingQueueExampleTake example = new RedisBlockingQueueExampleTake();
        new Thread(() -> {
            try {
                example.addData("data1");
                Thread.sleep(1000);
                example.addData("data2");
                Thread.sleep(1000);
                example.addData("data3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        example.processData();
    }
}
