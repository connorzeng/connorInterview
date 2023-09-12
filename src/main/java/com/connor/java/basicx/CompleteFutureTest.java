package com.connor.java.basicx;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompleteFutureTest {

    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync((Supplier<Void>) () -> {
            System.out.println("currentThread:" + Thread.currentThread().getName());
            System.out.println("A");
            return null;
        }).thenApply((Function<Void, Void>) unused -> {
            System.out.println("currentThread:" + Thread.currentThread().getName());
            System.out.println("B");
            return null;
        }).thenApply(unused -> {
            System.out.println("currentThread:" + Thread.currentThread().getName());
            System.out.println("C");
            return null;
        });
        future.complete(null);
    }
}
