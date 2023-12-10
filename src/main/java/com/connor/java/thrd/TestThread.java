package com.connor.java.thrd;

import java.util.HashMap;
import java.util.Map;

public class TestThread {


    public static void main(String[] args) {
        Thread a = new MyThread("A");
        Thread b = new MyThread("B");
        Thread c = new MyThread("C");
        a.start();
        b.start();
        c.start();
    }

    private static String lock = "lock";
    private static String curName = "A";

    private static Map<String,String> nextMap = new HashMap<>();

    static{
        nextMap.put("A","B");
        nextMap.put("B","C");
        nextMap.put("C","A");
    }
    static class MyThread extends Thread{

        public MyThread(String name){
            this.name = name;
        }
        private String name;



        @Override
        public void run() {
                // A--> B --> C
            while (true){
                synchronized (lock){
                    if (!curName.equals(name)){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(name);
                    curName = nextMap.get(curName);
                    lock.notifyAll();
                }
            }
        }
    }
}
