package io.github.sruby.concurrent.collection;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 创建一个 Callable 对象
        Callable<String> callableTask = () -> {
            // 模拟一个耗时的操作
            Thread.sleep(2000);
            return "Callable task completed";
        };

        // 提交 Callable 任务给线程池
        Future<String> future = executorService.submit(callableTask);

        // 在这里可以执行其他任务

        try {
            // 获取 Callable 任务的结果
            String result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executorService.shutdown();
    }
}
