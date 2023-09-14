package com.connor.java.basicx;

import java.util.concurrent.*;

public class ThreadTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 打印当前线程
        System.out.println("当前线程：" + Thread.currentThread().getName());

        Thread a = new Thread(()->{
            threadLocal.set(1);  // 在当前线程中设置值为1
            try {
                Thread.sleep(2000 * 2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "a");
        a.start();
        // 获取当前进程号
        // System.out.println("pid: " + ProcessHandle.current().pid());


        Runtime.getRuntime().addShutdownHook(new Thread(a::interrupt
        ));

        // 创建一个虚拟线程，打印当前线程的信息
        // Thread.startVirtualThread(()-> System.out.println("当前线程信息：" + Thread.currentThread().getName()));
        // Thread.startVirtualThread(()-> System.out.println("当前线程信息：" + Thread.currentThread().getName()));

        testFuture();

        Thread.sleep(2000);
        System.exit(1);

    }

     public static void testFuture() throws ExecutionException, InterruptedException {
         // 创建一个 ExecutorService，用于管理线程池
         ExecutorService executorService = Executors.newSingleThreadExecutor();

         // 提交一个 Callable 任务给线程池，并获取 Future 对象
         Future<String> future = executorService.submit(new MyCallable());

         // 可以继续执行其他逻辑，不会阻塞主线程

         // 调用 Future 的 get() 方法来获取任务的结果，该方法会阻塞当前线程直到任务完成
         String result = future.get();

         System.out.println("任务执行结果：" + result);

         // 关闭线程池
         executorService.shutdown();
     }

    // 自定义 Callable 实现类
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            // 执行耗时操作或其他任务逻辑
            Thread.sleep(2000);
            return "任务执行完成";
        }
    }


}