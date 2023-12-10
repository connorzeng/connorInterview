package com.connor.java;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestAPI {

//    有一系列任务需要处理，最多 N 个并发；
//    只要有任务处理遇到错误，主程序就立即返回，输出对应错误信息；
//    等待所有任务执行成功。
//            const N = 5
//    var APIList = []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"}
//
//    func main() {
//        // impl your job scheduler here
//    }
//
//    func callAPI(a string) error {
//        // handler
//
//        time.Sleep(100 * time.Millisecond)
//        return nil
//    }

    private static boolean stop = false;
    public static void main(String[] args) {

        ExecutorService services = Executors.newFixedThreadPool(5);
        String[] APIList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        boolean stop = false;

        for (int i = 0; i < APIList.length; i++) {
            int finalI = i;
            services.submit(() -> {
                try {
                    callAPI(APIList[finalI]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        if (stop) {
            services.shutdownNow();
        }

        services.shutdown();
    }
    public static void callAPI(String a) throws InterruptedException {
        try{
            System.out.println(a);
            Thread.sleep(2000);
        }catch (Exception e) {
            stop = true;
            throw new RuntimeException(e);
        }
    }

}
