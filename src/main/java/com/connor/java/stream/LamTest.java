package com.connor.java.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LamTest {


    public static void main(String[] args) {

        // 1. 遍历
        List<String> arrs = Arrays.asList("ab", "ac", "aa","dd");
        arrs.forEach(System.out::println);

        // 2. 排序
        arrs.sort(String::compareTo);
        arrs.forEach(System.out::println);

        // 3.过滤
        List<String> a = arrs.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.startsWith("a")){
                    return true;
                }
                return false;
            }
        }).sorted(String::compareTo).collect(Collectors.toList());
        a.forEach(System.out::println);

        // 4.计算, 获取arrs2每个字符的长度, 放入到List中
        // map不是k-v存储,而是做一个映射转换.
        List<String> arrs2 = Arrays.asList("adb", "acddd", "adda","ddddd");
        List<Integer> arrs3 = arrs2.stream().map(String::length).collect(Collectors.toList());
        arrs3.forEach(System.out::println);

        // 5.取sum, 使用reduce
        List<Integer> arrs4 = Arrays.asList(1, 4, 9, -1, 33);
        // 取arrs4和
        int sums = arrs4.stream().reduce(0, Integer::sum);
        System.out.println("总数:" + sums);

        // 6.分组, 分group
        Map<Integer,List<String>> group = arrs2.stream().collect(Collectors.groupingBy(String::length));
        group.forEach((k,v)-> System.out.println("k:"+ k + ",v:" + v));


        // 7.使用Optional进行NPE判断.
        String as = "helloworld";
        String as1 = null;
        Optional.ofNullable(as).ifPresent(System.out::println);
        Optional.ofNullable(as1).ifPresent(System.out::println);
        System.out.println(as1);
    }
}
