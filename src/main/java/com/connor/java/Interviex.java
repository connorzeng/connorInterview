package com.connor.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interviex {
    public static void main(String[] args) {
        // 创建一个固定大小为5的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交任务给线程池执行
        for (int i = 0; i < 10; i++) {
            Task task = new Task(i);
            executor.execute(task);
        }

        // 关闭线程池
        executor.shutdown();
    }

    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is being executed by " + Thread.currentThread().getName());
        }
    }
}
