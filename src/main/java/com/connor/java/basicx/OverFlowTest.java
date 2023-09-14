package com.connor.java.basicx;

public class OverFlowTest {
    public static void main(String[] args) {
        // 使用线程池执行，开启一个线程。
        new Thread(() -> {
            // 无限循环
            while (true) {
                // sleep 5000
                try {
                    System.out.println(System.currentTimeMillis());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 制造一个stackoverflow
        stackOverFlow();

        // 无限循环
        while (true) {
            // sleep 5000
            try {
                Thread.sleep(5000);
                // 打印当前时间
                System.out.println(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void stackOverFlow() {
        stackOverFlow();
    }
}
