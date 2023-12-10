import java.util.ArrayList;
import java.util.List;



public class OOMExample {
    public static void main(String[] args) throws InterruptedException {
        int  count = 0;
        int finalCount = count;
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    Runtime runtime = Runtime.getRuntime();
                    long maxMemory = runtime.maxMemory();
                    long totalMemory = runtime.totalMemory();
                    long freeMemory = runtime.freeMemory();
                    long usedMemory = totalMemory - freeMemory;

//                    System.out.println("Max Memory: " + formatSize(maxMemory));
//                    System.out.println("Total Memory: " + formatSize(totalMemory));
//                    System.out.println("Free Memory: " + formatSize(freeMemory));
//                    System.out.println("Used Memory: " + formatSize(usedMemory));
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            while (true) {
                Thread t = new Thread(() -> {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                count++;
                t.start();
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of Memory Error: Unable to create more threads");
        }

        Thread.sleep(5000);
        System.out.println("count: " + count);
    }
    public static void mainb(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                // 分配大量内存，触发OOM
                List<byte[]> list = new ArrayList<>();
                while (true) {
                    list.add(new byte[1024 * 1024]); // 分配1MB内存
                }
            } catch (OutOfMemoryError e) {
                System.out.println("Thread 1: OutOfMemoryError");
            }
        });

        Thread thread2 = new Thread(() -> {
            // 另一个线程继续执行
            for (int i = 0; i < 15; i++) {
                System.out.println("Thread 2: Running " + i);
                // 统计并打印当前JVM内存
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Runtime runtime = Runtime.getRuntime();
                long maxMemory = runtime.maxMemory();
                long totalMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
                long usedMemory = totalMemory - freeMemory;

                System.out.println("Max Memory: " + formatSize(maxMemory));
                System.out.println("Total Memory: " + formatSize(totalMemory));
                System.out.println("Free Memory: " + formatSize(freeMemory));
                System.out.println("Used Memory: " + formatSize(usedMemory));
            }
        });
        thread1.start();
        thread2.start();
    }
    private static String formatSize(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024));
    }
}
