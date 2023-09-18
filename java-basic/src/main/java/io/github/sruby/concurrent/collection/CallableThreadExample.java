package io.github.sruby.concurrent.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableThreadExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个 Callable 对象
        Callable<String> callableTask = () -> {
            // 模拟一个耗时的操作
            Thread.sleep(2000);
            return "Callable task completed";
        };

        FutureTask<String> stringFutureTask = new FutureTask<>(callableTask);
        Thread thread = new Thread(stringFutureTask);
        thread.start();

//        阻塞等待
        String result = stringFutureTask.get();

        log.info(result);
        log.info("end");
    }
}
