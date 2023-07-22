package io.github.sruby.interview.examination.vivo.fintech.solution2;

import java.util.concurrent.*;

/**
 * 并行远程调用
 *
 * @author Sruby
 * @date 7/22/2023 11:10 PM
 */
public class ParallelApiCall {
    private static ExecutorService executor = Executors.newFixedThreadPool(4); // 创建一个线程池

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建callable任务
        Callable<String> task1 = createTask("API 1");
        Callable<String> task2 = createTask("API 2");
        Callable<String> task3 = createTask("API 3");
        Callable<String> task4 = createTask("API 4");

        // 提交任务并获取Future
        Future<String> future1 = executor.submit(task1);
        System.out.println("future1");
        Future<String> future2 = executor.submit(task2);
        Future<String> future3 = executor.submit(task3);
        Future<String> future4 = executor.submit(task4);

        // 获取结果
        System.out.println("Result 1: " + future1.get());
        System.out.println("Result 2: " + future2.get());
        System.out.println("Result 3: " + future3.get());
        System.out.println("Result 4: " + future4.get());


        System.out.println("completed");
        // 关闭线程池
        executor.shutdown();
    }

    private static Callable<String> createTask(String apiName) {
        return () -> {
            preProcess(apiName);
            String result = externalApiCall(apiName);
            postProcess(result);
            return result;
        };
    }

    private static void preProcess(String apiName) {
        System.out.println("Preprocessing for " + apiName);
        // 实际的预处理逻辑
    }

    private static String externalApiCall(String apiName) throws InterruptedException {
        System.out.println("Calling " + apiName);
        Thread.sleep(200); // 模拟200ms的API调用
        return "Result from " + apiName;
    }

    private static void postProcess(String result) {
        System.out.println("Postprocessing for " + result);
        // 实际的后处理逻辑
    }
}
