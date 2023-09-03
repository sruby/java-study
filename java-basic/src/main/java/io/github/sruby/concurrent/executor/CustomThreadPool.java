package io.github.sruby.concurrent.executor;

import java.util.LinkedList;
import java.util.List;

public class CustomThreadPool {
    private final int poolSize;
    private final List<WorkerThread> threads;
    private final LinkedList<Runnable> taskQueue;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        threads = new LinkedList<>();
        taskQueue = new LinkedList<>();

        for (int i = 0; i < poolSize; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.addLast(task);
            taskQueue.notify();
        }
    }

    public void shutdown() {
        for (WorkerThread thread : threads) {
            thread.stopThread();
        }
    }

    private class WorkerThread extends Thread {
        private boolean running = true;

        public void run() {
//            在WorkerThread线程的run方法中，它会不断地从任务队列中获取任务并执行，
//            直到线程被停止（通过stopThread方法）。如果任务队列为空，线程会等待，直到有新的任务被添加到队列中。
            while (running) {
                Runnable task;

                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    task = taskQueue.removeFirst();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopThread() {
            running = false;
        }
    }
}

class WorkerThread implements Runnable {
    private String name;

    public WorkerThread(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " completed");
    }
}
