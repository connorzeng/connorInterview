package com.connor.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class ArraySolution {


    public static void main(String[] args) {

        int[] a1  = new int[]{1,2,4};
        int[] a2  = new int[]{1,3,4};
        int[] a3  = new int[]{1,5,9};
        int[] a4  = new int[]{3,8,10};
        ArrayList<int[]> files = new ArrayList<>();
        files.add(a1);
        files.add(a2);
        files.add(a3);
        files.add(a4);

        //bubleSort(new int[]{5,3,2,4});

        int[] sortArray = new int[]{4,5,6,1,2};
        quickSort(sortArray, 0, sortArray.length - 1);

//        int[] res = mergeList(files, 0, files.size() - 1);
        //int[] res = mergeList(a1, a2);

//        System.out.println(Arrays.toString(res));

        System.out.println(Arrays.toString(sortArray));

    }

    public static int[] mergeList(ArrayList<int[]> files, int left, int right){
        if (right <= left){
            return files.get(right);
        }

        int privot = (left + right) / 2;
        int[] l = mergeList(files, left, privot);
        int[] r = mergeList(files, privot + 1, right);

        return mergeList(l, r);
    }
    public static int[] mergeList(int[] l, int[] r){
        if (l.length == 0 || r.length == 0){
            return new int[]{};
        }

        int[] res = new int[l.length + r.length];
        int i = 0, j = 0, k = 0;
        while (i < l.length && j < r.length){
            int curL = l[i];
            int curR = r[j];
            if (curL < curR){
                res[k] = curL;
                i++;
            } else {
                res[k] = curR;
                j++;
            }
            k++;
        }

        while (i < l.length){
            res[k] = l[i];
            i++;
            k++;
        }
        while (j < r.length){
            res[k] = r[j];
            j++;
            k++;
        }
        return res;
    }

    public static void bubleSort(int[] arrays){
        if (arrays.length <= 1){
            return;
        }
        for (int i = arrays.length - 1; i >= 0; i--){
            for (int j = 0; j < i; j++){
                if (arrays[j] > arrays[j+1]){
                    swapValue(arrays, j, j+1);
                }
            }
        }
    }

    private static void swapValue(int[] arrays, int j, int i) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }


    public static void quickSort(int[] arr, int low, int high){
        int p,i,j,temp;

        if(low >= high) {
            return;
        }
        //p就是基准数,这里就是每个数组的第一个
        p = arr[low];
        i = low;
        j = high;
        while(i < j) {
            //右边当发现小于p的值时停止循环
            while(arr[j] >= p && i < j) {
                j--;
            }
            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于p的值时停止循环
            while(arr[i] <= p && i < j) {
                i++;
            }

            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = p;
        quickSort(arr,low,j-1);  //对左边快排
        quickSort(arr,j+1,high); //对右边快排
    }


    // 使用栈来实现队列

    public static void testQ(){



    }

    class MyQueue<T> {

        public MyQueue(int lenght){

        }

        Stack<T> inS = new Stack<>();
        Stack<T> outS = new Stack<>();


        public boolean offer(T t){
            inS.push(t);
            return true;
        }


        public T poll(){

            // 判断outS是否为空
            if (outS.isEmpty()){
                while (!inS.isEmpty()){
                    outS.push(inS.pop());
                }
            }

            // 再次判断
            if (outS.isEmpty()){
                return null;
            }

            return outS.pop();
        }

    }


}
