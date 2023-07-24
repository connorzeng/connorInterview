package com.connor.java.sort;

public class Sort {

    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        


    }


    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
    }


    public static void bubbleSort(int[] arr){
        for (int length = arr.length - 1; length > 0; length--){
            for (int l = 0; l < length; l++){
                if (arr[l+1] < arr[l]){
                    swap(arr,l+1, l);
                }
            }
        }
    }

    public static void selectSort(int[] arr){
        for (int h = arr.length - 1; h > 0; h-- ){
            // 将最大的冒泡到最后一位。
            int max = 0;
            for (int l = 1; l < h; l++){
                if (arr[max] < arr[l]){
                    max = l;
                }
            }
            if (arr[max] > arr[h]){
                swap(arr, max ,h);
            }
        }
    }

    public static void quickSort(int[] arr){
        doQuickSort(arr,0,arr.length-1);
    }

    private static void doQuickSort(int[] arr, int low, int high) {
        if (low < high){
            // 分治
            int p = partition(arr, low, high);

            // 左右在比对
            doQuickSort(arr, low, p - 1);
            doQuickSort(arr,p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        int p = arr[low]; //pivot随意设置

        while (i < j){
            // 右边找到比 pivot小的下标。
            while (i < j && arr[j] >= p) {
                j--;
            }

            // 左边找到比 pivot大的下标。
            while( i < j && arr[i] <= p) {
                i++;
            }
            swap(arr, i, j);
        }
        arr[low] = arr[i];
        arr[i] = p; // 将基准移动到中间。

        return i;
    }
    public static void swap(int[] arr, int i, int j) {
        if (i == j){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
