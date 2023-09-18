package io.github.sruby.concurrent.executor;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable task = new WorkerThread("Task " + i);
            threadPool.execute(task);
        }

        threadPool.shutdown();
    }
}
