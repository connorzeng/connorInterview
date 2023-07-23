package com.connor.java.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class SortTest {

    private static final Logger logger = Logger.getLogger(SortTest.class.getName());

    @Test
    public void testQuickSort() {
        logger.info("begin test quick sort");
//        int[] arr =  new int[]{4,3,10,2,9,11};
//        int[] arr =  new int[]{5,4,3,2,1};
        int[] arr =  new int[]{5,4,3,2,1};
        logger.info("before sort:" + Arrays.toString(arr));
        Sort.quickSort(arr);
        logger.info("after sort:" + Arrays.toString(arr));
    }

    @Test
    public void testBubbleSort() {
        logger.info("begin test bubble sort");
        int[] arr =  new int[]{4,3,10,2,9,11};
//        int[] arr =  new int[]{5,4,3,2,1};
//        int[] arr =  new int[]{5,4,3,2,1};
        logger.info("before sort:" + Arrays.toString(arr));
        Sort.bubbleSort(arr);
        logger.info("after sort:" + Arrays.toString(arr));
    }

    @Test
    public void testSelectSort() {
        logger.info("begin test select sort");
        int[] arr =  new int[]{4,3,10,2,9,11};
//        int[] arr =  new int[]{5,4,3,2,1};
//        int[] arr =  new int[]{5,4,3,2,1};
        logger.info("before sort:" + Arrays.toString(arr));
        Sort.selectSort(arr);
        logger.info("after sort:" + Arrays.toString(arr));
    }
    // insertSort
    @Test
    public void testInsertSort() {
        logger.info("begin test insert sort");
        int[] arr =  new int[]{4,3,10,2,9,11};
//        int[] arr =  new int[]{5,4,3,2,1};
//        int[] arr =  new int[]{5,4,3,2,1};
        logger.info("before sort:" + Arrays.toString(arr));
        Sort.insertSort(arr);
        logger.info("after sort:" + Arrays.toString(arr));
    }

    @Test
    public void testMergeSort(){
        logger.info("begin test merge sort");
//        int[] arr =  new int[]{4,3,10,2,9,11};
//        int[] arr =  new int[]{5,4,3,2,1};
        int[] arr =  new int[]{5,4,3,2,1};
        logger.info("before sort:" + Arrays.toString(arr));
        Sort.mergeSort(arr);
        logger.info("after sort:" + Arrays.toString(arr));
    }


}