package com.connor.java.leetcode;

public class TestConnor {
    // 最大连续子数组, 最大值.
    public static void main(String[] args) {
        System.out.println(maxSeq(new int[]{ 1, 4, -1, 9, -1}));
        System.out.println(maxSub(new int[]{ 1, 4, -1, 9, -1}));

        System.out.println(maxSeq(new int[]{ 1, -1, -1, 9, -1}));
        System.out.println(maxSub(new int[]{ 1, -1, -1, 9, -1}));

        System.out.println(maxSeq(new int[]{ 1, 4, -3, 9, -1}));
        System.out.println(maxSub(new int[]{ 1, 4, -3, 9, -1}));

        System.out.println(maxSeq(new int[]{-1, -1}));
        System.out.println(maxSub(new int[]{-1, -1}));
    }

    public static int maxSeq(int[] params){
        // [ 1, 4, -5, 9, -1]
        if (params.length == 0){
            return 0;
        }
        if (params.length == 1){
            return params[0];
        }

        int max = 0;
        int left = 0;
        for (int i = 0; i < params.length; i++) {
            int sum = 0;
            for (int j = left; j <= i; j++){
                sum += params[j];
            }
            max = Math.max(sum, max);

            // 判断sum
            if (sum <= 0){
                left = i+1;
            }
        }
        return max;
    }

    public static  int maxSub(int[] params){
        if (params.length == 0){
            return 0;
        }
        if (params.length == 1){
            return params[0];
        }
        int max = 0;
        int sum = 0;
        for (int param : params) {
            sum += param;
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }


}
