package com.connor.java.basic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class FinalMethodProxyExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建目标对象
        FinalClass finalObj = new FinalClass();

        // 创建 CGLIB 的 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FinalClass.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("Before method execution");
            Object result = proxy.invokeSuper(obj, args1); // 调用原始方法
            System.out.println("After method execution");
            return result;
        });

        // 创建代理对象
        FinalClass proxyObj = (FinalClass) enhancer.create();

        // 调用代理对象的 final 方法
        proxyObj.finalMethod();
        Thread.sleep(1000);
        //proxyObj.test();
    }

    static final class FinalClass {
        public final void finalMethod() {
            System.out.println("Final method1");
        }
        public void test() {
            System.out.println("testa");
        }
    }
}

