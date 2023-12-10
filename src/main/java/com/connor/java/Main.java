package com.connor.java;

import com.connor.java.basic.IShout;

import java.util.ServiceLoader;

public class Main {

//    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Press Opt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        String consoleEncoding = System.getProperty("file.encoding");
//        System.out.println("11111111111：" + consoleEncoding);
//
//        System.out.println("Hello and welcome!");
//        System.out.println("Hello and welcome!");
//        System.out.println("行动方案!");
//        System.out.println("hello1");
//
//        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//            // Press Ctrl+D to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Cmd+F8.
//            System.out.println("i1 = " + i);
//        }
//        testSPI();
//        testClassLoader();
//    }



    public static void testSPI(){
        ServiceLoader<IShout> servics = ServiceLoader.load(IShout.class);
        for (IShout servic : servics) {
            servic.shout();
        }
    }

    public static void testClassLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> shoutClass = Class.forName("com.connor.java.basic.Dog");

        IShout shout  = (IShout) shoutClass.newInstance();

        shout.shout();
    }

    public static void main(String[] args) {

        System.out.println(fib(100));
    }
    public static long fib(int i){
        long[] dp = new long[i+1];
        dp[1] = 1;
        dp[2] = 1;
        if (i <=2 ){
            return dp[i];
        }

        for (int j = 3; j < dp.length; j++){
            dp[j] = dp[j-1] + dp[j-2];
        }

        return dp[i];
    }
}
