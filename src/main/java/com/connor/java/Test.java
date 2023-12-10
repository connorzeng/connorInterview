package com.connor.java;

import java.util.Stack;

public class Test {


    public static void main(String[] args) {

        int[] file1 = new int[]{1,3,5};
        int[] file2 = new int[]{2,4,6,8};
        int[] file3 = new int[]{3,3,5};
        int[] file4 = new int[]{3,4,5};

        mergeList(file1,file2);

        mergeListLot(file1, file2, file3, file4);
    }

    class Index {
        public int i;
        public int j;

    }

    private static int[] mergeListLot(int[]... files) {
        if (files.length == 0){
            return null;
        }
        int min = 0;
        Stack<Index> minStack = new Stack<>();
        for (int i = 0; i < files.length; i++) {


        }




        return null;
    }

    private static int[] mergeList(int[] file1, int[] file2) {
        int[] result = new int[file1.length + file2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < file1.length && j < file2.length){
            int cur1 = file1[i];
            int cur2 = file2[j];
            if (cur1 < cur2){
                result[k] = cur1;
                i++;
            } else {
                result[k] = cur2;
                j++;
            }
            k++;
        }

        while (i < file1.length){
            result[k] = file1[i];
            k++;
        }
        while (j < file2.length){
            result[k] = file2[j];
            k++;
            j++;
        }

        return result;
    }

}
