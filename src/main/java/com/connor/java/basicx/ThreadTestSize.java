package com.connor.java.basicx;


import java.util.concurrent.*;

/**
 * 测试线程池数量
 */
public class ThreadTestSize {

    public static void main(String[] args) throws InterruptedException {

        // 1. 新建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                8,10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());

        System.out.println("1111111-初始化");
        // 打印线程池活跃线程数
        System.out.println("活跃线程数：" + threadPoolExecutor.getActiveCount());
        // 打印队列大小
        System.out.println("队列大小：" + threadPoolExecutor.getQueue().size());

        // 添加任务
        for (int i = 0; i < 6; i++) {
            threadPoolExecutor.execute(() -> {
                // System.out.println("当前线程：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 抛出异常
                throw new RuntimeException("测试异常");
            });
        }
        System.out.println("222222-打满队列和核心线程池");
        Thread.sleep(1000 * 1);
        // 打印线程池活跃线程数
        System.out.println("活跃线程数：" + threadPoolExecutor.getActiveCount());
        // 打印队列大小
        System.out.println("队列大小：" + threadPoolExecutor.getQueue().size());

        System.out.println("333333-再次添加任务，超出队列大小，创建新线程");
        threadPoolExecutor.execute(() -> {
            // System.out.println("当前线程：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 抛出异常
            throw new RuntimeException("测试异常");
        });

        // 打印线程池活跃线程数
        System.out.println("活跃线程数：" + threadPoolExecutor.getActiveCount());
        // 打印队列大小
        System.out.println("队列大小：" + threadPoolExecutor.getQueue().size());

        // 延迟20秒
        // 延迟20秒
        Thread.sleep(1000 * 30);

        System.out.println("44444-等待所有任务执行完成，查看线程池");
        // 打印线程池活跃线程数
        System.out.println("活跃线程数：" + threadPoolExecutor.getActiveCount());
        // 打印队列大小
        System.out.println("队列大小：" + threadPoolExecutor.getQueue().size());
    }
}
