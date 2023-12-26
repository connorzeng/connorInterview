package com.connor.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestInterview {

//    题目1：
//    小Q在进行射击气球的游戏，如果小Q在连续T枪中打爆了所有颜色的气球，将得到一只QQ公仔作为奖励。（每种颜色的气球至少被打爆一只）。
//    这个游戏中有m种不同颜色的气球，编号1到m。小Q一共有n发子弹，然后连续开了n枪。小Q想知道在这n枪中，打爆所有颜色的气球最少用了连续几枪？
//            ● 输入描述：
//    第一行两个空格间隔的整数数n，m。n<=1000000 m<=2000
//    第二行一共n个空格间隔的整数，分别表示每一枪打中的气球的颜色,0表示没打中任何颜色的气球。
//            ● 输出描述：
//    一个整数表示小Q打爆所有颜色气球用的最少枪数。如果小Q无法在这n枪打爆所有颜色的气球，则输出-1
//            ● 示例1
//    输入：
//            12 子弹 5 颜色
//            2 5 3 1 3 2 4 1 0 5 4 3
//    输出：
//            6
//            ● 示例2
//    输入：
//            12 5
//            2 5 3 1 3 2 4 1 5 0 4 3
//    输出： 5

    public static void main(String[] args) {

        System.out.println(getShortIndexNew(12, 5, new int[]{2, 5, 3, 1, 3, 2, 4, 1, 0, 5, 4, 3}));
//        System.out.println(getShortIndexNew(12, 5, new int[]{2, 5, 3, 1, 3, 2, 4, 1, 5, 0, 4, 3}));

    }
    
    

    public static int getShortIndexNew(int n, int m, int[] shuts){
        if (n <= 0 || m <= 0){
            return -1;
        }
        Set<Integer> shutRes = new HashSet<>();
        int minSeq = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        for (; r < shuts.length; r++) {
            if (shuts[r] > 0){
                shutRes.add(shuts[r]);
            }
            if (shutRes.size() == m){
                minSeq = Math.min(minSeq, r - l);
                l++;
                r = l;
                if (shuts.length - l <= m){
                    break;
                }
                shutRes.clear();
            }

        }
        return minSeq;
    }


    public static int getShortIndex(int n, int m, int[] shuts){
        if (n <= 0 || m <= 0){
            return -1;
        }
        // 预制数据
//        Map<Integer,Boolean> shortResult  = new HashMap<>();
//        for (int i = 0; i < m; i++) {
//            shortResult.put(i+1, false);
//        }
        Set<Integer> shutRes = new HashSet<>();
        for (int i = 0; i < shuts.length; i++) {
            if (shuts[i] > 0){
                shutRes.add(shuts[i]);
            }
            // 计算颜色是否打满
            if (shutRes.size() == m){
                return i;
            }
        }

        return -1;
    }
}
