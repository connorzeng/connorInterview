package com.connor.java.basicx;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(()->{

            try {
                Thread.sleep(2000 * 2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "a");
        a.start();
        // 获取当前进程号
        System.out.println("pid: " + ProcessHandle.current().pid());


        Runtime.getRuntime().addShutdownHook(new Thread(a::interrupt
        ));

        // 创建一个虚拟线程，打印当前线程的信息
        Thread.startVirtualThread(()->{
            System.out.println("当前线程信息：" + Thread.currentThread().getName());
        });

        Thread.sleep(2000);
        System.exit(1);
    }
}