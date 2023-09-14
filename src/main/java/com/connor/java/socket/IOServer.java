package com.connor.java.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * @author 闪电侠
 */
public class IOServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);
        HashSet<Thread> works = new HashSet<>();

        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    Thread a = new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                        }
                    });
                    a.start();
                    works.add(a);

                } catch (IOException e) {
                }

            }
        }).start();


        // (2) 每隔1s打印线程数
        new Thread(() -> {
            while (true) {
                works.forEach((a)->{
                    // 打印当前线程状态
                    System.out.println(a.getState());
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }
}
